package com.example.jakobdozier.powwow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddInterestActivity extends AppCompatActivity implements View.OnClickListener {

    private String [] listOfActive;
    private String [] listOfGames;
    private String [] listOfMovies;
    private String [] listOfSocial;
    private String [] listOfSports;
    private String [] listOfVideogames;

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
    }

    public void submit() {
        startActivity(new Intent(this, ProfileTab1.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonActive:

                break;
            case R.id.buttonGames:

                break;
            case R.id.buttonMovies:

                break;
            case R.id.buttonSocial:

                break;
            case R.id.buttonSports:

                break;
            case R.id.buttonVideogames:

                break;
            case R.id.buttonSubmit:
                submit();
                break;
        }
    }
}
