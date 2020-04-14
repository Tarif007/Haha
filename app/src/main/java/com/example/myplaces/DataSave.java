package com.example.myplaces;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DataSave extends AppCompatActivity {
    Button btnAdd, btnClear;
    ListView lstView;
    EditText addressName;
    DatabaseHelper dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_save);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnClear = (Button) findViewById(R.id.btnClear);
        addressName = (EditText) findViewById(R.id.locationName);
        lstView = (ListView) findViewById(R.id.lstView);

        FillList();
    }

    public void AddData(View v) {
        try {
            if (TextUtils.isEmpty(addressName.getText().toString()))
                Toast.makeText(this, "Please enter address name", Toast.LENGTH_SHORT).show();
            else {
                dbController = new DatabaseHelper(this);
                String s = dbController.InsertData(addressName.getText().toString());
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                FillList();
                addressName.setText("");
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void ClearData(View v) {
        lstView.setAdapter(null);

    }

    public void FillList() {
        try {
            int[] id = {R.id.txtListElement};
            String[] addressName = new String[]{"address"};
            if (dbController == null)
                dbController = new DatabaseHelper(this);
            SQLiteDatabase sqlDb = dbController.getReadableDatabase();
            Cursor c = dbController.getAddress();

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    R.layout.list_template, c, addressName, id, 0);
            lstView.setAdapter(adapter);

        } catch (Exception ex) {
            Toast.makeText(DataSave.this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

}