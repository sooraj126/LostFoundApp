package com.example.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetailActivity extends AppCompatActivity {

    TextView txtDetailName, txtDetailType, txtDetailCategory, txtDetailPhone,
            txtDetailDescription, txtDetailDate, txtDetailLocation;

    Button btnBack, btnDelete;
    ImageView imgDetail;

    DatabaseHelper dbHelper;
    String itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        dbHelper = new DatabaseHelper(this);

        btnBack = findViewById(R.id.btnBack);
        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailType = findViewById(R.id.txtDetailType);
        txtDetailCategory = findViewById(R.id.txtDetailCategory);
        txtDetailPhone = findViewById(R.id.txtDetailPhone);
        txtDetailDescription = findViewById(R.id.txtDetailDescription);
        txtDetailDate = findViewById(R.id.txtDetailDate);
        txtDetailLocation = findViewById(R.id.txtDetailLocation);
        imgDetail = findViewById(R.id.imgDetail);
        btnDelete = findViewById(R.id.btnDelete);

        itemId = getIntent().getStringExtra("id");

        txtDetailName.setText(getIntent().getStringExtra("name"));
        txtDetailType.setText("Type: " + getIntent().getStringExtra("type"));
        txtDetailCategory.setText("Category: " + getIntent().getStringExtra("category"));
        txtDetailPhone.setText("" + getIntent().getStringExtra("phone"));
        txtDetailDescription.setText("" + getIntent().getStringExtra("description"));
        txtDetailDate.setText("" + getIntent().getStringExtra("timestamp"));
        txtDetailLocation.setText("" + getIntent().getStringExtra("location"));

        String imageUri = getIntent().getStringExtra("image");

        if (imageUri != null && !imageUri.isEmpty()) {
            imgDetail.setImageURI(Uri.parse(imageUri));
        }

        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            boolean deleted = dbHelper.deleteItem(Integer.parseInt(itemId));

            if (deleted) {
                Toast.makeText(this, "Advert removed successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error removing advert", Toast.LENGTH_SHORT).show();
            }
        });
    }
}