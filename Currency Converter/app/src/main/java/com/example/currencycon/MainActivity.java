package com.example.currencycon;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.currencycon.currency.CustomSpinnerAdapter;
import com.example.currencycon.currency.ExchangeRate;
import com.example.currencycon.currency.ExchangeRateDatabase;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final OkHttpClient client = new OkHttpClient();
    private static final int INTERNET_PERMISSION_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check and request internet permission if needed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        INTERNET_PERMISSION_REQUEST_CODE);
            }
        }

        // Set up UI components
        setUpSpinners();
        setUpPopupMenu();

        // Add click listener to the calculate button
        Button calculateButton = findViewById(R.id.button_1);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCurrencies();
            }
        });
    }

    // Set up spinners with custom adapter
    private void setUpSpinners() {
        List<CharSequence> items = Arrays.asList(getResources().getTextArray(R.array.Currency));
        List<Integer> imageIds = Arrays.asList(R.drawable.flag_eur, R.drawable.flag_usd, R.drawable.default_flag);

        Spinner spinner = findViewById(R.id.spinner_1);
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, items, imageIds);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner1 = findViewById(R.id.spinner_2);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);
    }

    // Set up popup menu for options
    private void setUpPopupMenu() {
        ImageView imageView = findViewById(R.id.imageView4);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });
    }

    // Update the currency rates from the ECB
    private void updateCurrencies() {
        try {
            URL url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String xmlData = readStream(in);

                // Parse XML data and update exchange rates
                updateExchangeRatesFromXml(xmlData);
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            Log.e("CurrencyConverter", "Exception during currency rates update: " + e.getMessage());
        }
    }

    // Read the input stream and convert it to a string
    private String readStream(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append('\n');
        }
        return stringBuilder.toString();
    }

    // Parse the XML data and update exchange rates
    private void updateExchangeRatesFromXml(String xmlData) {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(xmlData));

            int eventType = parser.getEventType();
            String currentCurrency = null;
            double currentRate = 0;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String tag = parser.getName();
                        if ("Cube".equals(tag)) {
                            String currencyAttribute = parser.getAttributeValue(null, "currency");
                            if (currencyAttribute != null) {
                                currentCurrency = currencyAttribute;
                            }
                            String rateAttribute = parser.getAttributeValue(null, "rate");
                            if (rateAttribute != null) {
                                currentRate = Double.parseDouble(rateAttribute);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("Cube".equals(parser.getName()) && currentCurrency != null) {
                            ExchangeRateDatabase.getInstance().setExchangeRate(currentCurrency, currentRate);
                            currentCurrency = null;
                        }
                        break;
                }

                eventType = parser.next();
            }

            // Notify UI to refresh if needed
            runOnUiThread(new Runnable() {
                @Override
                public void run() {


                }
            });

        } catch (XmlPullParserException | IOException e) {
            Log.e("CurrencyConverter", "Error parsing XML data: " + e.getMessage());
        }
    }

    // Show popup menu with options
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu2, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.item1) {
                    onItemSelected(ExchangeRateDatabase.RATES);
                } else if (itemId == R.id.item2) {
                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(() -> updateCurrencies());
                }
                return true;
            }

            private void onItemSelected(ExchangeRate[] rates) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Currency List");

                StringBuilder message = new StringBuilder();
                for (ExchangeRate rate : rates) {
                    // Append the currency name, capital, and exchange rate value
                    message.append(rate.getCurrencyName())
                            .append(" - ")
                            .append(rate.getCapital())
                            .append(" - ")
                            .append(rate.getRateForOneEuro())
                            .append("\n");
                }

                builder.setMessage(message.toString());
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
        popupMenu.show();
    }


    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == INTERNET_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can proceed with your logic
            } else {
                // Permission denied, handle accordingly
                Toast.makeText(this, "Internet permission is required for currency update.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Calculate currency conversion
    private void calculateCurrencies() {
        // Get values from UI elements
        EditText inputEditText = findViewById(R.id.input_1);
        Spinner fromCurrencySpinner = findViewById(R.id.spinner_1);
        Spinner toCurrencySpinner = findViewById(R.id.spinner_2);
        EditText resultEditText = findViewById(R.id.editTextNumberDecimal3);

        String inputText = inputEditText.getText().toString();
        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();

        if (!inputText.isEmpty()) {
            try {
                double inputValue = Double.parseDouble(inputText);

                // Get exchange rates
                double exchangeRateFrom = ExchangeRateDatabase.getInstance().getExchangeRate(fromCurrency);
                double exchangeRateTo = ExchangeRateDatabase.getInstance().getExchangeRate(toCurrency);

                if (exchangeRateFrom <= 0 || exchangeRateTo <= 0) {
                    Log.e("CurrencyConverter", "Invalid exchange rates");
                    Toast.makeText(MainActivity.this, "Invalid exchange rates", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Perform currency conversion
                double result = inputValue / exchangeRateFrom * exchangeRateTo;

                // Display the result in the result field
                resultEditText.setText(String.valueOf(result));

            } catch (NumberFormatException e) {
                Log.e("CurrencyConverter", "NumberFormatException: " + e.getMessage());
                Toast.makeText(MainActivity.this, "Invalid input value", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("CurrencyConverter", "Exception during conversion: " + e.getMessage());
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "An error occurred during conversion", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Please enter a valid input value", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle when nothing is selected in the spinner
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Handle when an item is selected in the spinner
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
