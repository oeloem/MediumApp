package com.example.oeloem.mediumapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends AppCompatActivity {

    @BindView(R.id.btnplay)
    Button btnplay;
    @BindView(R.id.btnstop)
    Button btnstop;
    @BindView(R.id.btnrecord)
    Button btnrecord;
    @BindView(R.id.stplay)
    Button stplay;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    String outputFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);

        // Atur tombol default
        btnplay.setEnabled(false);
        btnstop.setEnabled(false);
        stplay.setEnabled(false);

        // tempat penyimpanan
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record.3gp";

        // Masukan komponen kedalam mediarecorder
        mediaRecorder = new MediaRecorder();

        // memasukan komponen media player
        mediaPlayer = new MediaPlayer();
    }

    @OnClick({R.id.btnplay, R.id.btnstop, R.id.btnrecord, R.id.stplay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnplay:
                try {
                    mediaPlayer.setDataSource(outputFile);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }

                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }

                try {
                    mediaPlayer.start();
                }catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnstop:
                mediaRecorder.stop();
                //Todo berguna supaya audio tersimpan saat buttton stop di klik
                mediaRecorder.release();
                mediaRecorder = null;
                mediaRecorder = new MediaRecorder();

                btnplay.setEnabled(true);
                btnrecord.setEnabled(true);
                btnstop.setEnabled(false);

                Toast.makeText(this, "Stop recording...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnrecord:

                // Sumber suara
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                // format file
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                // encorder file record
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                // MEMASUKAN FILE KEDALAM DIREKTORY
                mediaRecorder.setOutputFile(outputFile);
                mediaPlayer = new MediaPlayer();

                try {
                    mediaRecorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {

                }

                mediaRecorder.start();

                stplay.setEnabled(false);
                btnplay.setEnabled(false);
                btnrecord.setEnabled(false);
                btnstop.setEnabled(true);

                Toast.makeText(this, "Recording...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.stplay:

                // Jika masih
                try {
                    mediaPlayer.stop();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }

                btnplay.setEnabled(true);
                btnrecord.setEnabled(true);
                btnstop.setEnabled(false);
                stplay.setEnabled(false);

                Toast.makeText(this, "Stop playing", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
