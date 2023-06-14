package com.quynhlm.dev.assignmentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Acticity_Welcome extends AppCompatActivity {

    TextView txtwel;
    TextView txtpoly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtwel = findViewById(R.id.txtwel);
        txtpoly = findViewById(R.id.txtpoly);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Acticity_Welcome.this, Activity_Dang_Nhap.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
        Animation animation = AnimationUtils.loadAnimation(Acticity_Welcome.this, R.anim.an_hien);
        txtwel.startAnimation(animation);
        txtpoly.startAnimation(animation);
    }
}