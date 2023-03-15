package com.zirwa.timech.Categorie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.zirwa.timech.Adapter.Cate_adapter;
import com.zirwa.timech.Database.Note;
import com.zirwa.timech.Database.NoteDatabase;
import com.zirwa.timech.R;

import java.util.List;

public class HealthActivity extends AppCompatActivity {
    TextView healthtask;
    RecyclerView health_recyclerView;
    List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        healthtask=findViewById(R.id.healthtask);
        health_recyclerView=findViewById(R.id.health_recyclerView);

        notes= NoteDatabase.getInstance(HealthActivity.this).getNoteDao().getAllNoteByCatagoris("health");
        Cate_adapter wrokadapter =new Cate_adapter(HealthActivity.this,notes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        health_recyclerView.setLayoutManager(linearLayoutManager);
        health_recyclerView.setAdapter(wrokadapter);


        //  Log.i("TAG", "onCreate: "+notes.size());
        healthtask.setText("You have total "+notes.size()+" task");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Health TO DO");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}