package com.example.tommal.sqliteapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainSecond extends AppCompatActivity {
private static Button btnSwe ;
private static ImageView img1;
    private static ImageView img2;
    private int current_immages_index;
    int [] immages = {R.mipmap.my_img,R.mipmap.ic_launcher, R.mipmap.my_2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
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

        changeImg();

    }


    private void changeImg() {
        btnSwe = (Button) findViewById(R.id.buttonSwe);
        img1 = (ImageView) findViewById(R.id.imageView1);


        btnSwe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_immages_index++;
                current_immages_index = current_immages_index % immages.length;
                img1.setImageResource(immages[current_immages_index]);
            }
        });

    }

}
