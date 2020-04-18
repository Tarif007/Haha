package com.example.myplaces.DatabaseOperation;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = EntityTable.class , version = 1)
public abstract class AddressDatabase extends RoomDatabase {

    private static AddressDatabase addressDatabase;
    public abstract AddDao addDao();

    public static synchronized AddressDatabase getInstance(Context context){
        if (addressDatabase == null){
            addressDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AddressDatabase.class , "addressDatabase")
                    .fallbackToDestructiveMigration().addCallback(roomCallback)
                    .build();
        }

        return addressDatabase;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(addressDatabase).execute();
        }
    };


    private static class PopulateAsyncTask extends AsyncTask<Void , Void,Void> {

        private AddDao addDao;
        public PopulateAsyncTask(AddressDatabase database) {
            addDao = database.addDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            addDao.insert(new EntityTable("Title 1" , "description 1" , 1));
            addDao.insert(new EntityTable("Title 2" , "description 2" ,2 ));
            addDao.insert(new EntityTable("Title 3" , "description 3" , 3));
            return null;
        }
    }
}
