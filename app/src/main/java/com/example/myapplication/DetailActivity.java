package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView TDNAME,nameSC,NameNO,info,Uses,Dosage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TDNAME=findViewById(R.id.TextMedicine);
        nameSC=findViewById(R.id.edtaddtenkhoahocDT);
        NameNO=findViewById(R.id.edtaddTenthuongDT);
        info=findViewById(R.id.edtaddInformationDT);
        Uses=findViewById(R.id.edtaddUsesDT);
        Dosage=findViewById(R.id.edtadddosageDT);



        Intent myintent = getIntent();
        String nametree = myintent.getStringExtra("nameSC");
        TDNAME.setText(nametree);

        Intent myintent2 = getIntent();
        String nametreeSC = myintent.getStringExtra("nameSC");
        nameSC.setText(nametree);

        Intent myintent3 = getIntent();
        String nameNor = myintent.getStringExtra("nameNo");
        NameNO.setText(nameNor);

        Intent myintent4 = getIntent();
        String infomation = myintent.getStringExtra("info");
        info.setText(infomation);

        Intent myintent5 = getIntent();
        String uses = myintent.getStringExtra("Uses");
        Uses.setText(uses);

        Intent myintent6 = getIntent();
        String dosage = myintent.getStringExtra("Dosage");
        Dosage.setText(dosage);

    }
}