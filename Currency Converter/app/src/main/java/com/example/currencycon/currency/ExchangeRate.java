package com.example.currencycon.currency;

/**
 * Represents an exchange rate for a specific currency.
 */
public class ExchangeRate {
    private String currencyName;
    private double rateForOneEuro;
    private String capital;

    private int imageResourceId;  // Resource ID for the currency flag image

    // Constructor and other methods remain the same

    /**
     * Gets the resource ID for the currency flag image.
     *
     * @return The resource ID for the currency flag image.
     */
    public int getImageResourceId() {
        return imageResourceId;
    }

    /**
     * Sets the resource ID for the currency flag image.
     *
     * @param imageResourceId The resource ID for the currency flag image.
     */
    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    /**
     * Constructs an ExchangeRate object.
     *
     * @param currencyName   The name of the currency.
     * @param capital        The capital of the country associated with the currency.
     * @param rateForOneEuro The exchange rate for one Euro.
     */
    public ExchangeRate(String currencyName, String capital, double rateForOneEuro) {
        this.currencyName = currencyName;
        this.rateForOneEuro = rateForOneEuro;
        this.capital = capital;
    }

    /**
     * Gets the name of the currency.
     *
     * @return The name of the currency.
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Gets the capital of the country associated with the currency.
     *
     * @return The capital of the country associated with the currency.
     */
    public String getCapital() {
        return capital;
    }

    /**
     * Gets the exchange rate for one Euro.
     *
     * @return The exchange rate for one Euro.
     */
    public double getRateForOneEuro() {
        return rateForOneEuro;
    }

    /**
     * Sets the exchange rate for one Euro.
     *
     * @param rateForOneEuro The exchange rate for one Euro.
     */
    public void setRateForOneEuro(double rateForOneEuro) {
        this.rateForOneEuro = rateForOneEuro;
    }
}
