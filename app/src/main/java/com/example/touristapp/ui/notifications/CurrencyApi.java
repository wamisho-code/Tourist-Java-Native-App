package com.example.touristapp.ui.notifications;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyApi {
    @GET("latest")
    Call<apiModel> getExchangeRates(
            @Query("access_key") String apiKey
    );
}
