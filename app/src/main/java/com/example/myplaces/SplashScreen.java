package com.example.myplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    ProgressBar progressBar;
    int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doIt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startMap();

            }
        });

        thread.start();


    }

    public void doIt() throws InterruptedException {
        for (progress = 20; progress <= 100; progress = progress + 20) {
            Thread.sleep(1000);
            progressBar.setProgress(progress);
        }
    }

    public void startMap(){
        Intent intent = new Intent(SplashScreen.this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}
