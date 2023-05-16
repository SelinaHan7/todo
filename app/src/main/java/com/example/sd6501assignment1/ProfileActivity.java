package com.example.sd6501assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
   // String TAG= "ProfileActivity";

    TextView profileName, profileEmail,profilePassword;
    private View editProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profilePassword = findViewById(R.id.profilePassword);
        //init();

        showUserData();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }

        });
    }
//    public void init(){
//        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));
//        ProfileFragment fragment = new ProfileFragment();
//        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, fragment);
//        transaction.addToBackStack(getString(R.string.profile_fragment));
//        transaction.commit();
//    }
    public void showUserData(){
        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        String userEmail = intent.getStringExtra("email");
        String userPassword = intent.getStringExtra("password");

        profileName.setText(userName);
        profileEmail.setText(userEmail);
        profilePassword.setText(userPassword);
    }

    public void passUserData(){
        String edtUsername= profileName.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query  checkUserDatabase = reference.orderByChild("username").equalTo(edtUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nameFromDB = snapshot.child(edtUsername).child("name").getValue(String.class);
                    String emailFromDB = snapshot.child(edtUsername).child("email").getValue(String.class);
                    String passwordFromDB = snapshot.child(edtUsername).child("password").getValue(String.class);

                    Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);

                    intent.putExtra("name", nameFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("password", passwordFromDB);

                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}