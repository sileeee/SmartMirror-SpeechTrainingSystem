package com.example.smartmirror;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FreeTalking extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_talking);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Free Talking");
    }
}
