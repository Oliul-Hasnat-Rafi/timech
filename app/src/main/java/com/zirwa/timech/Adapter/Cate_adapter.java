package com.zirwa.timech.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zirwa.timech.Database.Note;
import com.zirwa.timech.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Cate_adapter extends RecyclerView.Adapter<Cateviewholder> {

    Context context;
    List<Note> noteList;
    public SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MMM yyyy", Locale.US);
    public SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-M-yyyy", Locale.US);
    Date date = null;
    String outputDateString=null;


    public Cate_adapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public Cateviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categoriy_simpol , parent, false);
        return new Cateviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cateviewholder holder, int position) {
        Note  note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.notetext.setText(note.getInsertnote());
        holder.catagoris.setText(note.getCatagoris());
        holder.time_simpol.setText(note.getTime());
        holder.cate_checkBox.setVisibility(View.GONE);
        holder.cate_delete.setVisibility(View.GONE);
        try {
            date = inputDateFormat.parse(note.getDate());
            outputDateString = dateFormat.format(date);

            String[] items1 = outputDateString.split(" ");
            String day = items1[0];
            String dd = items1[1];
            String month = items1[2];

            holder.day.setText(day);
            holder.date.setText(dd);
            holder.month.setText(month);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Random rnd = new Random();
        int  currentColor = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
        holder.cardView.setCardBackgroundColor(currentColor);

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
