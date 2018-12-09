package com.example.tommal.sqliteapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {
private static ListView list_view;
String[] NAMES = new String[]{
  "Tomas","Mali","Nela","Esme","Spiro"
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        displayListView();
        doNotifyMe();

    }

    public void doNotifyMe() {
        ((Button) findViewById(R.id.buttonNotifyMe)).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                PendingIntent pendingIntent = PendingIntent.getActivity(ListViewActivity.this,0,intent,0);
                Notification noti = new Notification.Builder(ListViewActivity.this)
                        .setTicker("TickerTitle")
                        .setContentTitle("ContentText")
                        .setContentText("This is the text which be appear here..")
                        .setSmallIcon(R.mipmap.my_2)
                        .addAction(R.mipmap.my_2, "Action 1", pendingIntent)
                        .setContentIntent(pendingIntent).getNotification();

                noti.flags = Notification.FLAG_AUTO_CANCEL;

                NotificationManager nm =    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(0,noti);
            }
        });


    }


    public void displayListView(){
        list_view = (ListView) findViewById(R.id.my_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.name_list,NAMES);
        list_view.setAdapter(adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) list_view.getItemAtPosition(position);
                Toast.makeText(ListViewActivity.this, "Position : " + position + " Value : " + value,  Toast.LENGTH_LONG).show();
            }
        });
    }


}
