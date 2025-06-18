package com.example.touristapp.Adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.touristapp.Classfile.Share;
import com.example.touristapp.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>{
    private ArrayList<Share> userReactions ;
    private  Context context;
    public CommentAdapter(ArrayList<Share> userReactions, Context context){
        this.userReactions=userReactions;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_layout, parent, false);
        return new CommentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                holder.userName.setText(userReactions.get(position).getUserName());
                holder.comment.setText(userReactions.get(position).getComment());
                holder.loaction.setText("From " +userReactions.get(position).getLocation());
                holder.date.setText(userReactions.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return userReactions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userName,comment,date,loaction;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.userName);
            date=itemView.findViewById(R.id.date);
            loaction=itemView.findViewById(R.id.commenter_location);
            comment=itemView.findViewById(R.id.commentSection);
        }
    }
}
