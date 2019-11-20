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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;

    String lmail;
    String lpass;

    EditText mailet;
    EditText passet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        mailet = findViewById(R.id.lmailet);
        passet = findViewById(R.id.lpasset);

    }
    Button btnlg;

    public void clickLg(View view){
        final Dialog dialog = new Dialog(this);
        final Intent in1 = new Intent(this,MainMap.class);

        lmail = mailet.getText().toString();
        lpass = passet.getText().toString();

        mAuth.signInWithEmailAndPassword(lmail,lpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    dialog.setContentView(R.layout.activity_pop);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    btnlg = (Button)dialog.findViewById(R.id.btncon);
                    btnlg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            startActivity(in1);
                        }
                    });
                    dialog.show();
                }else{
                    Toast.makeText(getBaseContext(),"Wrong Password!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean checkInfo(){
        int c=0;
        if(lmail.isEmpty()){
            mailet.setError("Email is required");c=1;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(lmail).matches()){
            mailet.setError("Please enter valid email");c=1;
        }else{
            mailet.setError(null);
        }
        if(lpass.isEmpty()){
            passet.setError("Password is required");c=1;
        }else {
            passet.setError(null);
        }

        if(c!=0){ return false; }else{return true; }
    }
}
