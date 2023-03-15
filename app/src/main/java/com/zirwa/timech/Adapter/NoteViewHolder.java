package com.zirwa.timech.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.zirwa.timech.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView title, notetext,catagoris,date,day,month,time_simpol,task;
    CardView cardView;
    ImageView delete;
    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.title);
        notetext=itemView.findViewById(R.id.note);
        catagoris=itemView.findViewById(R.id.catagoris);
        cardView=itemView.findViewById(R.id.card);
        delete=itemView.findViewById(R.id.delete);
        day=itemView.findViewById(R.id.day);
        date=itemView.findViewById(R.id.date);
        month=itemView.findViewById(R.id.month);
        time_simpol=itemView.findViewById(R.id.time_simpol);
        task=itemView.findViewById(R.id.task);


    }
}
