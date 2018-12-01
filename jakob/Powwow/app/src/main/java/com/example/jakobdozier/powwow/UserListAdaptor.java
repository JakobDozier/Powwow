package com.example.jakobdozier.powwow;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserListAdaptor extends RecyclerView.Adapter<UserListAdaptor.ViewHolder> {

    public List<userProfile> userList;

    public UserListAdaptor(List<userProfile> userList){
        this.userList = userList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.firstNameText.setText(userList.get(i).getFirstName());
        viewHolder.lastNameText.setText(userList.get(i).getLastName());
        viewHolder.usernameText.setText(userList.get(i).getUsername());
        viewHolder.ageText.setText(String.valueOf(userList.get(i).getAge()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TextView firstNameText;
        public TextView lastNameText;
        public TextView usernameText;
        public TextView ageText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            firstNameText = (TextView) mView.findViewById(R.id.groupName);
            lastNameText = (TextView) mView.findViewById(R.id.lastName);
            ageText = (TextView) mView.findViewById(R.id.age);
            usernameText = (TextView) mView.findViewById(R.id.groupDescription);
        }
    }
}
