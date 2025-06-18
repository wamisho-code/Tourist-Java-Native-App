package com.example.touristapp.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.touristapp.R;

public class BookingActivity extends AppCompatActivity {
    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar=findViewById(R.id.progressHotel);
            String url =getIntent().getStringExtra("link");

        WebView webView = findViewById(R.id.webView);

        // Configure WebView
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript
        webView.getSettings().setLoadWithOverviewMode(true); // Fit content to screen
        webView.getSettings().setUseWideViewPort(true); // Enable viewport scaling
        webView.getSettings().setSupportZoom(true); // Enable zoom
        webView.getSettings().setBuiltInZoomControls(true); // Add zoom controls
        webView.getSettings().setDisplayZoomControls(false); // Hide zoom controls
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.getSettings().setDomStorageEnabled(true); // Enable DOM storage for better compatibility
        webView.getSettings().setDatabaseEnabled(true); // Enable database storage



        // Set a WebViewClient to handle page load callbacks
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE); // Show ProgressBar when loading starts
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE); // Hide ProgressBar when loading finishes
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE); // Hide ProgressBar on error
                Toast.makeText(BookingActivity.this, "Failed to load webpage.", Toast.LENGTH_SHORT).show();
            }
        });
        webView.loadUrl(url);



    }
}