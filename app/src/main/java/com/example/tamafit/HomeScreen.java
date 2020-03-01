package com.example.tamafit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity{

    AnimationDrawable babyAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        // Get the Intent that started this activity and extract the string
        //Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        //TextView textView = findViewById(R.id.textView);
        //textView.setText(message);

        ImageView babyImage = (ImageView) findViewById(R.id.babyImage);
        babyImage.setBackgroundResource(R.drawable.baby_animated);
        babyAnimation = (AnimationDrawable) babyImage.getBackground();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        babyAnimation.start();
    }




}

