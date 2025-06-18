package com.example.touristapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

import com.example.touristapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationFragment extends Fragment {
    private String baseCurrency = "USD"; // Default base currency
    private String convertedToCurrency = "ETB"; // Default target currency
    double exchangeRate = 0; // Holds the exchange rate
    private Button convert;
    private ProgressBar gp_bar;
    private EditText amount;
    private EditText resultTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        amount = view.findViewById(R.id.editText);
        resultTextView = view.findViewById(R.id.resultEdt);
        gp_bar = view.findViewById(R.id.progress_bar);
        convert = view.findViewById(R.id.convert);
        Spinner fromSpinner = view.findViewById(R.id.fromSpinner);
        Spinner toSpinner = view.findViewById(R.id.toSpinner);
        View animatedView = view.findViewById(R.id.dropAnime);
        CardView cv = view.findViewById(R.id.cardView);
        Animation fallDown = AnimationUtils.loadAnimation(getContext(), R.anim.drop_down);
        animatedView.startAnimation(fallDown);
        Animation cMoveUp = AnimationUtils.loadAnimation(getContext(), R.anim.move_up);
        cv.startAnimation(cMoveUp);

        // Populate spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.currency_codes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Handle spinner selections
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baseCurrency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertedToCurrency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Button click listener
        convert.setOnClickListener(v -> {
            gp_bar.setVisibility(View.VISIBLE);
            String amountString = amount.getText().toString();
            if (amountString.isEmpty()) {
                Toast.makeText(getContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                gp_bar.setVisibility(View.GONE);
            } else {
                getApiResult(amountString);
            }
        });
    }

    private void getApiResult(String amountString) {
        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create the API interface
        CurrencyApi currencyApi = retrofit.create(CurrencyApi.class);

        // API call with API key
        Call<apiModel> call = currencyApi.getExchangeRates("bb86860ccd505a00736d8216cb917024");

        call.enqueue(new Callback<apiModel>() {
            @Override
            public void onResponse(Call<apiModel> call, Response<apiModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiModel model = response.body();
                    if (model.isSuccess()) {
                        exchangeRate = model.getRate(convertedToCurrency);

                        // Calculate the converted amount
                        double amount = Double.parseDouble(amountString);
                        double convertedAmount = amount * exchangeRate;

                        // Display the result
                        resultTextView.setText("" + convertedAmount);
                    } else {
                        Toast.makeText(getContext(), "Failed to get exchange rate", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "API response failed", Toast.LENGTH_SHORT).show();
                }
                gp_bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<apiModel> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
