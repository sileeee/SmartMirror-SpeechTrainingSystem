package com.example.smartmirror;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class GuideVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_video);

        final VideoView testVideo = (VideoView)findViewById(R.id.testVideo);
        Resources res = getResources();
        int id_video = res.getIdentifier("test","raw", getPackageName());

        Uri uri = Uri.parse("android.resource://com.example.test/" + id_video);

        // 해당하는 비디오 uri 장착
        testVideo.setVideoURI(uri);
        // 비디오 시작
        testVideo.start();
        // 화면 아래에 비디오 컨트롤러 만들기
        MediaController mcontroller = new MediaController(this);
        testVideo.setMediaController(mcontroller);
        testVideo.setOnTouchListener(new View.OnTouchListener() {
            // 터치 시 비디오 정지, 시작 처리
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (testVideo.isPlaying()) { // 비디오의 재생여부를 boolean 값으로 반환.
                    testVideo.pause();
                    return false;
                } else {
                    testVideo.start();
                    return false;
                }
            }
        });

    }
}
