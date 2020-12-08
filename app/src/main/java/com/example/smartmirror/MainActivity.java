package com.example.smartmirror;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ClickBtn(View view) {
        Intent intent = new Intent( this, FreeTalking.class);
        startActivity(intent);
    }
    public void ClickBtn2(View view) {
        Intent intent = new Intent( this, VideoPractice.class);
        startActivity(intent);
    }
    public void ClickBtn3(View view) {
        Intent intent = new Intent( this, GuideVideo.class);
        startActivity(intent);
    }
}
