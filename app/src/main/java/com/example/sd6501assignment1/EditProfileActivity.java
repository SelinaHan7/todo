package com.example.sd6501assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {
    EditText editUserName, editEmail, editPassword;
    Button btnEditProfile, btnCancelEdit;
    String nameUser, emailUser,passwordUser;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        
        reference = FirebaseDatabase.getInstance().getReference();
        editUserName = findViewById(R.id.edit_UserName);
        editEmail = findViewById(R.id.edit_Email);
        editPassword = findViewById(R.id.edit_Password);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnCancelEdit = findViewById(R.id.btnCancelEdit);
        showData();

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNameChanged() || isEmailChanged() ||isPasswordChanged()){

                    Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                 }else{
                    Toast.makeText(EditProfileActivity.this, "No changes!", Toast.LENGTH_SHORT).show();
                    }
                 }
            });
        btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
                }
            });
        }

    private void showData() {
        Intent intent = getIntent();
        nameUser= intent.getStringExtra("name");
        emailUser= intent.getStringExtra("email");
        passwordUser= intent.getStringExtra("password");

        editUserName.setText(nameUser);
        editEmail.setText(emailUser);
        editPassword.setText(passwordUser);
    }

    public  boolean isNameChanged(){
        if(!nameUser.equals(editUserName.getText().toString())){
            reference.child(nameUser).child("name").setValue(editUserName.getText().toString());

            nameUser = editUserName.getText().toString();
            return true;
           }else{
            return false;
           }
       }

    public  boolean isEmailChanged(){
        if(!emailUser.equals(editEmail.getText().toString())){
            reference.child(emailUser).child("name").setValue(editEmail.getText().toString());

            passwordUser = editEmail.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public  boolean isPasswordChanged(){
        if(!passwordUser.equals(editPassword.getText().toString())){
            reference.child(passwordUser).child("name").setValue(editPassword.getText().toString());
            //child nameUser?????
            passwordUser = editPassword.getText().toString();
            return true;
        }else{
            return false;
        }
    }

}