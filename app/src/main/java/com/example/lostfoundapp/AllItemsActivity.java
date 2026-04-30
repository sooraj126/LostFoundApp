package com.example.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class AllItemsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    ArrayList<ItemModel> list;
    ItemAdapter adapter;

    Spinner spinnerFilter;
    String[] categories = {"All", "Electronics", "Pets", "Wallets", "Documents", "Keys", "Other"};

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);

        recyclerView = findViewById(R.id.recyclerView);
        dbHelper = new DatabaseHelper(this);


        list = new ArrayList<>();
        adapter = new ItemAdapter(this, list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        spinnerFilter = findViewById(R.id.spinnerFilter);

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                categories
        );

        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerFilter.setAdapter(adapterSpinner);


        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadItems(categories[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadItems(spinnerFilter.getSelectedItem().toString());
    }


    private void loadItems(String selectedCategory) {

        list.clear();

        Cursor cursor = dbHelper.getAllItems();

        while (cursor.moveToNext()) {

            String category = cursor.getString(7);

            if (selectedCategory.equals("All") || category.equals(selectedCategory)) {

                list.add(new ItemModel(
                        cursor.getString(0),
                        cursor.getString(2),
                        cursor.getString(1),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9)
                ));
            }
        }

        cursor.close();

        adapter.notifyDataSetChanged();
    }
}