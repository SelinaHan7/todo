package com.example.sd6501assignment1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {
    EditText inputUserName, inputEmail, inputPassword;
    Button btnSignUp;
    TextView haveAnAccount;

    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inputUserName=findViewById(R.id.inputUserName);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);

        btnSignUp=findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String userName = inputUserName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                HelperClass helperClass = new HelperClass(userName, email, password);
                reference.child(userName).setValue(helperClass);

                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    //email is  empty
                    inputEmail.setError("Invalid email address");
                    inputEmail.requestFocus();

                } else if (userName.isEmpty() ||userName.length()<4 || userName.length()>12) {
                    // user name should be 4-12 letters
                    inputUserName.setError("Must enter 4-12 characters");
                    inputUserName.requestFocus();

                } else if (password.isEmpty() || password.length() < 6) {
                    // password is empty
                    inputPassword.setError("Password must be at least 6 characters long");
                    inputPassword.requestFocus();

                } else {
                    // completed registration, go to login layout
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("You have signup successfully!")
                            .setPositiveButton("OK", (dialog, id) -> {
                                // Close the dialog
                                dialog.dismiss();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            });
                    builder.create().show();

                }
            }
        });

        haveAnAccount = findViewById(R.id.haveAnAccount);
        haveAnAccount.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

    }
}