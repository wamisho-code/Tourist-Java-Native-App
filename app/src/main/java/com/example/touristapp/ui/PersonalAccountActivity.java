package com.example.touristapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.touristapp.Classfile.Tourist;
import com.example.touristapp.MainActivity;
import com.example.touristapp.Model.UniqueCodes;
import com.example.touristapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import android.content.res.Resources;

import java.util.Arrays;
import java.util.List;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalAccountActivity extends AppCompatActivity {
    Button saveButton;
    FirebaseDatabase database;
    FirebaseAuth auth;
    private List<String> countryList;
    EditText firstName, lastName, countryEditText;
    TextView  email;
    ProgressBar progressBar;
    ImageView account;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_account);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();


        database=FirebaseDatabase.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        account=findViewById(R.id.account);
        progressBar=findViewById(R.id.progressbar);

        String name = user.getDisplayName();
        String emailString = user.getEmail();
        String photoUrl = user.getPhotoUrl().toString();
        firstName = findViewById(R.id.firstname_text);
        lastName = findViewById(R.id.lastname_text);

        countryEditText = findViewById(R.id.country_array);
        saveButton = findViewById(R.id.saveAccount);

        saveButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            Tourist tourist = new Tourist();
            String fName = firstName.getText().toString();
            String lName = lastName.getText().toString();
            String uemail = emailString;
            String countryName = countryEditText.getText().toString();

            if (isValidCountry(countryName) && !fName.isEmpty() && !lName.isEmpty()
                    ) {
                tourist.setFirst_name(fName);
                tourist.setLast_name(lName);
                tourist.setPlace(countryName);
                tourist.setEmail(uemail);
                UniqueCodes uniqueCodes=new UniqueCodes();
                String subChild = uniqueCodes.dateUnique() + tourist.getFirst_name();
                database.getReference().child("UserInfo").child(subChild).setValue(tourist)
                        .addOnCompleteListener(task -> {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(PersonalAccountActivity.this, "User data added successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, MainActivity.class));
                            } else {
                                Toast.makeText(PersonalAccountActivity.this, "Failed to add user data", Toast.LENGTH_SHORT).show();
                            }
                        });


            }else if (fName.isEmpty() && lName.isEmpty()
            ){
                Toast.makeText(this, "Fill all information", Toast.LENGTH_SHORT).show();
            }
            if(!isValidCountry(countryName) ) {
                Toast.makeText(PersonalAccountActivity.this, "Invalid Country Name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isValidCountry(String countryName) {
        if (countryList == null) {
            Resources res = getResources();
            countryList = Arrays.asList(res.getStringArray(R.array.country_array));
        }
        return countryList.contains(countryName);
    }


}









