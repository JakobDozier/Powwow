package com.example.jakobdozier.powwow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import static android.support.constraint.Constraints.TAG;

public class GroupsTab3 extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseFirestore mFirestore;
    private GroupListAdaptor groupListAdaptor;
    private List<GroupProfile> groupList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.groups_tab3, container, false);

        groupList = new ArrayList<>();
        groupListAdaptor = new GroupListAdaptor(groupList);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.GroupFeed);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(groupListAdaptor);

        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("groups").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    Log.d(TAG, "Error: "+e.getMessage());
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){
                    if (doc.getType() == DocumentChange.Type.ADDED){
                        GroupProfile group = doc.getDocument().toObject(GroupProfile.class);
                        groupList.add(group);

                        groupListAdaptor.notifyDataSetChanged();
                    }
                }
            }
        });

        return rootView;
    }
}
