package com.zirwa.timech.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zirwa.timech.Database.Note;
import com.zirwa.timech.Database.NoteDatabase;
import com.zirwa.timech.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Save_to_do extends AppCompatActivity {
    public SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MMM yyyy", Locale.US);
    EditText editTitle,editNote;
    TextView datetextview,timetextview;
    ImageView datebtn,timebtn;
    Button button;
    String Title,notedata , value,dt,tm;
    String update_name,update_email,updatevalue,updatedt,updatetm;
    int year,day,month, mHour, mMinute,id;
    Calendar calendar,cal;
    TimePickerDialog   timePickerDialog;
    Spinner spinner;
    String[] Catagoris = {"Work", "personal","shopping","health"};
    int taskid;
    boolean isedit,myedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_do);




        editTitle=findViewById(R.id.Title);
        editNote=findViewById(R.id.Note);
        button=findViewById(R.id.button);
        datetextview=findViewById(R.id.datetextview);
        datebtn=findViewById(R.id.datebtn);
        timetextview=findViewById(R.id.timextview);
        timebtn=findViewById(R.id.timebtn);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,Catagoris);
        spinner.setAdapter(adapter);

        Intent intent1 = getIntent();
        id = intent1.getIntExtra("ID",0);
        String rawtitle=intent1.getStringExtra("title");
        String rawnote=intent1.getStringExtra("note");
        String rawcata=intent1.getStringExtra("Catagoris");
        String rawdate=intent1.getStringExtra("date");
        String rawtime=intent1.getStringExtra("time");
        myedit=intent1.getBooleanExtra("edit", false);
        editTitle.setText(rawtitle);
        editNote.setText(rawnote);
        datetextview.setText(rawdate);
        timetextview.setText(rawtime);

        update();
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                calendar = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
               year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        Save_to_do.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                datetextview.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);

                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });


        timebtn.setOnClickListener(view -> {
             cal = Calendar.getInstance();
            mHour = cal.get(Calendar.HOUR_OF_DAY);
            mMinute = cal.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
         timePickerDialog = new TimePickerDialog(this,
                    (view12, hourOfDay, minute) -> {
                        timetextview.setText(getTime(hourOfDay, minute));
                        timePickerDialog.dismiss();
                    }, mHour, mMinute, false);

                   // cal.getTimeInMillis();
            timePickerDialog.show();


        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myedit==true){
                    update();
                    Toast.makeText(Save_to_do.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Save_to_do.this, Dashboard_Activity.class));
                    finish();
                }
                else {
                    insertdata();
                }
               // setalarm();

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("TO DO");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void settask(int taskid, boolean isedit){

        this.taskid=taskid;
        this.isedit=isedit;
    }

    private void update() {
        update_name= editTitle.getText().toString();
        update_email= editNote.getText().toString();
        updatevalue = spinner.getSelectedItem().toString();
        updatedt=datetextview.getText().toString();
        updatetm=timetextview.getText().toString();

        Note note =new Note();
        note.setId(id);
        note.setTitle(update_name);
        note.setInsertnote(update_email);
        note.setCatagoris(updatevalue);
        note.setDate(updatedt);
        note.setTime(updatetm);
        NoteDatabase.getInstance(Save_to_do.this).getNoteDao().update(note);
    }

    private String getTime(int hourOfDay, int minute) {
        Time tme = new Time(hourOfDay, minute, 0);//seconds by default set to zero
       SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(tme);
    }


    private void insertdata() {
        Title= editTitle.getText().toString();
        notedata= editNote.getText().toString();
        value = spinner.getSelectedItem().toString();
        dt=datetextview.getText().toString();
        tm=timetextview.getText().toString();


        if(Title.length()==0){
            Toast.makeText(this, "Title can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(notedata.length()==0){
            Toast.makeText(this, "Note can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        else  {
            Note note = new Note();
            note.setTitle(Title);
            Log.i("TAG", "checkingtitle: "+Title.length());
            note.setInsertnote(notedata);
            note.setCatagoris(value);
            note.setDate(dt);
            note.setTime(tm);
            NoteDatabase.getInstance(this).getNoteDao().insert(note);
            Toast.makeText(Save_to_do.this, "Saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Save_to_do.this, Dashboard_Activity.class));
            finish();
        }
    }
}