package com.example.jakobdozier.powwow;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GroupListAdaptor extends RecyclerView.Adapter<GroupListAdaptor.ViewHolder> {

    public List<GroupProfile> groupList;

    public GroupListAdaptor(List<GroupProfile> groupList){
        this.groupList = groupList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.groupNameText.setText(groupList.get(i).getGroupName());
        viewHolder.groupDescriptionText.setText(groupList.get(i).getGroupDescription());
        viewHolder.membersText.setText(String.valueOf(groupList.get(i).getMembers()));
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TextView groupNameText;
        public TextView membersText;
        public TextView groupDescriptionText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            groupNameText = (TextView) mView.findViewById(R.id.groupName);
            membersText = (TextView) mView.findViewById(R.id.members);
            groupDescriptionText = (TextView) mView.findViewById(R.id.groupDescription);
        }
    }
}