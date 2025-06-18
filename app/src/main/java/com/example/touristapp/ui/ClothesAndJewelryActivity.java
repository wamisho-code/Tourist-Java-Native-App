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

import org.w3c.dom.Text;

public class ClothesAndJewelryActivity extends AppCompatActivity {

    CardView sidama,somale,oromia,tigray,amhara;
    TextView sidama_txt,somale_txt,oromia_txt,tigray_txt,amhara_txt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clothes_and_jewelry);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sidama=findViewById(R.id.sidam_cultural);
        somale=findViewById(R.id.somale_cultual);
        oromia=findViewById(R.id.oromo_cultural);

        amhara=findViewById(R.id.amara_cultual);
        sidama_txt=findViewById(R.id.sidama_culture_txt);
        somale_txt=findViewById(R.id.somale_culture_txt);
        oromia_txt=findViewById(R.id.oromo_culture_txt);

        amhara_txt=findViewById(R.id.amhara_culture_txt);
        sidama.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","cultualClothesAndJewelry");
            intent.putExtra("title",sidama_txt.getText().toString());
            intent.putExtra("image","sidama2");
            intent.putExtra("id","1");
            intent.putExtra("commentCatagory","sidama");
            startActivity(intent);
        });
        somale.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","cultualClothesAndJewelry");
            intent.putExtra("title",somale_txt.getText().toString());
            intent.putExtra("id","4");
            intent.putExtra("image","somale");
            intent.putExtra("commentCatagory","somale");
            startActivity(intent);
        });
        oromia.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","cultualClothesAndJewelry");
            intent.putExtra("title",oromia_txt.getText().toString());
            intent.putExtra("id","2");
            intent.putExtra("image","oromia");
            intent.putExtra("commentCatagory","oromia");
            startActivity(intent);
        });
        amhara.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","cultualClothesAndJewelry");
            intent.putExtra("title",amhara_txt.getText().toString());
            intent.putExtra("id","3");
            intent.putExtra("image","amhara");
            intent.putExtra("commentCatagory","amhara");
            startActivity(intent);
        });

    }
}