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

public class VactionSiteActivity extends AppCompatActivity {
    private CardView haile,kuriftu,paradise,sheraton,skylite;
    private TextView haile_txt,kuriftu_txt,paradise_txt,sheraton_txt,skylite_txt;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vaction_site);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        haile=findViewById(R.id.hawassa);
        kuriftu=findViewById(R.id.kuriftu);
        paradise=findViewById(R.id.paradise);
        sheraton=findViewById(R.id.sheraton);
        skylite=findViewById(R.id.skyLight);


        haile_txt=findViewById(R.id.haileresort);
        kuriftu_txt=findViewById(R.id.kuriftu_txt);
        paradise_txt=findViewById(R.id.paradise_txt);
        skylite_txt=findViewById(R.id.skyliteHotel);
        sheraton_txt=findViewById(R.id.sheraton_txt);


        haile.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","vacationPlaces");
            intent.putExtra("id","1");
            intent.putExtra("image","haile");
            intent.putExtra("title",haile_txt.getText().toString());
            intent.putExtra("commentCatagory","haile");
            startActivity(intent);
        });
        kuriftu.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","vacationPlaces");
            intent.putExtra("id","2");
            intent.putExtra("image","kuriftu");
            intent.putExtra("title",kuriftu_txt.getText().toString());
            intent.putExtra("commentCatagory","kuriftu");
            startActivity(intent);
        });
        sheraton.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","vacationPlaces");
            intent.putExtra("id","4");
            intent.putExtra("image","sheraten");
            intent.putExtra("title",sheraton_txt.getText().toString());
            intent.putExtra("commentCatagory","sheraton");
            startActivity(intent);
        });
        skylite.setOnClickListener(v -> {
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","vacationPlaces");
            intent.putExtra("id","3");
            intent.putExtra("image","skylite");
            intent.putExtra("title",skylite_txt.getText().toString());
            intent.putExtra("commentCatagory","skylite");
            startActivity(intent);
        });
        paradise.setOnClickListener(v->{
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("tbname","vacationPlaces");
            intent.putExtra("image","paradise");
            intent.putExtra("id","5");
            intent.putExtra("title",paradise_txt.getText().toString());
            intent.putExtra("commentCatagory","paradise");
            startActivity(intent);

        });

    }
}