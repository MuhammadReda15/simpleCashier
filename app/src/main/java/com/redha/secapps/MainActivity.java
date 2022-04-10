package com.redha.secapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etkodeBarang, etjumlahBarang;
    private Button btnHitung;
    private TextView tvnamaBarang, tvhargaBarang,tvtotalHarga,tvdiskon,tvjumlahBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penjualan);

        etkodeBarang = findViewById(R.id.etkodeBarang);
        etjumlahBarang = findViewById(R.id.etjumlahBarang);
        btnHitung = findViewById(R.id.btnHitung);
        tvdiskon = findViewById(R.id.tvdiscount);
        tvnamaBarang = findViewById(R.id.tvnamaBarang);
        tvhargaBarang = findViewById(R.id.tvhargaBarang);
        tvtotalHarga = findViewById(R.id.tvtotalHarga);
        tvjumlahBayar = findViewById(R.id.tvjumlahBayar);

        btnHitung.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnHitung){
            String kodeBarang = etkodeBarang.getText().toString().trim().toUpperCase();
            String jumlahBarang = etjumlahBarang.getText().toString().trim();

            boolean isian = false;
            if(TextUtils.isEmpty(kodeBarang)){
                isian = true;
                etkodeBarang.setError("Kode barang tidak boleh kosong");
            }
            if(TextUtils.isEmpty(jumlahBarang)){
                isian = true;
                etjumlahBarang.setError("jumlah barang tidak boleh kosong");
            }

            if(kodeBarang.length()<=4){
                isian = true;
                etkodeBarang.setError("Periksa kembali kode barang");
            }

            if(!isian){
                String kode = kodeBarang.substring(0, 3);
                String diskon = kodeBarang.substring(kodeBarang.length() - 2);
                int disc = Integer.parseInt(diskon);
                int jb = Integer.parseInt(jumlahBarang);

                int hargaAND = 1000000, hargaIOS = 2000000, hargaBLB = 1750000, hargaWNP = 2500000;

                DecimalFormat harga = new DecimalFormat("#,###,###");
                int th1 = jb * hargaAND, th2 = jb * hargaIOS, th3 = jb * hargaBLB, th4 = jb * hargaWNP;
                String thh1 = harga.format(th1), thh2 = harga.format(th2), thh3 = harga.format(th3), thh4 = harga.format(th4);

                int diskon1 = th1 * disc / 100, diskon2 = th2 * disc / 100, diskon3 = th3 * disc / 100, diskon4 = th4 * disc / 100;
                String diskonn1 = harga.format(diskon1), diskonn2 = harga.format(diskon2), diskonn3 = harga.format(diskon3), diskonn4 = harga.format(diskon4);

                int jb1 = th1 - diskon1, jb2 = th2 = diskon2, jb3 = th3 - diskon3, jb4 = th4 - diskon4;
                String jbb1 = harga.format(jb1), jbb2 = harga.format(jb2), jbb3 = harga.format(jb3), jbb4 = harga.format(jb4);

                String hargaAnd = harga.format(hargaAND), hargaIos = harga.format(hargaIOS), hargaBlb = harga.format(hargaBLB), hargaWnp = harga.format(hargaWNP);

                switch (kode) {
                    case "AND":
                        tvnamaBarang.setText("Android");
                        tvhargaBarang.setText(hargaAnd);
                        tvtotalHarga.setText(thh1);
                        tvdiskon.setText(diskonn1);
                        tvjumlahBayar.setText(jbb1);
                        break;

                    case "IOS" :
                        tvnamaBarang.setText("Apple");
                        tvhargaBarang.setText(hargaIos);
                        tvtotalHarga.setText(thh2);
                        tvdiskon.setText(diskonn2);
                        tvjumlahBayar.setText(jbb2);
                        break;

                    case "BLB" :
                        tvnamaBarang.setText("BlackBerry");
                        tvhargaBarang.setText(hargaBlb);
                        tvtotalHarga.setText(thh3);
                        tvdiskon.setText(diskonn3);
                        tvjumlahBayar.setText(jbb3);
                        break;

                    case "WNP" :
                        tvnamaBarang.setText("Windows Phone");
                        tvhargaBarang.setText(hargaWnp);
                        tvtotalHarga.setText(thh4);
                        tvdiskon.setText(diskonn4);
                        tvjumlahBayar.setText(jbb4);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Kode barang tidak ditemukan! Harap periksa kembali kode barang", Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}