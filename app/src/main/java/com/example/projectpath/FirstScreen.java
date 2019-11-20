package com.example.projectpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

    }

    public void clickSignin (View view){
        startActivity(new Intent(FirstScreen.this, Login.class));
    }
    public void clickCreate (View view){
        startActivity(new Intent(FirstScreen.this, SignUp.class));
    }
}
