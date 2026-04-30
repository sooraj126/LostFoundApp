package com.example.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCreateAdvert, btnShowAllItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCreateAdvert = findViewById(R.id.btnCreateAdvert);
        btnShowAllItems = findViewById(R.id.btnShowAllItems);


        btnCreateAdvert.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CreateAdvertActivity.class));
        });


        btnShowAllItems.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AllItemsActivity.class));
        });
    }
}