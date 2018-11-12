package com.example.jakobdozier.powwow;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileTab1 extends Fragment {

    private TextView email;
    private TextView userName;
    private TextView fName;
    private TextView lName;

    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_tab1, container, false);

        email = rootView.findViewById(R.id.textViewEmail);
        userName = rootView.findViewById(R.id.textViewUsername);
        fName = rootView.findViewById(R.id.textViewFirstName);
        lName = rootView.findViewById(R.id.textViewLastName);

        firebaseFirestore = FirebaseFirestore.getInstance();

        return rootView;
    }
}
