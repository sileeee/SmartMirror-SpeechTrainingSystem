package com.example.smartmirror;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class GuideVideo extends AppCompatActivity {
    VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_video);

        vv= findViewById(R.id.vv);
        //Video Uri
        Uri videoUri= Uri.parse("https://r1---sn-npoe7nlz.googlevideo.com/videoplayback?expire=1607951097&ei=mQ7XX9f5Ko3PxwKv-4y4Dg&ip=180.247.196.192&id=o-AJHodvf6HOhybj0qiClOw2cuq3eQHyYIlAjJgxU3IDAa&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=EGWzC-DjLp4GxOXSxr4YRMUF&gir=yes&clen=151658650&ratebypass=yes&dur=3356.281&lmt=1605268093672951&fvip=1&c=WEB&txp=6430432&n=UQ-7QDM9r7w4hSLvQ&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRgIhALh0AyCtAFF_diCxr0bF2Ftiv-Hk2cuoiWNP9soOBo97AiEAg-G52ToiJIfrt4T0xLHtLgH0iyqcrTiSL6koEOcBG7U%3D&redirect_counter=1&rm=sn-2uuxa3vh-n0cr7e&req_id=5d42995eeacca3ee&cms_redirect=yes&ipbypass=yes&mh=3o&mm=29&mn=sn-npoe7nlz&ms=rdu&mt=1607929256&mv=m&mvi=1&pl=23&lsparams=ipbypass,mh,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIgNdMAitnSXhXZpR30RBtpqipBL7FScwtGVDLbhulITaUCIQDKzvBqggw-dcFw2QjblMkFlNAdRqsfBH6u02xNJyNbXQ%3D%3D");
        // 자체 경로
        Uri introURI;
        introURI = Uri.parse("android.resource://your.app.package/" + R.raw.test);


        //비디오뷰의 재생, 일시정지 등을 할 수 있는 '컨트롤바'를 붙여주는 작업
        vv.setMediaController(new MediaController(this));

        //VideoView가 보여줄 동영상의 경로 주소(Uri) 설정하기
        vv.setVideoURI(videoUri);

        //동영상을 읽어오는데 시간이 걸리므로..
        //비디오 로딩 준비가 끝났을 때 실행하도록..
        //리스너 설정
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //비디오 시작
                vv.start();
            }
        });

    }//onCreate ..

    //화면에 안보일때...
    @Override
    protected void onPause() {
        super.onPause();

        //비디오 일시 정지
        if(vv!=null && vv.isPlaying()) vv.pause();
    }
    //액티비티가 메모리에서 사라질때..
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //
        if(vv!=null) vv.stopPlayback();
    }
}