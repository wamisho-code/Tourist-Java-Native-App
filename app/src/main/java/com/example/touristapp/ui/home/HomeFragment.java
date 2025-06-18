package com.example.touristapp.ui.home;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.touristapp.Adpter.ImageAdapter;
import com.example.touristapp.MainActivity;
import com.example.touristapp.R;
import com.example.touristapp.databinding.FragmentHomeBinding;
import com.example.touristapp.ui.ClothesAndJewelryActivity;
import com.example.touristapp.ui.CulturalEventAndHolidayActivity;
import com.example.touristapp.ui.DisplayActivity;
import com.example.touristapp.ui.FoodandDrinksActivity;
import com.example.touristapp.ui.NaturalandHistoriicalActivity;
import com.example.touristapp.ui.PersonalAccountActivity;
import com.example.touristapp.ui.SigninActivity;
import com.example.touristapp.ui.VactionSiteActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;

import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

public class HomeFragment extends Fragment {
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;
    private android.os.Handler handler;
    private Runnable runnable;
   // private LinearLayout food;
    private ImageView account;
    private TextView meskel_txt,timket_txt,ashenda_txt,irrecha_txt,chanbelala_txt;
    FirebaseAuth auth;
    private CardView kitfo,shiro,doro,firfir,beyaynet,bursame;
    private TextView kitfo_txt,doro_txt,firfir_txt,shiro_txt,beyaynet_txt,bursame_txt;

    private  CardView vactionSite,naturalAndHistorical,culuralEventandHoliday, culturalClothesandJewlery;
    private int scrollSpeed = 3;
    ArrayList<String> data;
    private CardView meskel,irrecha,timket,ashenda,chanbelala;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        data=new ArrayList<>();
        String imageUrl=getActivity().getIntent().getStringExtra("imageurl");
        auth=FirebaseAuth.getInstance();

        account=binding.account;



        account.setOnClickListener(v -> {
            signOut();
            getActivity().finish();

        });
        naturalAndHistorical=binding.nh;
        //culturalClothesandJewlery=binding.Clothesandjewelary;
        //culuralEventandHoliday=binding.event;


        Animation moveRight = AnimationUtils.loadAnimation(getContext(), R.anim.move_right);
        naturalAndHistorical.startAnimation(moveRight);
        naturalAndHistorical.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), NaturalandHistoriicalActivity.class));
        });
        vactionSite=binding.vacationSite;
        Animation moveLeft = AnimationUtils.loadAnimation(getContext(), R.anim.move_left);
        vactionSite.startAnimation(moveLeft);
        vactionSite.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), VactionSiteActivity.class));
        });
        naturalAndHistorical.setOnClickListener(v -> {
            System.out.printf("ggg");
            startActivity(new Intent(getContext(), NaturalandHistoriicalActivity.class));
        });
//        food=binding.foodbutton;
//        food.setOnClickListener(v -> {
//            startActivity(new Intent(getContext(), FoodandDrinksActivity.class));
//        });

//        culturalClothesandJewlery.setOnClickListener(v -> {
//            startActivity(new Intent(getContext(), ClothesAndJewelryActivity.class));
//        });
//        culuralEventandHoliday.setOnClickListener(v -> {
//            startActivity(new Intent(getContext(),CulturalEventAndHolidayActivity.class));
//        });

        kitfo=root.findViewById(R.id.kitfo);
        shiro=root.findViewById(R.id.shiro);
        doro=root.findViewById(R.id.dorowet);
        firfir=root.findViewById(R.id.firfir);
        beyaynet=root.findViewById(R.id.beyaynet);
        bursame=root.findViewById(R.id.bursame);

        kitfo_txt=root.findViewById(R.id.kitfo_txt);
        shiro_txt=root.findViewById(R.id.shiro_txt);
        doro_txt=root.findViewById(R.id.doro_txt);
        firfir_txt=root.findViewById(R.id.firfir_txt);
        beyaynet_txt=root.findViewById(R.id.beyanet_txt);
        bursame_txt=root.findViewById(R.id.burshame_txt);

        kitfo.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DisplayActivity.class);
            intent.putExtra("tbname","foodAndDrinks");
            intent.putExtra("id","5");
            intent.putExtra("image","kitfo");
            intent.putExtra("title","Kitfo");
            intent.putExtra("commentCatagory","kitfo");
            startActivity(intent);
        });
        shiro.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(),DisplayActivity.class);
            intent.putExtra("tbname","foodAndDrinks");
            intent.putExtra("id","1");
            intent.putExtra("image","shiro");
            intent.putExtra("title","Shiro");
            intent.putExtra("commentCatagory","shiro");
            startActivity(intent);
        });
        doro.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(),DisplayActivity.class);
            intent.putExtra("tbname","foodAndDrinks");
            intent.putExtra("id","2");
            intent.putExtra("image","dorowet");
            intent.putExtra("title","Dorowet");
            intent.putExtra("commentCatagory","dorowet");
            startActivity(intent);
        });
        firfir.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(),DisplayActivity.class);
            intent.putExtra("tbname","foodAndDrinks");
            intent.putExtra("id","3");
            intent.putExtra("image","firfir");
            intent.putExtra("title","Firfir");
            intent.putExtra("commentCatagory","firfir");
            startActivity(intent);
        });
        beyaynet.setOnClickListener(v->{
            Intent intent = new Intent(getContext(),DisplayActivity.class);
            intent.putExtra("tbname","foodAndDrinks");
            intent.putExtra("id","6");
            intent.putExtra("image","beyanet");
            intent.putExtra("title","Beyaynet");
            intent.putExtra("commentCatagory","beyaynet");
            startActivity(intent);

        });
        bursame.setOnClickListener(v->{
            Intent intent = new Intent(getContext(),DisplayActivity.class);
            intent.putExtra("tbname","foodAndDrinks");
            intent.putExtra("id","4");
            intent.putExtra("image","bursame");
            intent.putExtra("title","Bursame");
            intent.putExtra("commentCatagory","bursame");
            startActivity(intent);

        });


        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

// Set up adapter with image resources
        List<Integer> imageResources = Arrays.asList(
                R.mipmap.sidama,
                R.drawable.amhara,
                R.mipmap.oromia,
                R.drawable.somale
        );
        List<String> nameResources = Arrays.asList(
                "Kolo",
                "Habesha Kemis",
                "Oromia",
                "Somale"
        );

        ImageAdapter adapter = new ImageAdapter(getContext(), imageResources,nameResources,"m");

        recyclerView.setAdapter(adapter);

// Similarly for recyclerView2
        RecyclerView recyclerView2 = root.findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        List<String> nameResources2 = Arrays.asList(
                "Meskel",
                "Timket",
                "Irrecha",
                "Ashenda",
                "Chanbelala"
        );
        List<Integer> imageResources2 = Arrays.asList(
                R.mipmap.meskel,
                R.drawable.timker,
                R.mipmap.irrecha,
                R.drawable.asheda,
                R.mipmap.sidama5
        );

        ImageAdapter adapter2 = new ImageAdapter(getContext(), imageResources2,nameResources2,"event");
        recyclerView2.setAdapter(adapter2);
        meskel=root.findViewById(R.id.meskel);
        irrecha=root.findViewById(R.id.irrecha);
        ashenda=root.findViewById(R.id.ashenda);
        chanbelala=root.findViewById(R.id.chenbelala);
        timket=root.findViewById(R.id.timket);




        String tableName="cultualeventAndHolyday";


        return root;
    }
    private  void signOut(){
        auth.signOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        googleSignInClient.signOut().addOnCompleteListener(task -> {
            Toast.makeText(getContext(), "Signed out successfully", Toast.LENGTH_SHORT).show();

        });
        startActivity(new Intent(getContext(), SigninActivity.class));


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}





