package com.example.lostfoundapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateAdvertActivity extends AppCompatActivity {

    RadioGroup radioGroupType;
    Spinner spinnerCategory;
    EditText etName, etPhone, etDescription, etDate, etLocation;
    Button btnUploadImage, btnSave;
    ImageView imgPreview;

    DatabaseHelper dbHelper;
    String imageUri = "";

    String[] categories = {"Electronics", "Pets", "Wallets", "Documents", "Keys", "Other"};

    private final ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

                if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                    Uri selectedImageUri = result.getData().getData();

                    if (selectedImageUri != null) {

                        final int takeFlags = result.getData().getFlags()
                                & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                        getContentResolver().takePersistableUriPermission(selectedImageUri, takeFlags);

                        imageUri = selectedImageUri.toString();

                        imgPreview.setImageURI(selectedImageUri);
                        imgPreview.setVisibility(View.VISIBLE);

                        Toast.makeText(this, "Image selected", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        dbHelper = new DatabaseHelper(this);

        radioGroupType = findViewById(R.id.radioGroupType);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        etLocation = findViewById(R.id.etLocation);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnSave = findViewById(R.id.btnSave);
        imgPreview = findViewById(R.id.imgPreview);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                categories
        );

        categoryAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        etDate.setOnClickListener(v -> showDatePicker());

        btnUploadImage.setOnClickListener(v -> openGallery());

        btnSave.setOnClickListener(v -> saveAdvert());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        imagePickerLauncher.launch(intent);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    etDate.setText(date);
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }

    private void saveAdvert() {

        int selectedId = radioGroupType.getCheckedRadioButtonId();

        String type = "";

        if (selectedId == R.id.radioLost) {
            type = "Lost";
        } else if (selectedId == R.id.radioFound) {
            type = "Found";
        }

        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();

        String timestamp = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss",
                Locale.getDefault()
        ).format(new Date());

        if (type.isEmpty() || name.isEmpty() || phone.isEmpty()
                || description.isEmpty() || date.isEmpty()
                || location.isEmpty() || imageUri.isEmpty()) {

            Toast.makeText(this, "Please fill all details and upload image", Toast.LENGTH_SHORT).show();

        } else {

            boolean inserted = dbHelper.insertItem(
                    type, name, phone, description,
                    date, location, category, imageUri, timestamp
            );

            if (inserted) {
                Toast.makeText(this, "Advert Saved Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error saving advert", Toast.LENGTH_SHORT).show();
            }
        }
    }
}