package com.example.myapplication.DataBase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class DbAdapter extends RecyclerView.Adapter<DbAdapter.MyViewHolder> {
    private Context  context;
    private ArrayList id, time, event;

    public DbAdapter(Context context, ArrayList id, ArrayList time, ArrayList event){
        this.context = context;
        this.id = id;
        this.time = time;
        this.event = event;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_log_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.time.setText(String.valueOf(time.get(position)));
        holder.event.setText(String.valueOf(event.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id, time,event;
        public MyViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.idTv);
            event = view.findViewById(R.id.eventTV);
            time = view.findViewById(R.id.timeTV);
        }
    }
}
