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
import android.widget.Toast;

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
                reference.child(userName).setValue(helperClass);if (userName != null && email != null && password != null) {
                    if (userName.isBlank() || userName.length() < 4 || userName.length() > 12) {
                        // User name should be 4-12 characters
                        inputUserName.setError("Must enter 4-12 characters");
                        inputUserName.requestFocus();
                    } else if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        // Invalid email address
                        inputEmail.setError("Invalid email address");
                        inputEmail.requestFocus();
                    } else if (password.isBlank() || password.length() < 6) {
                        // Password must be at least 6 characters long
                        inputPassword.setError("Password must be at least 6 characters long");
                        inputPassword.requestFocus();
                    } else {
                        // Completed registration, go to login layout
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("You have signed up successfully!")
                                .setPositiveButton("OK", (dialog, id) -> {
                                    // Close the dialog
                                    dialog.dismiss();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                })
                                .setNegativeButton("Cancel", (dialog, id) -> {
                                    dialog.dismiss();
                                    // Return to the previous page
                                    // ...
                                });
                        builder.create().show();
                    }
                } else {
                    // Information must be filled
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Information must be filled")
                            .setPositiveButton("OK", (dialog, id) -> {
                                dialog.dismiss();
                            });
                    builder.create().show();
                }

                haveAnAccount = findViewById(R.id.haveAnAccount);
                haveAnAccount.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

   }
        });
    }
}