package com.example.smartmirror;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AudioMain extends Activity
{
    private AudioReader audioReader;
    private int sampleRate = 8000;
    private int inputBlockSize = 256;
    private int sampleDecimate = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioReader = new AudioReader();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        audioReader.startReader(sampleRate, inputBlockSize * sampleDecimate, new AudioReader.Listener()
        {
            @Override
            public final void onReadComplete(int dB)
            {
                receiveDecibel(dB);
            }

            @Override
            public void onReadError(int error)
            {

            }
        });
    }

//    public void doStart(View v)
//    {
//        audioReader.startReader(sampleRate, inputBlockSize * sampleDecimate, new AudioReader.Listener()
//        {
//            @Override
//            public final void onReadComplete(int dB)
//            {
//                receiveDecibel(dB);
//            }
//
//            @Override
//            public void onReadError(int error)
//            {
//
//            }
//        });
//    }

    private void receiveDecibel(final int dB)
    {
        Log.e("###", dB+" dB");
//        TextView tv = (TextView) findViewById(R.id.textView);
//        tv.setText("DeciBel Is"+dB+"dB");

    }

    public void doStop(View v)
    {
        audioReader.stopReader();
    }
}

