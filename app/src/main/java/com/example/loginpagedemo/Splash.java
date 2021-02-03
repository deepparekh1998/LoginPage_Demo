package com.example.loginpagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    ImageView imgtop, imgheart, imgbeats, imgbottom;
    TextView textView;
    CharSequence charSequence;
    int index;
    long delay = 200;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        imgtop = findViewById(R.id.iv_top);
        imgheart = findViewById(R.id.iv_heart);
        imgbeats = findViewById(R.id.iv_beats);
        imgbottom = findViewById(R.id.iv_btm);

        textView = findViewById(R.id.txtview);

        //Setting the animation into Full Screen

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Starting the top Animation

        Animation anim = AnimationUtils.loadAnimation(this
                ,R.anim.top_anmt);

        imgtop.startAnimation(anim);

        //Initializing Object Animator here

        ObjectAnimator objectAnimator =  ObjectAnimator.ofPropertyValuesHolder(
                imgheart,
                PropertyValuesHolder.ofFloat("scaleX",1.2f),
                PropertyValuesHolder.ofFloat("scaleY",1.2f)
        );

        //Setting duration

        objectAnimator.setDuration(500);

        //Setting the repeat counter

        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);

        //Setting the repeat mode counter

        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //To start the animator

        objectAnimator.start();

        animatedText("Welcome To IITS");

        //Initializing the bottom animation
        Animation animate = AnimationUtils.loadAnimation(this
                ,R.anim.bottom_anmt);

        //Starting bottom animation
        imgbottom.setAnimation(animate);

        //Initiazing Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 6000);
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            textView.setText(charSequence.subSequence(0,index++));
            if (index <= charSequence.length()){
                handler.postDelayed(runnable,delay);

            }
        }
    };
    //Creating the method for animated text

    public void animatedText(CharSequence cs){
        charSequence = cs;
        index = 0;
        textView.setText("");
        handler.removeCallbacks(runnable);

        //Running handler
        handler.postDelayed(runnable,delay);
    }
}