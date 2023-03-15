package com.zirwa.timech.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zirwa.timech.Adapter.NoteAdapter;
import com.zirwa.timech.Buttonhending;
import com.zirwa.timech.Categorie.HealthActivity;
import com.zirwa.timech.Categorie.PersonalActivity;
import com.zirwa.timech.Categorie.ShoppingActivity;
import com.zirwa.timech.Categorie.wrokActivity;
import com.zirwa.timech.Database.Note;
import com.zirwa.timech.Database.NoteDatabase;
import com.zirwa.timech.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Dashboard_Activity extends AppCompatActivity implements Buttonhending {
    RecyclerView RecyclerView;
    ImageView addbtn;
    TextView task,tasktitle,taskcate,tasknotedes;
    CardView wrok,personal,shopping,heath;
    List<Note> tasknote;
    List<Note> notescheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerView = findViewById(R.id.RecyclerView);
        addbtn = findViewById(R.id.addbtn);
        task = findViewById(R.id.task);
        wrok = findViewById(R.id.work);
        personal = findViewById(R.id.personal);
        shopping = findViewById(R.id.shopping);
        heath = findViewById(R.id.health);
        tasktitle = findViewById(R.id.tasktitle);
        taskcate = findViewById(R.id.taskcate);
        tasknotedes = findViewById(R.id.tasknote);

        nexttask();

        wrok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notescheck = NoteDatabase.getInstance(Dashboard_Activity.this).getNoteDao().getAllNoteByCatagoris("Work");
                if (notescheck.size() == 0) {
                    Toast.makeText(Dashboard_Activity.this, "You don't have any task in Work", Toast.LENGTH_SHORT).show();
                } else {
                    Intent wrok = new Intent(Dashboard_Activity.this, wrokActivity.class);
                    startActivity(wrok);
                }
            }
        });

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notescheck = NoteDatabase.getInstance(Dashboard_Activity.this).getNoteDao().getAllNoteByCatagoris("personal");
                if (notescheck.size() == 0) {
                    Toast.makeText(Dashboard_Activity.this, "You don't have any task in personal", Toast.LENGTH_SHORT).show();
                } else {
                    Intent personal = new Intent(Dashboard_Activity.this, PersonalActivity.class);
                    startActivity(personal);
                }
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notescheck = NoteDatabase.getInstance(Dashboard_Activity.this).getNoteDao().getAllNoteByCatagoris("shopping");
                if (notescheck.size() == 0) {
                    Toast.makeText(Dashboard_Activity.this, "You don't have any task in Shopping", Toast.LENGTH_SHORT).show();
                } else {
                    Intent shopping = new Intent(Dashboard_Activity.this, ShoppingActivity.class);
                    startActivity(shopping);
                }
            }
        });
        heath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notescheck = NoteDatabase.getInstance(Dashboard_Activity.this).getNoteDao().getAllNoteByCatagoris("health");
                if (notescheck.size() == 0) {
                    Toast.makeText(Dashboard_Activity.this, "You don't have any task in health", Toast.LENGTH_SHORT).show();
                } else {
                    Intent heath = new Intent(Dashboard_Activity.this, HealthActivity.class);
                    startActivity(heath);
                }
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Activity.this, Save_to_do.class);
                startActivity(intent);
            }
        });
        updatenote();
    }

    private void nexttask() {
        tasknote= NoteDatabase.getInstance(this).getNoteDao().getNotes();
//        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");



        if(tasknote.size()==0){
            tasktitle.setText("You don't have any task" );
            taskcate.setText("");
            tasknotedes.setText("");

        }
        else {
            String task_title=tasknote.get(0).getTitle();
            tasktitle.setText(task_title);
            String task_cate=tasknote.get(0).getCatagoris();
            taskcate.setText("Category: "+task_cate);
            String task_des=tasknote.get(0).getInsertnote();
            tasknotedes.setText(task_des);

            try {
                String dateString = tasknote.get(0).getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                Date date = sdf.parse(dateString);

                long startDate = date.getTime();

//                Log.i("TAG", "nexttask: "+startDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }


            Long now = System.currentTimeMillis();

        }
        getSupportActionBar().setTitle("Dashboard");

    }

    @Override
    public void editbutton(Note note) {
        Save_to_do save_to_do=new Save_to_do();
      boolean editcheck=  save_to_do.isedit=true;
        Intent intent1 = new Intent(Dashboard_Activity.this, Save_to_do.class);
        intent1.putExtra("ID",note.getId());
        intent1.putExtra("title",note.getTitle());
        intent1.putExtra("note",note.getInsertnote());
        intent1.putExtra("Catagoris", note.getCatagoris());
        intent1.putExtra("date",note.getDate());
        intent1.putExtra("time",note.getTime());
        intent1.putExtra("edit",editcheck);
        startActivity(intent1);
        updatenote();
        nexttask();
        finish();
    }

    @Override
    public void deletebutton(Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard_Activity.this);
        builder.setMessage("Do you want delete this user?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            NoteDatabase.getInstance(this).getNoteDao().delete(note);
            updatenote();
            nexttask();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    @Override
    public void onBackPressed() {


        new AlertDialog.Builder(this)
                .setTitle("Alert!")
                .setMessage("Do you want to close this application?")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int  which)    {

                        //do other stuff here
                        dialog.cancel();
                    }
                })
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }


                }).show();
    }

    @Override
    public void checkbutton(Note note) {
        Toast.makeText(this, "Thanks! for complete your task", Toast.LENGTH_SHORT).show();
        NoteDatabase.getInstance(this).getNoteDao().delete(note);
        updatenote();
        nexttask();
    }

    private void updatenote() {
        NoteAdapter adapter = new NoteAdapter(this, NoteDatabase.getInstance(this).getNoteDao().getNotes(), Dashboard_Activity.this);
        task.setText("your have total " +adapter.getItemCount() + " task");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.setAdapter(adapter);
    }


}