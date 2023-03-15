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

public class wrokActivity extends AppCompatActivity {
        RecyclerView wrok_recyclerView;
        TextView wroktask;
        List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrok);

        wrok_recyclerView=findViewById(R.id.wrok_recyclerView);
        wroktask=findViewById(R.id.wroktask);

        notes= NoteDatabase.getInstance(wrokActivity.this).getNoteDao().getAllNoteByCatagoris("Work");
        Cate_adapter wrokadapter =new Cate_adapter(wrokActivity.this,notes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        wrok_recyclerView.setLayoutManager(linearLayoutManager);
        wrok_recyclerView.setAdapter(wrokadapter);
        Log.i("TAG", "onCreate: "+notes);

      //  Log.i("TAG", "onCreate: "+notes.size());
        wroktask.setText("You have total "+notes.size()+" task");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Wrok TO DO");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}