package com.example.projectpath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference myRef;

    Spinner spn1;
    String gender[]= {"Male","Female"};
    ArrayAdapter<String>arrayAdapter;

    String umail;
    String upass;
    String ucpass;
    String ugender;
    String utype;

    EditText mailet;
    EditText passet;
    EditText cpasset;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();

        mailet = (EditText)findViewById(R.id.usernameSET);
        passet = (EditText)findViewById(R.id.passSET);
        cpasset = (EditText)findViewById(R.id.cpassSET);

        spn1 = (Spinner)findViewById(R.id.spingender);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,gender);
        spn1.setAdapter(arrayAdapter);

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ugender = gender[i].toLowerCase();
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
                if (checked) utype = "owner";
                break;
            case R.id.radio_travel:
                if (checked) utype = "traveler";
                break;
            default:
                break;
        }
    }

    Button btncon;
    Button btnscancelS;
    Button btnconsucS;

    public void clickConS(View view){
        final Intent ifs = new Intent(this,FirstScreen.class);

        if(checkInfo()){
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

                    //submit
                    signIn();

                    btnconsucS = (Button)dialog2.findViewById(R.id.btnconsucsign);
                    btnconsucS.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog2.dismiss();
                            startActivity(ifs);
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

    }

    public void clickCanS(View view){
        startActivity(new Intent(SignUp.this, FirstScreen.class));
    }

    public void signIn(){
        mAuth.createUserWithEmailAndPassword(umail,upass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mAuth.signInWithEmailAndPassword(umail,upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                uid = user.getUid();
                                //Toast.makeText(getBaseContext(),"Uid is : "+uid,Toast.LENGTH_SHORT).show();
                                myRef.child("user").child(uid).child("gender").setValue(ugender);
                                myRef.child("user").child(uid).child("user-type").setValue(utype);
                            }else{
                                Toast.makeText(getBaseContext(),"Fail to Create Data",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(getBaseContext(),"Fail to Create Account",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkInfo(){

        CheckBox chkb = (CheckBox)findViewById(R.id.chkTerm);
        TextView ctve = (TextView)findViewById(R.id.checkboxError);

        umail = mailet.getText().toString();
        upass = passet.getText().toString();
        ucpass = cpasset.getText().toString();

        int c = 0;
        if(umail.isEmpty()){
            mailet.setError("Username is required");c = 1;
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(umail).matches()){
                mailet.setError("Please enter valid Email!!");c = 1;
            }else {
                mailet.setError(null);
            }
        }
        if(upass.isEmpty()||ucpass.isEmpty()){
            if(upass.isEmpty()){passet.setError("Password is required");c = 1;}
            if(ucpass.isEmpty()){cpasset.setError("Password is required");c = 1;}
        }else {
            if(!upass.equalsIgnoreCase(ucpass)){
                passet.setError("Password dose not match");c=1;
            }else{
                passet.setError(null);cpasset.setError(null);
            }
        }

        if(!chkb.isChecked()){
            ctve.setError("Please confirm Term & Conditions");c=1;
        }else {ctve.setError(null);}

        if(c==0){
            return true;
        }else {
            return false;
        }

    }
}
