package com.example.myplaces.DatabaseOperation;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AddRepository {

    private AddDao addDao;
    private LiveData<List<EntityTable>> allAddress;

    public AddRepository(Application application){
        AddressDatabase addressDatabase = AddressDatabase.getInstance(application);
        addDao = addressDatabase.addDao();
        allAddress = addDao.getAll();

    }
   public void insert(EntityTable addr){

        new InsertAddressAsyncTask(addDao).execute(addr);

   }

   public void update(EntityTable addr){
        new UpdateAddressAsyncTask(addDao).execute(addr);

   }

   public void delete(EntityTable addr){
        new DeleteAddressAsyncTask(addDao).execute(addr);

   }

   public void deleteAll(){
        new DeleteAllAddressAsyncTask(addDao).execute();
   }

    public androidx.lifecycle.LiveData<List<EntityTable>> getAllAddress() {
        return allAddress;
    }


    private static class InsertAddressAsyncTask extends AsyncTask<EntityTable , Void,Void>{

        private AddDao addDao;
        public InsertAddressAsyncTask(AddDao addDao) {
            this.addDao = addDao;
        }


        @Override
        protected Void doInBackground(EntityTable... entityTables) {
            addDao.insert(entityTables[0]);
            return null;
        }
    }



    private static class UpdateAddressAsyncTask extends AsyncTask<EntityTable , Void,Void>{

        private AddDao addDao;
        public UpdateAddressAsyncTask(AddDao addDao) {
            this.addDao = addDao;
        }


        @Override
        protected Void doInBackground(EntityTable... entityTables) {
            addDao.update(entityTables[0]);
            return null;
        }
    }


    private static class DeleteAddressAsyncTask extends AsyncTask<EntityTable , Void,Void>{

        private AddDao addDao;
        public DeleteAddressAsyncTask(AddDao addDao) {
            this.addDao = addDao;
        }


        @Override
        protected Void doInBackground(EntityTable... entityTables) {
            addDao.delete(entityTables[0]);
            return null;
        }
    }


    private static class DeleteAllAddressAsyncTask extends AsyncTask<Void , Void,Void>{

        private AddDao addDao;
        public DeleteAllAddressAsyncTask(AddDao addDao) {
            this.addDao = addDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            addDao.deleteAll();
            return null;
        }
    }


}
