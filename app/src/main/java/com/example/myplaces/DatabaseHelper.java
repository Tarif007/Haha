package com.example.myplaces;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOGCAT = null;

    public DatabaseHelper(Context applicationcontext) {
        super(applicationcontext, "location.db", null, 1);
        Log.d(LOGCAT, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS location ( _id INTEGER PRIMARY KEY, address TEXT)";
        database.execSQL(query);
    }

    public String InsertData(String address) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String query = "insert into location (address) values ('" + address + "')";
            database.execSQL(query);
            database.close();
            return "Added Successfully";
        } catch (Exception ex) {
            return ex.getMessage().toString();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old,
                          int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS location";
        database.execSQL(query);
        onCreate(database);
    }

    public Cursor getAddress() {
        try {
            String selectQuery = "SELECT * FROM location order by _id desc";
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            return cursor;
        } catch (Exception ex) {
            return null;
        }
    }
}