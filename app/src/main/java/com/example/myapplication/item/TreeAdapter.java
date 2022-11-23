package com.example.myapplication.item;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailActivity;
import com.example.myapplication.HomeActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.DataViewHolder> {
    HomeActivity context;
    ArrayList<Medicine> list;

    public void setData( ArrayList<Medicine> list) {
        this.list = list;
    }

    public MedicineAdapter(HomeActivity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_medicine, parent, false);

        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Medicine tree=list.get(position);
        if(list==null){
            return;
        }
        holder.Tv_NametreeSc1.setText(tree.getNameScience());
        holder.Tv_NametreeN1.setText(tree.getNameNormal());
        holder.Tv_Info1.setText(tree.getInformation());
        holder.Tv_Uses1.setText(tree.getUses());
        holder.Tv_dosage1.setText(tree.getDosage());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(context, DetailActivity.class);
                myintent.putExtra("nameSC",tree.getNameScience());
                myintent.putExtra("nameNo",tree.getNameNormal());
                myintent.putExtra("info",tree.getInformation());
                myintent.putExtra("Uses",tree.getUses());
                myintent.putExtra("Dosage",tree.getDosage());
                context.startActivity(myintent);
            }
        });
        holder.Detele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoa(tree.getNameScience());


                            }
        });

    }

    @Override
    public int getItemCount() {
        if (list!=null)
            return list.size();
        else return 0;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        Button btnLuu,btnHuy;
        RelativeLayout relativeLayout;
        TextView Tv_NametreeSc1,Tv_NametreeN1,Tv_Info1,Tv_Uses1,Tv_dosage1,Detele;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            Detele=itemView.findViewById(R.id.deleteTV);
            Tv_NametreeSc1=itemView.findViewById(R.id.Tv_NameMedicineSc1);
            Tv_NametreeN1=itemView.findViewById(R.id.Tv_NameMedicineN1);
            Tv_Info1=itemView.findViewById(R.id.Tv_Info1);
            Tv_Uses1=itemView.findViewById(R.id.Tv_Uses1);
            Tv_dosage1=itemView.findViewById(R.id.Tv_dosage1);
            btnLuu=itemView.findViewById(R.id.LuuCay);
            btnHuy=itemView.findViewById(R.id.btnHuy);
            relativeLayout=itemView.findViewById(R.id.loitem);




        }
    }
}
