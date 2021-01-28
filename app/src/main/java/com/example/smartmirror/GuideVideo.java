package com.example.smartmirror;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Spinner;
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

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_video);

        final TextView textView = (TextView)findViewById(R.id.textView);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                textView.setText("현재 예제 비디오 : " + parent.getItemAtPosition(position));
                if(position == 2)
                {
                    Uri videoUri = Uri.parse("https://r5---sn-npoeen7k.googlevideo.com/videoplayback?expire=1607951277&ei=TQ_XX-3UCLWWxN8PhsixiAo&ip=180.247.196.192&id=o-AAX4OpMgI2ZpvpBaO__P0eF80oBanvgpMmnQJtjnCwiw&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=6qGNcdKM99o3HKzLteJR_R8F&gir=yes&clen=131031&ratebypass=yes&dur=4.458&lmt=1607929594551994&fvip=5&c=WEB&txp=6200222&n=U6Q2dKxEH7FkcV2QU&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIhAPu5Fuk844jSeY9ho8mqlxK3RQgOf6dhrS42vZ1fnGPAAiB29s4nyQpebRM-rg1vg1vd8-kARceSEW6e8_35TMA-AQ%3D%3D&rm=sn-2uuxa3vh-n0cr7s&req_id=3b61a842a620a3ee&ipbypass=yes&redirect_counter=2&cm2rm=sn-npoz676&cms_redirect=yes&mh=a2&mip=210.94.220.230&mm=34&mn=sn-npoeen7k&ms=ltu&mt=1607928998&mv=u&mvi=5&pl=24&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIhAPAzHNF0jMCHxnyyWrPenW9mV7xUjddwkxsT0xvwJWvpAiB29CVrWqPxgd1NlSXbCtBlHgQskHR08bV9y_WAEkHbeQ%3D%3D");
                    // 자체 경로
                    Uri introURI;
                    introURI = Uri.parse("android.resource://your.app.package/" + R.raw.test);


                    //비디오뷰의 재생, 일시정지 등을 할 수 있는 '컨트롤바'를 붙여주는 작업
                    //vv.setMediaController(new MediaController(this));

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
                    //if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    //    checkPermission();
                    //}
                }
                else if(position == 3)
                {
                    Uri videoUri = Uri.parse("https://r5---sn-npoeenee.googlevideo.com/videoplayback?expire=1607964796&ei=HETXX4LrJrv7zLUPrdGCqAE&ip=209.197.26.73&id=8b8adfb6a5ac9419&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=Tzscs640uzsvtecMYdWuXz8F&gir=yes&clen=75993&ratebypass=yes&dur=2.484&lmt=1607942510079303&fvip=5&beids=23886207&c=WEB&txp=6200222&n=LAqxBfJB-GHSnBmvd&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIhAPCctUkCwNZIHdVfGzku387XiGBBKVXINiZogfpeHDeWAiBjAUunzN3jiE7F0KC6upkv_TVl47kT0sPd4dDzF3F0sg%3D%3D&redirect_counter=1&cm2rm=sn-5uads76&fexp=23886207&req_id=1ea2f78ffcf3a3ee&cms_redirect=yes&mh=xL&mip=210.94.220.230&mm=34&mn=sn-npoeenee&ms=ltu&mt=1607942698&mv=u&mvi=5&pl=24&lsparams=mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRgIhANmykNk4OKEuIWAQg88iMW_nlaxnEXZFDtpmMV8lAmz6AiEA7-s4V0dN8XWMv2j6Gbq_YcMpSXQ0pdSd99hl6hWIGyQ%3D");
                    // 자체 경로
                    Uri introURI;
                    introURI = Uri.parse("android.resource://your.app.package/" + R.raw.test);


                    //비디오뷰의 재생, 일시정지 등을 할 수 있는 '컨트롤바'를 붙여주는 작업
                    //vv.setMediaController(new MediaController(this));

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
                    //if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    //    checkPermission();
                    //}
                }
                else if(position == 4)
                {

                }
                else if(position == 5)
                {

                }
                else{
                    Uri videoUri = Uri.parse("https://r5---sn-npoe7nez.googlevideo.com/videoplayback?expire=1607963613&ei=fT_XX_SpHJnjgAfg9YCgCw&ip=95.140.27.135&id=fa174334e408f616&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=u1aT_4rKxSZi2vASbQTpoG8F&gir=yes&clen=55153&ratebypass=yes&dur=2.020&lmt=1607941936115368&fvip=15&c=WEB&txp=6200222&n=td30fNV8vbZyH3jDy&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRgIhAP7H7s8IVRFG_9VAnFYxpccD6PZjHKMaxwETdvZ0MTBQAiEA11Mets846-iSz5OVXuerudbY2xwuUgW7k3tMeNtqS5c%3D&rm=sn-pivhx-n8vs76&req_id=3429a962f153a3ee&redirect_counter=2&cm2rm=sn-n8vyle6&cms_redirect=yes&mh=NT&mip=210.94.220.230&mm=34&mn=sn-npoe7nez&ms=ltu&mt=1607941907&mv=u&mvi=5&pl=24&lsparams=mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRgIhAKPhRic5RDse_Lr9k3Fi5z3oTDMFjQzvEuaCZzxhtUwBAiEArQnDd9sxjW0Ya7qmMGn1AhuSZMAM1tA4fR_SiJF9TDk%3D");
                    // 자체 경로
                    Uri introURI;
                    introURI = Uri.parse("android.resource://your.app.package/" + R.raw.test);


                    //비디오뷰의 재생, 일시정지 등을 할 수 있는 '컨트롤바'를 붙여주는 작업
                    //vv.setMediaController(new MediaController(this));

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
                    //if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    //    checkPermission();
                    //}
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });
        Button_event = (Button)findViewById(R.id.list);

        vv = findViewById(R.id.vv);
        //Video Uri
        Uri videoUri = Uri.parse("https://r5---sn-npoeen7k.googlevideo.com/videoplayback?expire=1607951277&ei=TQ_XX-3UCLWWxN8PhsixiAo&ip=180.247.196.192&id=o-AAX4OpMgI2ZpvpBaO__P0eF80oBanvgpMmnQJtjnCwiw&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=6qGNcdKM99o3HKzLteJR_R8F&gir=yes&clen=131031&ratebypass=yes&dur=4.458&lmt=1607929594551994&fvip=5&c=WEB&txp=6200222&n=U6Q2dKxEH7FkcV2QU&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIhAPu5Fuk844jSeY9ho8mqlxK3RQgOf6dhrS42vZ1fnGPAAiB29s4nyQpebRM-rg1vg1vd8-kARceSEW6e8_35TMA-AQ%3D%3D&rm=sn-2uuxa3vh-n0cr7s&req_id=3b61a842a620a3ee&ipbypass=yes&redirect_counter=2&cm2rm=sn-npoz676&cms_redirect=yes&mh=a2&mip=210.94.220.230&mm=34&mn=sn-npoeen7k&ms=ltu&mt=1607928998&mv=u&mvi=5&pl=24&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIhAPAzHNF0jMCHxnyyWrPenW9mV7xUjddwkxsT0xvwJWvpAiB29CVrWqPxgd1NlSXbCtBlHgQskHR08bV9y_WAEkHbeQ%3D%3D");
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