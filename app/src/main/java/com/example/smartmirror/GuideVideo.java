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
        Uri videoUri= Uri.parse("https://r5---sn-npoeen7k.googlevideo.com/videoplayback?expire=1607951277&ei=TQ_XX-3UCLWWxN8PhsixiAo&ip=180.247.196.192&id=o-AAX4OpMgI2ZpvpBaO__P0eF80oBanvgpMmnQJtjnCwiw&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=6qGNcdKM99o3HKzLteJR_R8F&gir=yes&clen=131031&ratebypass=yes&dur=4.458&lmt=1607929594551994&fvip=5&c=WEB&txp=6200222&n=U6Q2dKxEH7FkcV2QU&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIhAPu5Fuk844jSeY9ho8mqlxK3RQgOf6dhrS42vZ1fnGPAAiB29s4nyQpebRM-rg1vg1vd8-kARceSEW6e8_35TMA-AQ%3D%3D&rm=sn-2uuxa3vh-n0cr7s&req_id=3b61a842a620a3ee&ipbypass=yes&redirect_counter=2&cm2rm=sn-npoz676&cms_redirect=yes&mh=a2&mip=210.94.220.230&mm=34&mn=sn-npoeen7k&ms=ltu&mt=1607928998&mv=u&mvi=5&pl=24&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIhAPAzHNF0jMCHxnyyWrPenW9mV7xUjddwkxsT0xvwJWvpAiB29CVrWqPxgd1NlSXbCtBlHgQskHR08bV9y_WAEkHbeQ%3D%3D");
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