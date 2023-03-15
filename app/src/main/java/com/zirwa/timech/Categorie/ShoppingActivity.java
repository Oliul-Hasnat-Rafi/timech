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

public class ShoppingActivity extends AppCompatActivity {
    RecyclerView shopping_recyclerView;
    TextView shoppingtask;
    List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        shopping_recyclerView=findViewById(R.id.shopping_recyclerView);
        shoppingtask=findViewById(R.id.shoppingtask);

        notes= NoteDatabase.getInstance(ShoppingActivity.this).getNoteDao().getAllNoteByCatagoris("shopping");
        Cate_adapter wrokadapter =new Cate_adapter(ShoppingActivity.this,notes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        shopping_recyclerView.setLayoutManager(linearLayoutManager);
        shopping_recyclerView.setAdapter(wrokadapter);


        //  Log.i("TAG", "onCreate: "+notes.size());
        shoppingtask.setText("You have total "+notes.size()+" task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shopping TO DO");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}