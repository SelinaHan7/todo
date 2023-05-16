package com.example.sd6501assignment1;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView imageLogo;
    TextView txtWelcome;
    ProgressBar progressBar;
    int counter = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MainActivity", "Debug Two");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageLogo=findViewById(R.id.imgLogo);
        imageLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar = findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);

                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        //run progress bar, after 100% running, it will be gone
                        counter++;
                        Log.e("progressbar", String.valueOf(counter));//TESTING
                        progressBar.setProgress(counter);
                        if (counter ==3){
                            timer.cancel();
                            progressBar.setVisibility(View.INVISIBLE);
                            MainActivity.this.startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                    };
                    timer.schedule(timerTask, 100, 100);

            }
        });//start the app, go to login

    }

}