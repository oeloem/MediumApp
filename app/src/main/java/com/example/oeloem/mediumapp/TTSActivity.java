package com.example.oeloem.mediumapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//Todo implement berfungsi untuk mewarisi method dari kelas lain
public class TTSActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {


    @BindView(R.id.txtText)
    EditText txtText;
    @BindView(R.id.btnSpeech)
    Button btnSpeech;

    private android.speech.tts.TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        ButterKnife.bind(this);

        //masukan komponen tts
        textToSpeech = new android.speech.tts.TextToSpeech(this, this);

    }


    @Override
    public void onInit(int i) {
        // Jika bisa mengakses komponen TTS
        if (i == TextToSpeech.SUCCESS) {
            //Atur bahasa ke bahasa indonesia
            Locale indo = new Locale("in", "INA");
            // Variable untuk memasukan bahasa ke dalam TTS
            int result = textToSpeech.setLanguage(indo);

            // Jika bahasa tidak tersedia di perangkat atau Perangkat Tidak Mendukung
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this,"Bahasa Tidak Ditemukan", Toast.LENGTH_SHORT).show();

            } else {
                //Tampilkan tombol
                btnSpeech.setEnabled(true);
                // Method mengambil data text dari EditText
                mulaiBicara();
            }

        } else {
            // Jika komponen tidak terjangkau
            Toast.makeText(this, "Inisialisasi Gagal", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btnSpeech)
    public void onViewClicked() {

        mulaiBicara();
    }

    private void mulaiBicara() {
        // Variable penam[ung text
        String text = txtText.getText().toString();
        // Proses text
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
