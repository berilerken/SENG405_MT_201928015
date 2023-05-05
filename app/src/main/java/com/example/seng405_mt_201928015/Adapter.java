package com.example.seng405_mt_201928015;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

//Verilerin görüntülenmesi için oluşturduğum bir RecyclerView'dan extend edilen bir adapter sınıfıdır.
//DataClass sınıfından gelen verileri liste olarak tutar ve her bir öğe için görünüm oluşturur.
public class Adapter extends RecyclerView.Adapter<Adapter.UserViewHolder> {
    private Context context;
    private List<DataClass> userList;

    public Adapter(Context context, List<DataClass> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        DataClass user = userList.get(position);
        holder.nameSurnameTextView.setText(user.getFirstName() + " " + user.getLastName());
        holder.emailTextView.setText(user.getEmail());
        Glide.with(context).load(user.getImage()).into(holder.userImageView);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView nameSurnameTextView;
        private TextView emailTextView;
        private ImageView userImageView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSurnameTextView = itemView.findViewById(R.id.name_surname_textview);
            emailTextView = itemView.findViewById(R.id.email_textview);
            userImageView = itemView.findViewById(R.id.user_imageview);
        }
    }
}

