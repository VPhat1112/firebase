package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.item.Medicine;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UploadData extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseFirestore db;
    ArrayList<Medicine> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploaddata);
        EditText edtNameScience = (EditText) findViewById(R.id.edtaddtenkhoahoc1);
        EditText edtNameNormal = (EditText) findViewById(R.id.edtaddTenthuong1);
        EditText edtInformation = (EditText) findViewById(R.id.edtaddInformation1);
        EditText editUses = (EditText) findViewById(R.id.edtaddUses1);
        EditText edtDosage = (EditText) findViewById(R.id.edtadddosage1);

        String NameScience = edtNameScience.getText().toString().trim();
        String NameNormal = edtNameNormal.getText().toString().trim();
        String Information = edtInformation.getText().toString().trim();
        String Uses = editUses.getText().toString().trim();
        String Dosage =edtDosage.getText().toString().trim();

        Button btnThem = (Button) findViewById(R.id.LuuCay1);
        Button btnHuy = (Button) findViewById(R.id.btnHuy1);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NameScience = edtNameScience.getText().toString();
                String NameNormal = edtNameNormal.getText().toString();
                String Information = edtInformation.getText().toString();
                String Uses = editUses.getText().toString().trim();
                String Dosage =edtDosage.getText().toString().trim();
                db = FirebaseFirestore.getInstance();

                Map<String,Object> Medicine= new HashMap<>();
                Medicine.put("NameScience",NameScience);
                Medicine.put("NameNormal",NameNormal);
                Medicine.put("Information",Information);
                Medicine.put("Uses",Uses);
                Medicine.put("Dosage",Dosage);

                db.collection("Medicine").add(Medicine).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }
}
