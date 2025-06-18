package com.example.touristapp.ui.notifications;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class apiModel {
    @SerializedName("success")
    private boolean success;

    @SerializedName("rates")
    private Map<String, Double> rates;

    public boolean isSuccess() {
        return success;
    }

    public double getRate(String currency) {
        if (rates != null && rates.containsKey(currency)) {
            return rates.get(currency);
        }
        return 0;
    }
}
