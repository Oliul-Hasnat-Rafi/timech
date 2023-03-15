package com.zirwa.timech.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.zirwa.timech.R;

public class StartActivity extends AppCompatActivity {
    Button getbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getbtn = findViewById(R.id.getbtn);


//        getbtn.setOnClickListener(view -> {
//
//            Intent intent =new Intent(StartActivity.this, Dashboard_Activity.class);
//            startActivity(intent);
//
//        });

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun ==true) {
            getbtn.setOnClickListener(view -> {
            Intent intent =new Intent(StartActivity.this, Dashboard_Activity.class);
            startActivity(intent);
            finish();

        });
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

        if (isFirstRun==false) {
            Intent a =new Intent(StartActivity.this, Dashboard_Activity.class);
            startActivity(a);
            finish();
        }









    }
}