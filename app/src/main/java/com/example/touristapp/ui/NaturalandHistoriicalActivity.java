package com.example.touristapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.touristapp.R;

public class NaturalandHistoriicalActivity extends AppCompatActivity {

    private CardView aksum,lalibela,sofumer,nechisar,harrar,tiya;
    private TextView aksum_txt,lalibela_txt,sofumer_txt,nechisar_txt,harrar_txt,tiya_txt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_naturaland_historiical);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        aksum=findViewById(R.id.axsum);
        lalibela=findViewById(R.id.lalibela);

        sofumer=findViewById(R.id.sofumer);
        nechisar=findViewById(R.id.nechisar);
        harrar=findViewById(R.id.harrar);
        tiya=findViewById(R.id.tiya);
        aksum_txt=findViewById(R.id.aksum_txt);
        lalibela_txt=findViewById(R.id.lalibla_txt);
        sofumer_txt=findViewById(R.id.sofumer_txt);
        nechisar_txt=findViewById(R.id.nechisar_txt);
        harrar_txt=findViewById(R.id.harrar_txt);
        tiya_txt=findViewById(R.id.tiya_txt);

        aksum.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","historicalAndNaturalSites");
            intent.putExtra("id","0");
            intent.putExtra("image","axsumimage");
            intent.putExtra("title",aksum_txt.getText().toString());
            intent.putExtra("commentCatagory","aksum");
            startActivity(intent);
        });
        lalibela.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","historicalAndNaturalSites");
            intent.putExtra("id","1");
            intent.putExtra("image","lalibela");
            intent.putExtra("title",lalibela_txt.getText().toString());
            intent.putExtra("commentCatagory","lalibela");
            startActivity(intent);
        });

        sofumer.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","historicalAndNaturalSites");
            intent.putExtra("id","2");
            intent.putExtra("image","sofumer");
            intent.putExtra("title",sofumer_txt.getText().toString());
            intent.putExtra("commentCatagory","sofumer");
            startActivity(intent);
        });
        nechisar.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","historicalAndNaturalSites");
            intent.putExtra("id","3");
            intent.putExtra("image","nechisar");
            intent.putExtra("title",nechisar_txt.getText().toString());
            intent.putExtra("commentCatagory","nechisar");
            startActivity(intent);
        });
        harrar.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","historicalAndNaturalSites");
            intent.putExtra("title",harrar_txt.getText());
            intent.putExtra("image","harrar");
            intent.putExtra("id","4");
            intent.putExtra("commentCatagory","harrar");
            startActivity(intent);
        });
        tiya.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","historicalAndNaturalSites");
            intent.putExtra("title",tiya_txt.getText().toString());
            intent.putExtra("id","5");
            intent.putExtra("image","tiyastone");
            intent.putExtra("commentCatagory","tiya");
            startActivity(intent);
        });

    }
}