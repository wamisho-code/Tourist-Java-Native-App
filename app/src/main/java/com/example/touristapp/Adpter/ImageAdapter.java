package com.example.touristapp.Adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.touristapp.R;
import com.example.touristapp.ui.DisplayActivity;

import java.util.ArrayList;
import java.util.List;
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private AdapterView.OnItemClickListener onItemClickListener;
    private Context context;
    private String from;
    private List<Integer> imageResources;
    private List<String> nameResources;

    public ImageAdapter(Context context, List<Integer> imageResources, List<String> nameResources, String from) {
        this.context = context;
        this.from=from;
        this.nameResources=nameResources;
        this.imageResources = imageResources;
    }
    public interface OnItemClickListener {
        void onItemClick(String name, int position);
    }

    // Set the click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = (AdapterView.OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_fullscreen, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int imageResource = imageResources.get(position);
        holder.imageView.setImageResource(imageResource);
        holder.name.setText(nameResources.get(position));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context,DisplayActivity.class);
            if(from=="event"){
                if(position==0) {
                    intent.putExtra("tbname", "cultualeventAndHolyday");
                    intent.putExtra("id", "5");
                    intent.putExtra("image", "meskel");
                    intent.putExtra("title", holder.name.getText().toString());
                    intent.putExtra("commentCatagory", "meskel");
                }else if(position==2){
                    intent.putExtra("tbname","cultualeventAndHolyday");
                    intent.putExtra("id","2");
                    intent.putExtra("image","irrecha");
                    intent.putExtra("title",holder.name.getText().toString());
                    intent.putExtra("commentCatagory","irrecha");
                }else if(position==3){
                    intent.putExtra("tbname","cultualeventAndHolyday");
                    intent.putExtra("id","1");
                    intent.putExtra("image","asheda");
                    intent.putExtra("title",holder.name.getText().toString());
                    intent.putExtra("commentCatagory","ashenda");

                }else if(position==4){
                    intent.putExtra("tbname","cultualeventAndHolyday");
                    intent.putExtra("id","4");
                    intent.putExtra("image","sidama2");
                    intent.putExtra("title",holder.name.getText().toString());
                    intent.putExtra("commentCatagory","chanbelala");
                }else if(position==1){
                    intent.putExtra("tbname","cultualeventAndHolyday");
                    intent.putExtra("id","3");
                    intent.putExtra("image","timker");
                    intent.putExtra("title",holder.name.getText().toString());
                    intent.putExtra("commentCatagory","timket");
                }}
            else{
                if(position==0) {
                    intent.putExtra("tbname","cultualClothesAndJewelry");
                    intent.putExtra("title",holder.name.getText());
                    intent.putExtra("image","sidama");
                    intent.putExtra("id","1");
                    intent.putExtra("commentCatagory","sidama");
                }else if(position==3){
                    intent.putExtra("tbname","cultualClothesAndJewelry");
                    intent.putExtra("title",holder.name.getText());
                    intent.putExtra("id","4");
                    intent.putExtra("image","somale");
                    intent.putExtra("commentCatagory","somale");
                }else if(position==2){
                    intent.putExtra("tbname","cultualClothesAndJewelry");
                    intent.putExtra("title",holder.name.getText());
                    intent.putExtra("id","2");
                    intent.putExtra("image","oromia");
                    intent.putExtra("commentCatagory","oromia");

                }else if(position==1){
                    intent.putExtra("tbname","cultualClothesAndJewelry");
                    intent.putExtra("title",holder.name.getText());
                    intent.putExtra("id","3");
                    intent.putExtra("image","amhara");
                    intent.putExtra("commentCatagory","amhara");
                }
            }
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return imageResources.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.description_txt);
        }
    }
}
