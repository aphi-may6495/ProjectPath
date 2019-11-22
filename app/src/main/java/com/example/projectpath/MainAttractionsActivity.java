package com.example.projectpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainAttractionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_attractions);
    }

    public void toStore11(View view){
        startActivity(new Intent(this, Store1.class));
    }
    public void toStore22(View view){
        startActivity(new Intent(this, Store2.class));
    }
}
