package com.project.textrecognition.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.textrecognition.R;


public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_LENGTH = 3000;
    private ImageView applogo;
    private TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeView();


        setFadeAnimation();


        screenDelay();

    }

    /**
     * set animation to logo & app title
     */
    private void setFadeAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1500);
        applogo.startAnimation(alphaAnimation);
        appTitle.startAnimation(alphaAnimation);
    }

    /**
     * set screen delay into activity
     */
    private void screenDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    /**
     * initialize views
     */
    private void initializeView() {
        applogo = findViewById(R.id.splashLogo);
        appTitle = findViewById(R.id.appTitleTxt);
    }
}
