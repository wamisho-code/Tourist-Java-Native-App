package com.example.touristapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.touristapp.Adpter.CommentAdapter;
import com.example.touristapp.Classfile.Share;
import com.example.touristapp.Classfile.TourGuide;
import com.example.touristapp.DataBase.DatabaseHelper;
import com.example.touristapp.Model.UniqueCodes;
import com.example.touristapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayActivity extends AppCompatActivity {
    private Cursor res;
    private boolean commentIsShown = false;
    private RecyclerView commentLoader;
    private ArrayList<Share> userReactions;
    private ArrayList<Share> shareList;
    private RelativeLayout commentContainer;
    private LinearLayout extraDetails;
    private Button post;
    private String image;
    private EditText userComment;
    private ImageView imageView;
    private String commentCatagory;
    private FirebaseAuth auth;
    private CommentAdapter adapter;
    private String table_name;

    private TextView commentShow,description,Title;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase Auth
        imageView=findViewById(R.id.contentImage);
        auth = FirebaseAuth.getInstance();
        extraDetails=findViewById(R.id.placeDetails);
        // Initialize ArrayLists
        shareList = new ArrayList<>();
        description=findViewById(R.id.description);
        Title=findViewById(R.id.contenttitle);
        userReactions = new ArrayList<>();

        // Get Intent Data
        table_name=getIntent().getStringExtra("tbname");
        String tittle=getIntent().getStringExtra("title");
        String id=getIntent().getStringExtra("id");
        image=getIntent().getStringExtra("image");
        Title.setText(tittle);
        DatabaseHelper databaseHelper= new DatabaseHelper(this,table_name,id);
        res=databaseHelper.getAllData();
        commentCatagory = getIntent().getStringExtra("commentCatagory");
        getContent();
        // Initialize Views
        userComment = findViewById(R.id.user_comment_text);
        post = findViewById(R.id.postComment);
        commentContainer = findViewById(R.id.commentContainer);
        commentLoader = findViewById(R.id.comment_recyler);
        commentShow = findViewById(R.id.comments);

        // Setup RecyclerView and Adapter
        adapter = new CommentAdapter(shareList, this);
        commentLoader.setLayoutManager(new LinearLayoutManager(DisplayActivity.this));
        commentLoader.setAdapter(adapter);

        // Set OnClickListener for Post Button
        InputMethodManager imm = (InputMethodManager) getSystemService(DisplayActivity.INPUT_METHOD_SERVICE);
        post.setOnClickListener(v -> {
            View view = getCurrentFocus();
            if (view != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);}
            if (userComment.getText().toString().isEmpty()) {
                Toast.makeText(this, "Write Comment", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseUser user = auth.getCurrentUser();
            if (user == null || user.getEmail() == null) {
                Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
                return;
            }

            String emailString = user.getEmail();
            Log.e("email", emailString);

            DatabaseReference userInfoRef = FirebaseDatabase.getInstance().getReference("UserInfo");
            userInfoRef.orderByChild("email").equalTo(emailString).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        Log.e("Firebase", "No user found for email: " + emailString);
                        return;
                    }

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String firstName = snapshot.child("first_name").getValue(String.class);
                        String lastName = snapshot.child("last_name").getValue(String.class);
                        String location = snapshot.child("place").getValue(String.class);
                        String date= UniqueCodes.getFormattedTimestamp();

                        if (firstName == null || lastName == null) {
                            Log.e("Firebase", "Missing user details for email: " + emailString);
                            return;
                        }

                        Share share = new Share();
                        share.setComment(userComment.getText().toString());
                        share.setUserName(firstName + " " + lastName);
                        share.setLocation(location);
                        share.setDate(date);

                        DatabaseReference userReactionRef = FirebaseDatabase.getInstance().getReference("UserReaction")
                                .child(commentCatagory);

                        userReactionRef.push().setValue(share).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                userComment.setText("");
                                //Toast.makeText(DisplayActivity.this, "User data added successfully", Toast.LENGTH_SHORT).show();
                                getComment();
                            } else {
                                Toast.makeText(DisplayActivity.this, "Failed to add comment", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Firebase", "Error: " + databaseError.getMessage());
                }
            });
        });


        // Set OnClickListener for Show Comments
        commentShow.setOnClickListener(v -> {
            commentIsShown = !commentIsShown;
            if (commentIsShown) {
                commentContainer.setVisibility(View.VISIBLE);
            } else {
                commentContainer.setVisibility(View.GONE);
            }
        });

        // Fetch Comments
        getComment();
    }

    void getComment() {
        DatabaseReference userReactionRef = FirebaseDatabase.getInstance().getReference("UserReaction")
                .child(commentCatagory);

        userReactionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shareList.clear(); // Clear the list to avoid duplication
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Share share = snapshot.getValue(Share.class);
                    if (share != null) {
                        shareList.add(share);
                        Log.d("Firebase", "Comment: " + share.getComment() + ", UserName: " + share.getUserName());
                    }
                }
                // Notify your adapter or update your UI with the new data
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error: " + databaseError.getMessage());
            }
        });
    }
    void  getContent(){
        HashMap<String,String> link =new HashMap<>();
        link.put("skylite","https://www.ethiopianskylighthotel.com/");
        link.put("haile","https://hailehotelsandresorts.com/");
        link.put("paradise","https://www.paradiselodgeethiopia.com/");
        link.put("sheraton","https://www.marriott.com/en-us/hotels/addlc-sheraton-addis-a-luxury-collection-hotel-addis-ababa/overview/");
        link.put("paradise","https://www.paradiselodgeethiopia.com/");
        setImageOfContent(image);
        if (res.moveToFirst()) {
            // Extract data from the cursor for the first column
            String content = res.getString(2); // Column index starts at 0

            // Set data to a TextViews and populating the extradetail by filtering with if/else

            description.setText(content);
            if (table_name.equals("historicalAndNaturalSites") || table_name.equals("vacationPlaces")) {
                extraDetails.setVisibility(View.VISIBLE);

                TextView longitude = findViewById(R.id.longitude);
                longitude.setText( res.getString(4));
                longitude.setVisibility(View.VISIBLE);

                TextView latitude = findViewById(R.id.latitude);
                latitude.setText( res.getString(3));
                latitude.setVisibility(View.VISIBLE);

                TextView region = findViewById(R.id.region);
                region.setText("Region: " + res.getString(5));
                region.setVisibility(View.VISIBLE);

                TextView kmfromAA = findViewById(R.id.kmfromAA);
                kmfromAA.setText("Distance from Addis Ababa: " + res.getString(6));
                kmfromAA.setVisibility(View.VISIBLE);

                // Extract the link data from the Cursor before setting the listener
                String hotelLink = res.getString(7);

                TextView linker = findViewById(R.id.link);
                linker.setVisibility(View.VISIBLE);
                linker.setText("Hotel link");
                linker.setOnClickListener(v -> {
                    Toast.makeText(this, "Redirecting to hotel site...", Toast.LENGTH_SHORT).show();

                    if (hotelLink != null && !hotelLink.isEmpty()) {
                        Intent intent = new Intent(DisplayActivity.this, BookingActivity.class);
                        intent.putExtra("link", hotelLink); // Use the extracted link
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Invalid URL.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if (table_name.equals("foodAndDrinks")) {
                extraDetails.setVisibility(View.VISIBLE);
                TextView latitude=findViewById(R.id.latitude);
                latitude.setVisibility(View.VISIBLE);
                latitude.setText("Famous Restuarnt serving\nin the city: "+res.getString(3));
            }else if(table_name.equals("cultualeventAndHolyday")){
                extraDetails.setVisibility(View.VISIBLE);
                TextView longitude=findViewById(R.id.longitude);
                longitude.setText("Location: "+res.getString(4));
                longitude.setVisibility(View.VISIBLE);
                TextView latitude=findViewById(R.id.latitude);
                latitude.setVisibility(View.VISIBLE);
                latitude.setText("Date: "+res.getString(3));
            }



        }
        res.close(); // Always close the cursor when done


    }
    void setImageOfContent(String imageName){

        // Assuming you have the image name as a string (without the extension)
         // The name of the image in the drawable folder

    // Retrieve the resource ID dynamically
        int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());

// Check if the resource ID is valid
        if (imageResId != 0) {
            // Set the image to the ImageView
             // Replace with your ImageView ID
            imageView.setImageResource(imageResId);
        } else {
            // Handle the case where the image is not found
            Log.e("ImageView", "Image not found in drawable: " + imageName);
        }

    }






}
