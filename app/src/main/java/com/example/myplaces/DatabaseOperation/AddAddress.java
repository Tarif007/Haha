package com.example.myplaces.DatabaseOperation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myplaces.R;

public class AddAddress extends AppCompatActivity {


    public static final String EXTRA_ADDRESS = "com.example.haha.EXTRA_ADDRESS";
    public static final String EXTRA_DESCRIPTION = "com.example.haha.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.haha.EXTRA_PRIORITY";




    private EditText editTextAddress;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        editTextAddress = findViewById(R.id.edit_add);
        editTextDescription = findViewById(R.id.edit_des);
        numberPickerPriority = findViewById(R.id.picker);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Address");
    }

    private void saveAddress(){
        String address = editTextAddress.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (address.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "please insert a address and description" , Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_ADDRESS , address);
        data.putExtra(EXTRA_DESCRIPTION , description);
        data.putExtra(EXTRA_PRIORITY , priority);

        setResult(RESULT_OK , data);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_address:
                saveAddress();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
