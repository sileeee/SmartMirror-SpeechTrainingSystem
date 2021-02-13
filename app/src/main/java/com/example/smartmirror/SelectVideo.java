package com.example.smartmirror;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_video);
    }

    public void ClickVideo1(View view) {
        Intent intent = new Intent( this, GuideVideo.class);
        startActivity(intent);
    }
}
