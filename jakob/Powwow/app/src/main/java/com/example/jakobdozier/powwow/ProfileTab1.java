package com.example.jakobdozier.powwow;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class ProfileTab1 extends Fragment {

    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FIRSTNAME = "fistName";
    private static final String KEY_LASTNAME = "lastName";

    private TextView userName;
    private TextView email;
    private TextView fName;
    private TextView lName;

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_tab1, container, false);

        userName = rootView.findViewById(R.id.textViewUsername);
        email = rootView.findViewById(R.id.textViewEmail);
        fName = rootView.findViewById(R.id.textViewFirstName);
        lName = rootView.findViewById(R.id.textViewLastName);

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);

        firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String userNameData = documentSnapshot.getString(KEY_USERNAME);
                            String emailData = documentSnapshot.getString(KEY_EMAIL);
                            String fNameData = documentSnapshot.getString(KEY_FIRSTNAME);
                            String lNameData = documentSnapshot.getString(KEY_LASTNAME);

                            userName.setText("Username: " + userNameData);
                            email.setText("Email: " + emailData);
                            fName.setText("First Name: " + fNameData);
                            lName.setText("Last Name: " + lNameData);
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
