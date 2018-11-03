package com.example.jakobdozier.powwow;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText fName;
    EditText lName;
    EditText email;
    Button register;
    FloatingActionButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fName = (EditText) findViewById(R.id.editText_fName);
        lName = (EditText) findViewById(R.id.editText_lName);
        email = (EditText) findViewById(R.id.editText_email);

        register = (Button) findViewById(R.id.button_register);

        back = (FloatingActionButton) findViewById(R.id.floatingActionButton_back);

        register.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                break;
            case R.id.floatingActionButton_back:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
