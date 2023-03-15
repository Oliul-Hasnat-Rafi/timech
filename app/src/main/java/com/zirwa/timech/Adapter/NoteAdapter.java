package com.zirwa.timech.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.zirwa.timech.Buttonhending;
import com.zirwa.timech.Database.Note;
import com.zirwa.timech.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    int totaltask;
    Context context;
    List<Note> noteList;
    Buttonhending buttonhending;
    public SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MMM yyyy", Locale.US);
    public SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-M-yyyy", Locale.US);
    Date date = null;
    String outputDateString=null;


    public NoteAdapter(Context context, List<Note> noteList, Buttonhending buttonhending) {
        this.context = context;
        this.noteList = noteList;
        this.buttonhending = buttonhending;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.simpolraw , parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note  note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.notetext.setText(note.getInsertnote());
        holder.catagoris.setText(note.getCatagoris());
        holder.time_simpol.setText(note.getTime());
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

        holder.delete.setOnClickListener(view -> {
            buttonhending.deletebutton(note);
        });

        holder.itemView.setOnClickListener(view ->
                buttonhending.editbutton(note)
        );

        Random rnd = new Random();
         int  currentColor = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
        holder.cardView.setCardBackgroundColor(currentColor);
    }

    @Override
    public int getItemCount() {
        return noteList.size();

    }

}
