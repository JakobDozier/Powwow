package com.example.jakobdozier.powwow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddInterestActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> listOfActive = new ArrayList<String>();
    private ArrayList<String> listOfGames = new ArrayList<String>();
    private ArrayList<String> listOfMovies = new ArrayList<String>();
    private ArrayList<String> listOfSocial = new ArrayList<String>();
    private ArrayList<String> listOfSports = new ArrayList<String>();
    private ArrayList<String> listOfVideogames = new ArrayList<String>();
    ArrayList<Integer> totals = new ArrayList<Integer>();

    private ArrayList<String> currActive = new ArrayList<String>();
    private ArrayList<String> currGames = new ArrayList<String>();
    private ArrayList<String> currMovies = new ArrayList<String>();
    private ArrayList<String> currSocial = new ArrayList<String>();
    private ArrayList<String> currSports = new ArrayList<String>();
    private ArrayList<String> currVideogames = new ArrayList<String>();

    private EditText active;
    private EditText games;
    private EditText movies;
    private EditText social;
    private EditText sports;
    private EditText videogames;

    private Button buttonActive;
    private Button buttonGames;
    private Button buttonMovies;
    private Button buttonSocial;
    private Button buttonSports;
    private Button buttonVideogames;

    private Button buttonSubmit;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_interest);

        active = findViewById(R.id.editTextActive);
        games = findViewById(R.id.editTextGames);
        movies = findViewById(R.id.editTextMovies);
        social = findViewById(R.id.editTextSocial);
        sports = findViewById(R.id.editTextSports);
        videogames = findViewById(R.id.editTextVideogames);

        buttonActive = findViewById(R.id.buttonActive);
        buttonGames = findViewById(R.id.buttonGames);
        buttonMovies = findViewById(R.id.buttonMovies);
        buttonSocial = findViewById(R.id.buttonSocial);
        buttonSports = findViewById(R.id.buttonSports);
        buttonVideogames = findViewById(R.id.buttonVideogames);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        firebaseFirestore = FirebaseFirestore.getInstance();

        buttonActive.setOnClickListener(this);
        buttonGames.setOnClickListener(this);
        buttonMovies.setOnClickListener(this);
        buttonSocial.setOnClickListener(this);
        buttonSports.setOnClickListener(this);
        buttonVideogames.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
    }

    public void submit() {

        firebaseFirestore.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
//                            if (documentSnapshot.get("Active") != null){
//                                currActive = (ArrayList<String>) documentSnapshot.get("Active");
//                            }
//                            if (documentSnapshot.get("Games") != null){
//                                currActive = (ArrayList<String>) documentSnapshot.get("Games");
//                            }
//                            if (documentSnapshot.get("Movies") != null){
//                                currActive = (ArrayList<String>) documentSnapshot.get("Movies");
//                            }
//                            if (documentSnapshot.get("Social") != null){
//                                currActive = (ArrayList<String>) documentSnapshot.get("Social");
//                            }
//                            if (documentSnapshot.get("Sports") != null){
//                                currActive = (ArrayList<String>) documentSnapshot.get("Sports");
//                            }
//                            if (documentSnapshot.get("Videogames") != null){
//                                currActive = (ArrayList<String>) documentSnapshot.get("Videogames");
//                            }
//                            if (!currActive.isEmpty()){
//                                listOfActive.addAll(currActive);
//                            }
//                            if (!currGames.isEmpty()){
//                                listOfGames.addAll(currGames);
//                            }
//                            if (!currMovies.isEmpty()){
//                                listOfMovies.addAll(currMovies);
//                            }
//                            if (!currSocial.isEmpty()){
//                                listOfSocial.addAll(currSocial);
//                            }
//                            if (!currSports.isEmpty()){
//                                listOfSports.addAll(currSports);
//                            }
//                            if (!currVideogames.isEmpty()){
//                                listOfVideogames.addAll(currVideogames);
//                            }
                            totals.add(listOfActive.size());
                            totals.add(listOfGames.size());
                            totals.add(listOfMovies.size());
                            totals.add(listOfSocial.size());
                            totals.add(listOfSports.size());
                            totals.add(listOfVideogames.size());

                            Map<String, ArrayList> mUserInterests = new HashMap<>();
                            mUserInterests.put("Active", listOfActive);
                            mUserInterests.put("Games", listOfGames);
                            mUserInterests.put("Movies", listOfMovies);
                            mUserInterests.put("Social", listOfSocial);
                            mUserInterests.put("Sports", listOfSports);
                            mUserInterests.put("Videogames", listOfVideogames);
                            mUserInterests.put("Totals", totals);

                            for (int i = 0; i < listOfActive.size();i++){
                                firebaseFirestore.collection("interest").document(mAuth.getCurrentUser().getUid()).update("Active",FieldValue.arrayUnion(listOfActive.get(i)));
                            }

                            //add inversions to profile
                            getInversions(totals);
                        }

                    }
                });

        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonActive:
                    listOfActive.add(active.getText().toString());
                break;
            case R.id.buttonGames:
                    listOfGames.add(games.getText().toString());
                break;
            case R.id.buttonMovies:
                    listOfMovies.add(movies.getText().toString());
                break;
            case R.id.buttonSocial:
                    listOfSocial.add(social.getText().toString());
                break;
            case R.id.buttonSports:
                    listOfSports.add(sports.getText().toString());
                break;
            case R.id.buttonVideogames:
                    listOfVideogames.add(videogames.getText().toString());
                break;
            case R.id.buttonSubmit:
                submit();
                break;
        }
    }

    public void getInversions(ArrayList<Integer> arr){
        int inv_count = 0;
        for (int i = 0; i < arr.size() - 1; i++)
            for (int j = i + 1; j < arr.size(); j++)
                if (arr.get(i) < arr.get(j))
                    inv_count++;

        firebaseFirestore.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).update("inversions", inv_count);
    }
}
