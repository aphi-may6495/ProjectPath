package com.example.projectpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signIn (View view){
        startActivity(new Intent(MainActivity.this, Login.class));
    }

    public void signUp (View view){
        startActivity(new Intent(MainActivity.this, SignUp.class));
    }
}
