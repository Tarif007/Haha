package com.example.myplaces.DatabaseOperation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplaces.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DataActivity extends AppCompatActivity {
    public static final int ADD_ADDRESS_REQUEST =1;

    private AddViewModel addViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        FloatingActionButton floatingActionButton = findViewById(R.id.add_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DataActivity.this , AddAddress.class);
                startActivityForResult(intent, ADD_ADDRESS_REQUEST);

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final AddAdapter addAdapter = new AddAdapter();
        recyclerView.setAdapter(addAdapter);



        addViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        addViewModel.getAllAddress().observe(this, new Observer<List<EntityTable>>() {
            @Override
            public void onChanged(List<EntityTable> entityTables) {

                addAdapter.setEntityTables(entityTables);

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                addViewModel.delete(addAdapter.getEntityAt(viewHolder.getAdapterPosition()));
                Toast.makeText(DataActivity.this , "Address deleted" , Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_ADDRESS_REQUEST && resultCode == RESULT_OK){
            String address = data.getStringExtra(AddAddress.EXTRA_ADDRESS);
            String description = data.getStringExtra(AddAddress.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddAddress.EXTRA_PRIORITY , 1);

            EntityTable entityTable = new EntityTable(address,description,priority);
            addViewModel.insert(entityTable);

            Toast.makeText(this, "address saved" , Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "address not saved" , Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                addViewModel.deleteAll();
                Toast.makeText(this, "Deleted all addresses" , Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
