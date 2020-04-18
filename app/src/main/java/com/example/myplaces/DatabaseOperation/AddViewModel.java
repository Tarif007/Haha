package com.example.myplaces.DatabaseOperation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AddViewModel extends AndroidViewModel {

    private AddRepository repository;
    private LiveData<List<EntityTable>> allAddress;

    public AddViewModel(@NonNull Application application) {
        super(application);

        repository = new AddRepository(application);
        allAddress = repository.getAllAddress();
    }

    public void insert(EntityTable addr){
        repository.insert(addr);
    }

    public void update(EntityTable addr){
        repository.update(addr);
    }

    public void delete(EntityTable addr){
        repository.delete(addr);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<EntityTable>> getAllAddress(){
        return allAddress;
    }



}
