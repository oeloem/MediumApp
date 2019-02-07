package com.example.oeloem.mediumapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BluetothActivity extends AppCompatActivity {

    @BindView(R.id.btnon)
    Button btnon;
    @BindView(R.id.btnoff)
    Button btnoff;
    @BindView(R.id.btnvisible)
    Button btnvisible;
    @BindView(R.id.btnpaired)
    Button btnpaired;
    @BindView(R.id.LinearLayout)
    android.widget.LinearLayout LinearLayout;
    @BindView(R.id.listv)
    ListView listv;
    @BindView(R.id.imageView)
    ImageView imageView;

    //Komponen Pengatur Bluetooth
    BluetoothAdapter bluetoothAdapter;
    //Komponen Penampung data device yg Pernah Terhubung
    Set<BluetoothDevice> paired_device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetoth);
        ButterKnife.bind(this);

        //Masukan data bluetooth ke dalam variabel bluetoothAdapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    //Perintah Bluetooth on
    public void pon (View view) {
        //cek bluetooth on
        if (!bluetoothAdapter.isEnabled()) {
            //jika bluetooth tidak aktif, maka aktifkan bluetooth
            //minta pengaturan menggunakan Intent
            Intent hidupkan = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //memanggil pengaturan bluetooth
            startActivityForResult(hidupkan, 0);
            Toast.makeText(this, "Mengaktifkan Bluetooth", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Bluetooth sudah aktif", Toast.LENGTH_SHORT).show();
        }
    }

    //Perintah bluetooth off
    public void pof (View view) {
        bluetoothAdapter.disable();
        Toast.makeText(this, "Menonaktifkan bluetooth", Toast.LENGTH_SHORT).show();
    }

    //Perintah bluetooth visible
    public void pvis (View view) {
        //Kirim permintaan kpd bluetooth adapter untuk membuat perangkat terlihat di perangkat lain
        Intent tampil = new Intent(bluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        //Menampilkan pengaturan bluetooth
        startActivityForResult(tampil, 0);
    }

    //Perintah menampilkan perangkat paired
    public void plist(View view) {
        //Dapatkan data perangkat yg sudah pernah terhubung dari pengaturan bluetoothadapter
        paired_device = bluetoothAdapter.getBondedDevices();
        //variable penampung data untuk listview
        ArrayList list_perangkat = new ArrayList();

        for (BluetoothDevice bt : paired_device) {
            list_perangkat.add(bt.getName());

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_perangkat);
            //Pasang adapter ke koponen listview
            listv.setAdapter(adapter);
        }

    }
}
