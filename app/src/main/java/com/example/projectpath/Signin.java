package com.example.projectpath;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Signin extends AppCompatActivity {

    Spinner spn1;
    String gender[]= {"Male","Female"};
    ArrayAdapter<String>arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        spn1 = (Spinner)findViewById(R.id.spingender);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,gender);
        spn1.setAdapter(arrayAdapter);

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_placeown:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_travel:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    Button btncon;
    Button btnscancelS;
    Button btnconsucS;

    public void clickConS(View view){
        final Dialog dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.sign_confirm);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.setCancelable(false);
        dialog1.setCanceledOnTouchOutside(false);

        final Dialog dialog2 = new Dialog(this);
        dialog2.setContentView(R.layout.con_success_pop);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.setCancelable(false);
        dialog2.setCanceledOnTouchOutside(false);

        btncon = (Button)dialog1.findViewById(R.id.btnSignin);
        btncon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();

                btnconsucS = (Button)dialog2.findViewById(R.id.btnconsucsign);
                btnconsucS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });
                dialog2.show();
            }
        });
        btnscancelS = (Button)dialog1.findViewById(R.id.btncancels);
        btnscancelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }

    public void clickCanS(View view){
        startActivity(new Intent(Signin.this, FirstScreen.class));
    }
}
