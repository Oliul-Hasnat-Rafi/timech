package com.zirwa.timech.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.zirwa.timech.R;

public class Cateviewholder extends RecyclerView.ViewHolder {
    TextView title, notetext,catagoris,date,day,month,time_simpol,task;
    CardView cardView;
    ImageView cate_delete;
    CheckBox cate_checkBox;
    public Cateviewholder(@NonNull View itemView) {
        super(itemView);

        title=itemView.findViewById(R.id.title);
        notetext=itemView.findViewById(R.id.note);
        catagoris=itemView.findViewById(R.id.catagoris);
        cardView=itemView.findViewById(R.id.card);
        cate_delete=itemView.findViewById(R.id.cate_delete);
        day=itemView.findViewById(R.id.day);
        date=itemView.findViewById(R.id.date);
        month=itemView.findViewById(R.id.month);
        time_simpol=itemView.findViewById(R.id.time_simpol);
        cate_checkBox=itemView.findViewById(R.id.cate_checkBox);
        task=itemView.findViewById(R.id.task);

    }
}
