package com.example.smartmirror;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class GuideVideo extends AppCompatActivity {
    VideoView vv;
    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private EditText editText;
    private ImageView micButton;
    private Button Button_event;

    public void ClickBtnAnalysis(View view) {
        Intent intent = new Intent( this, Analysis.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_video);

        Button_event = (Button)findViewById(R.id.list);

        vv = findViewById(R.id.vv);
        //Video Uri
        Uri videoUri = Uri.parse("https://r4---sn-npoeen7d.googlevideo.com/videoplayback?expire=1613646913&ei=4fctYOGEFvSE1d8P0Ni4qAI&ip=59.153.225.206&id=o-AG64z7wNNP_QdXD-KfVail8k1CUM0q7T_INHaOnlmZts&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=CS_-i-lIdh0uFPfBz7ecanwF&gir=yes&clen=115533&ratebypass=yes&dur=3.668&lmt=1613623910834604&fvip=2&c=WEB&txp=6200222&n=Uz9rISAx75AH6Dr1J&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIhANqn0kJCF2Twm6Xs3PTh8pn2qDJzT88TTtDwqMZD0xTwAiBwmFW_qA_PyBIf9jlwlekzQMfHUsKcHHJEpksEBEyLFA%3D%3D&title=%ED%85%8C%EC%8A%A4%ED%8A%B8%EC%9A%A9&cm2rm=sn-n3cgv5qc5oq-bh2s67l,sn-oguks7l&req_id=a3a7a0ce5164a3ee&redirect_counter=2&cms_redirect=yes&mh=L6&mip=1.232.197.66&mm=34&mn=sn-npoeen7d&ms=ltu&mt=1613625161&mv=m&mvi=4&pl=18&lsparams=mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIhAKYqIT5k55lAdHd0BFsCl2sbd_Ou_BLsyAjHgRm2VZfKAiAfTknqWhKiGVGnvkaDa0G6i3GveRa1-iKsv2XO0j1DvA%3D%3D");
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }

        editText = findViewById(R.id.text);
        micButton = findViewById(R.id.button);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                editText.setText("");
                editText.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                micButton.setImageResource(R.drawable.ic_mic_black_off);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                editText.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        micButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    speechRecognizer.stopListening();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    micButton.setImageResource(R.drawable.ic_mic);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
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
        speechRecognizer.destroy();
        if(vv!=null) vv.stopPlayback();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }

}