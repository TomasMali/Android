package com.example.tomas.alarmnotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Mense.Mensa;
import Mense.ServizioMensa;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    public static  String title = "";
    public static  List<Mensa> mense_l = new ArrayList<>();
    private static ListView list_view;

    List<String> stockList = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment df = new TimePickerFragmen();
                df.show(getSupportFragmentManager(), "time picker");
            }
        });

        (findViewById(R.id.button_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });

        startButton();

        findViewById(R.id.imageButtonF).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView();
            }
        });
onSwitch();
    }

    public  List<Boolean> onSwitch() {
        List<Boolean> switches = new ArrayList<>();

switches.add(findViewById(R.id.lunedi).isEnabled());
        switches.add(findViewById(R.id.martedi).isEnabled());
        switches.add(findViewById(R.id.mercoledi).isEnabled());
        switches.add(findViewById(R.id.giovedi).isEnabled());
        switches.add(findViewById(R.id.venerdi).isEnabled());
        switches.add(findViewById(R.id.sabato).isEnabled());
        switches.add(findViewById(R.id.domenica).isEnabled());


        return switches;
    }



    public void listView() {

        stockList.clear();

        for(Mensa m : mense_l)
            stockList.add(m.getMensa() + " ->      Pranzo: " + m.getPranzo() + "       Cena: "+ m.getCena());

        String[] stockArr = new String[stockList.size()];
        stockArr = stockList.toArray(stockArr);



        list_view =  findViewById(R.id.listViewF);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.name_list,stockArr);
        list_view.setAdapter(adapter);


        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        updateTimeText(c);
        startAlarm(c);

    }

    private void startButton() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                          mense_l = ServizioMensa.getMense();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                listView();

                            }
                        });

                    }
                }).start();
            }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);

        if(c.before(Calendar.getInstance()))
            c.add(Calendar.DATE,1);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),alarmManager.INTERVAL_DAY ,pendingIntent);

    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(MainActivity.this,"Notification Canceled",Toast.LENGTH_LONG).show();

    }


    private void updateTimeText(Calendar c) {

        String timeText = "Notification set for ";
        timeText+= DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        Toast.makeText(MainActivity.this,timeText,Toast.LENGTH_LONG).show();

    }
}