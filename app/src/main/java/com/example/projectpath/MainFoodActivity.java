package com.example.projectpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food);
    }
    public void toFood1(View view){
        startActivity(new Intent(this, Food1.class));
    }
    public void toFood2(View view){
        startActivity(new Intent(this, Food2.class));
    }
    public void toFood3(View view){
        startActivity(new Intent(this, Food3.class));
    }

}
