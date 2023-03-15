package com.zirwa.timech.Categorie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.zirwa.timech.Adapter.Cate_adapter;
import com.zirwa.timech.Database.Note;
import com.zirwa.timech.Database.NoteDatabase;
import com.zirwa.timech.R;

import java.util.List;

public class PersonalActivity extends AppCompatActivity {
    RecyclerView personal_recyclerView;
    TextView personaltask;
    List<Note> personalnote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        personal_recyclerView=findViewById(R.id.personal_recyclerView);
        personaltask=findViewById(R.id.personalask);

        personalnote= NoteDatabase.getInstance(PersonalActivity.this).getNoteDao().getAllNoteByCatagoris("personal");
        Cate_adapter personal =new Cate_adapter(PersonalActivity.this,personalnote);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        personal_recyclerView.setLayoutManager(linearLayoutManager);
        personal_recyclerView.setAdapter(personal);
        Log.i("TAG", "onCreate: "+personalnote);

        //  Log.i("TAG", "onCreate: "+notes.size());
        personaltask.setText("You have total "+personalnote.size()+" task");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Personal TO DO");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }



}