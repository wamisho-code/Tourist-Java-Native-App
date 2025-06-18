package com.example.touristapp.Adpter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.touristapp.Classfile.TourGuide;
import com.example.touristapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TourGuideAdapter extends RecyclerView.Adapter<TourGuideAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TourGuide> tourGuideList;


    // Constructor
    public TourGuideAdapter(Context context, ArrayList<TourGuide> tourGuideList) {
        this.context = context;

        this.tourGuideList = tourGuideList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tour_guide, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TourGuide tourGuide = tourGuideList.get(position);

        // Reset views to their default state
        holder.nameTextView.setText("");
        holder.locationTextView.setText("Location:");
        holder.language.setText("Language:");
        holder.rating.setText("");
        holder.phone.setText("");

        // Set data to views
        holder.nameTextView.setText(tourGuide.getFirst_name() + " " + tourGuide.getLast_name());
        holder.locationTextView.setText(holder.locationTextView.getText() + " " + tourGuide.getLocation());
        holder.phone.setText(tourGuide.getPhone());
        holder.rating.setText(String.valueOf(tourGuide.getRating()));
        holder.language.setText(holder.language.getText() + " " + tourGuide.getLanguage());

        // Load image using Glide
        if (tourGuide.getImagruri() != null && !tourGuide.getImagruri().toString().isEmpty()) {
            Glide.with(context).load(tourGuide.getImagruri()).into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.person_icon); // Fallback image
        }

        // Setup the click listener for each item
        holder.itemView.setOnClickListener(v -> {
            holder.showRatingDialog(tourGuide);
        });
        holder.result_Dialog.findViewById(R.id.sendMail).setOnClickListener(v -> {
            String subject=((TextView) holder.result_Dialog.findViewById(R.id.mailSubject)).getText().toString();
            String body=((TextView) holder.result_Dialog.findViewById(R.id.mailBody)).getText().toString();
            if(subject.isEmpty() || body.isEmpty())
                Toast.makeText(context, "Fill all information", Toast.LENGTH_SHORT).show();
            else{
                sendEmail(subject,body,tourGuide.getEmail(),holder.result_Dialog.findViewById(R.id.mail_status_txt));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tourGuideList.size();
    }
    public void sendEmail(String subject, String body, String email, TextView result) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822"); // Restrict to email apps
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, locationTextView, language, rating, phone, dialogName;
        ImageView imageView, dialogProfile;
        Dialog result_Dialog;
        RelativeLayout guideCard;
        Button submitRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.guide_name);
            locationTextView = itemView.findViewById(R.id.guide_location);
            language = itemView.findViewById(R.id.guide_language);
            rating = itemView.findViewById(R.id.guide_rating);
            phone = itemView.findViewById(R.id.guide_phone);
            imageView = itemView.findViewById(R.id.guide_profile);

            // Pre-initialize the dialog
            result_Dialog = new Dialog(itemView.getContext());
            result_Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            result_Dialog.setContentView(R.layout.rating_layout);
        }

        public void showRatingDialog(TourGuide tourGuide) {
            RatingBar ratingBar = result_Dialog.findViewById(R.id.ratingBar_star);
            if (ratingBar == null) {
                Toast.makeText(itemView.getContext(), "RatingBar is null", Toast.LENGTH_SHORT).show();
                return;
            }

            final float[] rateValue = new float[1];
            try {
                rateValue[0] = tourGuide.getRating();
            } catch (NumberFormatException e) {
                Toast.makeText(itemView.getContext(), "Invalid rating value", Toast.LENGTH_SHORT).show();
                return;
            }

            // Set dialog profile image
            ImageView dialogProfile = result_Dialog.findViewById(R.id.guide_profile_star);
            Drawable imageDrawable = imageView.getDrawable();
            dialogProfile.setImageDrawable(imageDrawable);

            // Set dialog name text
            TextView dialogName = result_Dialog.findViewById(R.id.guide_name_star);
            dialogName.setText(tourGuide.getFirst_name() + " " + tourGuide.getLast_name());
            submitRating = result_Dialog.findViewById(R.id.submitRating);

            ratingBar.setOnRatingBarChangeListener((ratingBar1, rate, fromUser) -> {
                try {
                    rateValue[0] = (tourGuide.getRating() + rate) / 2;
                    rating.setText(String.valueOf(rateValue[0]));
                } catch (NumberFormatException e) {
                    Toast.makeText(itemView.getContext(), "Invalid rating value", Toast.LENGTH_SHORT).show();
                }
            });

            submitRating.setOnClickListener(v1 -> {
                publishRating(rateValue[0]);
                result_Dialog.dismiss();
            });

            // Show the dialog
            result_Dialog.show();
        }

        private void publishRating(float newRating) {
            String firstname = nameTextView.getText().toString().split(" ")[0];
            String phoneNumber = phone.getText().toString();

            // Query Firebase to find the correct node
            DatabaseReference tourGuidesRef = FirebaseDatabase.getInstance().getReference("TourGuides");
            tourGuidesRef.orderByChild("phone").equalTo(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TourGuide tourGuide = snapshot.getValue(TourGuide.class);
                        if (tourGuide != null && tourGuide.getFirst_name().contains(firstname)) {
                            // Update the rating value as a double
                            snapshot.getRef().child("rating").setValue((double) newRating)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                           // Toast.makeText(itemView.getContext(), "Rating updated successfully", Toast.LENGTH_SHORT).show();
                                            rating.setText(String.valueOf(newRating));
                                        } else {
                                            Toast.makeText(itemView.getContext(), "Failed to send rating", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(itemView.getContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
