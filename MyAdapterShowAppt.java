package com.example.med4ukm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterShowAppt extends RecyclerView.Adapter<MyAdapterShowAppt.MyViewHolder> {


    Context context;
    ArrayList<User2> list;

    public MyAdapterShowAppt(Context context, ArrayList<User2> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User2 user = list.get(position);
        holder.name.setText(user.getName());
        holder.contact.setText(user.getContact());
        holder.nric.setText(user.getNric());
        holder.date.setText(user.getDate());
        holder.type.setText(user.getType());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, contact, nric, date, type;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            contact = itemView.findViewById(R.id.tvContact);
            nric = itemView.findViewById(R.id.tvNric);
            date = itemView.findViewById(R.id.tvdate);
            type = itemView.findViewById(R.id.tvtype);



        }
    }
}
