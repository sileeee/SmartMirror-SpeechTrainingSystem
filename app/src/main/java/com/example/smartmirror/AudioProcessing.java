package com.example.smartmirror;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class AudioProcessing extends Activity implements OnClickListener{

    int frequency = 8000;
    int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    private RealDoubleFFT transformer;
    int blockSize=256;
    Button startStopButton;
    boolean started = false;

    RecordAudio recordTask;

//    ImageView imageView;
//    Bitmap bitmap;
//    Canvas canvas;
////    Paint paint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_main);
        startStopButton = (Button)findViewById(R.id.StartStopButton);
        startStopButton.setOnClickListener(this);

        transformer = new RealDoubleFFT(blockSize);

//        imageView = (ImageView)findViewById(R.id.ImageView01);
//        bitmap = Bitmap.createBitmap((int)256,(int)100,Bitmap.Config.ARGB_8888);
//        //그림 자르기(Bitmap source,int x, int y, int width, int height)
//        canvas = new Canvas(bitmap);
//        paint = new Paint();
//        paint.setColor(Color.GREEN);
//        imageView.setImageBitmap(bitmap);
    }


    private class RecordAudio extends AsyncTask <Void, double[],Void> {


//        protected void onProgressUpdate(double[]... toTransform){
//            canvas.drawColor(Color.BLACK);
//
//            for(int i=0; i<toTransform[0].length;i++){
//                int x = i;
//                int downy = (int)(100-(toTransform[0][i]*10));
//                int upy = 100;
//
//                canvas.drawLine(x, downy, x, upy, paint);
//            }
//            imageView.invalidate();
//        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                int bufferSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding);

                AudioRecord audioRecord = new AudioRecord(
                        MediaRecorder.AudioSource.VOICE_RECOGNITION,frequency,channelConfiguration,audioEncoding, bufferSize);

                short[] buffer = new short[blockSize];
                double[] toTransform = new double[blockSize];

                audioRecord.startRecording();

                while(started){
                    int bufferReadResult = audioRecord.read(buffer,0,blockSize);

                    for(int i = 0; i < blockSize && i < bufferReadResult; i++){
                        toTransform[i] = (double)buffer[i] / Short.MAX_VALUE;
                    }
                    transformer.ft(toTransform);

                    publishProgress(toTransform);
                }
                audioRecord.stop();
            }catch(Throwable t){
                Log.e("AudioRecord","Recording Failed");
            }

            return null;
        }
    }

    @Override
    public void onClick(View arg0){
        if(started){
            started = false;
            startStopButton.setText("Start");
            recordTask.cancel(true);
        }
        else{
            started =true;
            startStopButton.setText("Stop");
            recordTask = new RecordAudio();
            recordTask.execute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_audio_record, menu);
        return true;
    }

}