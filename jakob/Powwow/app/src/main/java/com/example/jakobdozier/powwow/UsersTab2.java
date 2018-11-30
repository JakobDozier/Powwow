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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
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
    String mEmail;
    long mInversionsMin;
    long mInversionsMax;
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
        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Log.d(TAG, "Current User Email: "+mEmail);

        mFirestore.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            mInversionsMax = documentSnapshot.getLong("inversions")+3;
                            mInversionsMin = documentSnapshot.getLong("inversions")-2;
                            mFirestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                    if(e != null){
                                        Log.d(TAG, "Error: "+e.getMessage());
                                    }
                                    for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){
                                        if(doc.getType() == DocumentChange.Type.ADDED){
                                            userProfile users = doc.getDocument().toObject(userProfile.class);
                                            if(!users.getEmail().equals(mEmail) && users.getInversions() <= mInversionsMax && users.getInversions() >= mInversionsMin){
                                                userList.add(users);
                                            }

                                            userListAdaptor.notifyDataSetChanged();
                                        }
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(getActivity(), "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                });


        return rootView;
    }
}
