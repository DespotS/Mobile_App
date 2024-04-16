package com.example.currencycon.currency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.currencycon.R;

import java.util.List;

/**
 * Custom ArrayAdapter for the currency spinner.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<CharSequence> {
    private final Context context;
    private final List<CharSequence> items;
    private final List<Integer> imageIds;

    /**
     * Constructor for the CustomSpinnerAdapter.
     *
     * @param context  The context.
     * @param items    The list of items (currencies).
     * @param imageIds The list of corresponding image resource IDs.
     */
    public CustomSpinnerAdapter(Context context, List<CharSequence> items, List<Integer> imageIds) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        this.imageIds = imageIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createCustomView(position, convertView, parent);
    }

    /**
     * Creates a custom view for the spinner item.
     *
     * @param position    The position of the item in the list.
     * @param convertView The recycled view.
     * @param parent      The parent view.
     * @return The custom view for the spinner item.
     */
    private View createCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Inflate the custom layout for the spinner item
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false);
        }

        // Find views within the custom layout
        TextView currencyName = convertView.findViewById(R.id.currencyName);
        ImageView currencyFlag = convertView.findViewById(R.id.currencyFlag);

        // Get the currency at the specified position
        CharSequence currency = getItem(position);

        // Set the text and corresponding image based on the currency
        if (currency != null) {
            currencyName.setText(currency);
            int flagResourceId = getFlagResourceId(currency.toString());
            currencyFlag.setImageResource(flagResourceId);
        }

        return convertView;
    }

    /**
     * Maps currency names to drawable resource IDs for flag images.
     *
     * @param currencyName The name of the currency.
     * @return The resource ID for the corresponding flag image.
     */
    // Method to map currency names to drawable resource IDs (adjust as needed)
    private int getFlagResourceId(String currencyName) {
        switch (currencyName) {
            case "EUR":
                return R.drawable.flag_eur;
            case "USD":
                return R.drawable.flag_usd;
            case "AUD":
                return R.drawable.flag_aud;
            case "BGN":
                return R.drawable.flag_bgn;
            case "BRL":
                return R.drawable.flag_brl;
            case "CAD":
                return R.drawable.flag_cad;
            case "CHF":
                return R.drawable.flag_chf;
            case "CNY":
                return R.drawable.flag_cny;
            case "CZK":
                return R.drawable.flag_czk;
            case "DKK":
                return R.drawable.flag_dkk;
            case "GBP":
                return R.drawable.flag_gbp;
            case "HKD":
                return R.drawable.flag_hkd;
            case "HRK":
                return R.drawable.flag_hrk;
            case "HUF":
                return R.drawable.flag_huf;
            case "IDR":
                return R.drawable.flag_idr;
            case "ILS":
                return R.drawable.flag_ils;
            case "INR":
                return R.drawable.flag_inr;
            case "ISK":
                return R.drawable.flag_isk;
            case "JPY":
                return R.drawable.flag_jpy;
            case "KRW":
                return R.drawable.flag_krw;
            case "MXN":
                return R.drawable.flag_mxn;
            case "MYR":
                return R.drawable.flag_myr;
            case "NOK":
                return R.drawable.flag_nok;
            case "NZD":
                return R.drawable.flag_nzd;
            case "PHP":
                return R.drawable.flag_php;
            case "PLN":
                return R.drawable.flag_pln;
            case "RON":
                return R.drawable.flag_ron;
            case "RUB":
                return R.drawable.flag_rub;
            case "SEK":
                return R.drawable.flag_sek;
            case "SGD":
                return R.drawable.flag_sgd;
            case "THB":
                return R.drawable.flag_thb;
            case "TRY":
                return R.drawable.flag_try;
            case "ZAR":
                return R.drawable.flag_zar;



            default:
                return 0; // Default flag resource
        }
    }
}

