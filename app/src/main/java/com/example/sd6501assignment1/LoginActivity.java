package com.example.sd6501assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName, edtPassword;
    Button btnLogin;
    TextView txtForgotPassword,txtNewAccount;
    ProgressBar progressBar;
    int counter = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MainActivity", "Debug One");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        //go to register
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        //go to sign up
        txtNewAccount = findViewById(R.id.txtNewAccount);
        txtNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //go to login process
        Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (username.isEmpty()) {
                    //no user name
                    edtUserName.setError("Username is required");
                    edtUserName.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    //no password
                    edtPassword.setError("Password is required");
                    edtPassword.requestFocus();
                    return;
                }
                if (username.equals("Selina") && password.equals("Selina")) {
                    // Correct username and password, show the progress bar and navigate to the SplashActivity
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(intent);
                    finish();
                }
                else {//check user
                    checkUser();
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(intent);

                }

            }
            public void checkUser(){

                String loginUsername = edtUserName.getText().toString().trim();
                String loginPassword = edtPassword.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUserDatabase =reference.orderByChild("username").equalTo(loginUsername);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            edtUserName.setError(null);
                            String passwordFromDB = snapshot.child(loginUsername).child("password").getValue(String.class);

                            if (passwordFromDB.equals(loginPassword)){
                                edtUserName.setError(null);
                                //Pass the data to profile
                                String nameFromDB=snapshot.child(loginUsername).child("name").getValue(String.class);
                                String emailFromDB=snapshot.child(loginUsername).child("email").getValue(String.class);

                                Intent intent = new Intent (LoginActivity.this, ProfileFragment.class);

                                intent.putExtra("name", nameFromDB);
                                intent.putExtra("email", emailFromDB);
                                intent.putExtra("password", passwordFromDB);

                                startActivity(intent);
                            }else{
                                edtPassword.setError("Invalid Credentials");
                                edtPassword.requestFocus();
                            }
                        }
                            else {
                                edtUserName.setError("User does not exist");
                                edtUserName.requestFocus();
                            }
                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //run progress bar, after 100% running, it will be gone
                counter++;
                progressBar.setProgress(counter);
                if (counter ==100){
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 100, 100);


    }

}