package com.example.med4ukm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList <ModelClass> arrayList;
    Context context;

    public Adapter(ArrayList<ModelClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_design,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(arrayList.get(position).title);
        holder.tv_desc.setText(arrayList.get(position).description);

      holder.tv_title.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(arrayList.get(position).isVisible)
              {
                 holder.tv_desc.setVisibility(View.GONE);
                 holder.rl_desc_line.setVisibility(View.GONE);
                 holder.rl_title_line.setVisibility(View.VISIBLE);
                 arrayList.get(position).isVisible=false;

              }
              else
              {
                  holder.tv_desc.setVisibility(View.VISIBLE);
                  holder.rl_desc_line.setVisibility(View.VISIBLE);
                  holder.rl_title_line.setVisibility(View.GONE);
                  arrayList.get(position).isVisible=true;
              }
          }
      });





    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        RelativeLayout rl_title_line;
        TextView tv_desc;
        RelativeLayout rl_desc_line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            rl_title_line=itemView.findViewById(R.id.rl_title_line);
            tv_desc=itemView.findViewById(R.id.tv_desc);
            rl_desc_line=itemView.findViewById(R.id.rl_desc_line);



        }
    }
}
