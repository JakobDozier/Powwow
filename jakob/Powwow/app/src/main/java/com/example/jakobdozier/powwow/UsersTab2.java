package com.example.jakobdozier.powwow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import static android.support.constraint.Constraints.TAG;

public class UsersTab2 extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private String mCurrentUser;
    private int mCurrentUserProfile;
    private UserListAdaptor userListAdaptor;
    private List<userProfile> userList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.users_tab2, container, false);

        userList = new ArrayList<>();
        userListAdaptor = new UserListAdaptor(userList);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.user_display);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(userListAdaptor);

        mFirestore = FirebaseFirestore.getInstance();

//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .setTimestampsInSnapshotsEnabled(true)
//                .build();
//        mFirestore.setFirestoreSettings(settings);

        mFirestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "Error: "+e.getMessage());
                }
                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){
                        userProfile users = doc.getDocument().toObject(userProfile.class);
                        userList.add(users);

                        for(int i = 0; i < userList.size();i++){
                            if(userList.get(i).getInversions() < 8 || userList.get(i).getInversions() > 13 || userList.get(i).getUsername().equals("peterparker")){
                                userList.remove(i);
                            }
                        }
                        userListAdaptor.notifyDataSetChanged();
                    }
                }
            }
        });




        return rootView;
    }
}
