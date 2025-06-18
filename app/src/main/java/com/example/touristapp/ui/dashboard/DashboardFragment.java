package com.example.touristapp.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.touristapp.Adpter.CommentAdapter;
import com.example.touristapp.Adpter.TourGuideAdapter;
import com.example.touristapp.Classfile.Share;
import com.example.touristapp.Classfile.TourGuide;
import com.example.touristapp.R;

import com.example.touristapp.databinding.FragmentDashboardBinding;
import com.example.touristapp.ui.DisplayActivity;
import com.example.touristapp.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ArrayList<TourGuide> tourGuideList;
    private  RecyclerView guideLoader;
    private LinearLayout loadingBar;

    private TourGuideAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        guideLoader =root.findViewById(R.id.tourist_guide_loader);
        // Initialize the list and adapter
        getGuide();
        tourGuideList = new ArrayList<>();
        loadingBar=root.findViewById(R.id.guideLoadingBar);
        adapter = new TourGuideAdapter(root.getContext(),tourGuideList);
        guideLoader.setLayoutManager(new LinearLayoutManager(root.getContext()));
        guideLoader.setAdapter(adapter);
      //  adapter = new TourGuideAdapter(tourGuideList);
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.recyclerView.setAdapter(adapter);



        return root;
    }

    void getGuide() {
        DatabaseReference userReactionRef = FirebaseDatabase.getInstance().getReference("TourGuides");

        userReactionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tourGuideList.clear(); // Clear the list to avoid duplication
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TourGuide guide = snapshot.getValue(TourGuide.class);
                    if (guide != null) {
                        tourGuideList.add(guide);

                    }
                }
                loadingBar.setVisibility(View.GONE);
                // Notify your adapter or update your UI with the new data
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error: " + databaseError.getMessage());
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
