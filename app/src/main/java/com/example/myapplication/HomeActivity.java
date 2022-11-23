package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.item.Medicine;
import com.example.myapplication.item.MedicineAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore db;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    MedicineAdapter adapter;
    ArrayList<Medicine> list;
    TextView btnthem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data ....");
        progressDialog.show();

        btnthem=findViewById(R.id.btn_Them);
        recyclerView=findViewById(R.id.Rcv_Tree);

        adapter= new MedicineAdapter(this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        list= new ArrayList<Medicine>();
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
        db=FirebaseFirestore.getInstance();

        //ReadDatabase();
        EventChangeListener();

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog_ThemRealtime();
                Dialog_Them();
            }
        });

    }
//    private void ReadDatabase(){
//
//        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//        DatabaseReference db=firebaseDatabase.getReference("Tree");
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Tree tree = dataSnapshot.getValue(Tree.class);
//                    list.add(tree);
//                    adapter.setData(list);
//                    recyclerView.setAdapter(adapter);
//                    if (progressDialog.isShowing())
//                           progressDialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//    public void Dialog_ThemRealtime(){
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_them);
//
//        EditText edtNameScience = (EditText) dialog.findViewById(R.id.edtaddtenkhoahoc);
//        EditText edtNameNormal = (EditText) dialog.findViewById(R.id.edtaddTenthuong);
//        EditText edtInformation = (EditText) dialog.findViewById(R.id.edtaddInformation);
//        EditText edtLeafColor = (EditText) dialog.findViewById(R.id.edtaddMaula);
//
//        String NameScience = edtNameScience.getText().toString().trim();
//        String NameNormal = edtNameNormal.getText().toString().trim();
//        String Information = edtInformation.getText().toString().trim();
//        String LeafColor = edtLeafColor.getText().toString().trim();
//
//        Button btnThem = (Button) dialog.findViewById(R.id.LuuCay);
//        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);
//
//        Tree tree = new Tree(NameScience,NameNormal,Information,LeafColor);
//        String pathOject=String.valueOf(tree.getNameNormal());
//        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//        DatabaseReference db=firebaseDatabase.getReference("Tree/"+pathOject);
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                db.child(pathOject).setValue(tree, new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        Toast.makeText(HomeActivity.this,"Push data success",Toast.LENGTH_LONG).show();
//                    }
//                });
//                Intent intent= new Intent(HomeActivity.this,HomeActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnHuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
    private void EventChangeListener() {

        db.collection("Medicine").orderBy("NameScience", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null){
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType()==DocumentChange.Type.ADDED){
                                list.add(dc.getDocument().toObject(Medicine.class));
                            }
                        }
                        adapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });

    }
    public void Dialog_Them(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them);

        final EditText edtNameScience = (EditText) dialog.findViewById(R.id.edtaddtenkhoahoc);
        final EditText edtNameNormal = (EditText) dialog.findViewById(R.id.edtaddTenthuong);
        final EditText edtInformation = (EditText) dialog.findViewById(R.id.edtaddInformation);
        EditText editUses = (EditText) findViewById(R.id.edtaddUses);
        EditText edtDosage = (EditText) findViewById(R.id.edtadddosage);
        Button btnThem = (Button) dialog.findViewById(R.id.LuuCay);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NameScience = edtNameScience.getText().toString();
                String NameNormal = edtNameNormal.getText().toString();
                String Information = edtInformation.getText().toString();
                String Uses = editUses.getText().toString();
                String Dosage =edtDosage.getText().toString();
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
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void DialogXoa(String NameScience){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa cây này hay không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.collection("Medicine")
                        .whereEqualTo("NameScience",NameScience)
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful() && !task.getResult().isEmpty()){
                                    DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);
                                    String NameSciences=documentSnapshot.getId();
                                    db.collection("Medicine")
                                            .document(NameSciences)
                                            .delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(HomeActivity.this,"Succesfull delete",Toast.LENGTH_LONG).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(HomeActivity.this,"Some error occured",Toast.LENGTH_LONG).show();
                                                }
                                            });



                                }else {
                                    Toast.makeText(HomeActivity.this,"Failed",Toast.LENGTH_LONG).show();
                                }                            }
                        });

                EventChangeListener();

            }
        });

        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogXoa.show();
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
    }

}