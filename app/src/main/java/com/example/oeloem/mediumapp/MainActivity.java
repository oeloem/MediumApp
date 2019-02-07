package com.example.oeloem.mediumapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lvhome)
    ListView lvhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 23
                && checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                ) {

            requestPermissions(new String[] {
                    Manifest.permission.RECORD_AUDIO
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.CAMERA
            }, 0);

        } else {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();

        }

        lvhome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, BluetothActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, WIFIActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, AudioActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent( MainActivity.this, SMSActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, EmailActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, TTSActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, STTActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, CameraActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, RecordActivity.class));
                        break;
                }
            }
        });

    }
}
