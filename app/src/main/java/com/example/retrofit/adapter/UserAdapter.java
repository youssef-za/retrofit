package com.example.retrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.R;

import com.example.retrofit.model.User;
import java.util.List;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private List<User> filteredList = new ArrayList<>();
    public void setUserList(List<com.example.retrofit.model.User> users) {
        this.userList = users;
        this.filteredList = new ArrayList<>(users); // Initialiser la liste filtrée
        notifyDataSetChanged();
    }
    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(userList);
        } else {
            for (User user : userList) {
                if (user.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(user);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone, city;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userName);
            email = itemView.findViewById(R.id.userEmail);
            phone = itemView.findViewById(R.id.userPhone);
            city = itemView.findViewById(R.id.userCity);
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = filteredList.get(position);
        holder.name.setText("Nom : " + user.getName());
        holder.email.setText("Email : " + user.getEmail());
        holder.phone.setText("Téléphone : " + user.getPhone());
        holder.city.setText("Ville : " + user.getCity());
    }

    @Override
    public int getItemCount() {
        return (filteredList == null) ? 0 : filteredList.size();
    }
}