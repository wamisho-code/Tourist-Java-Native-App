package com.example.touristapp.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper {
    private String columnNAME;
    private String TABLE_NAME;
    private static String DATABASE_NAME;
    private static String DATABASE_PATH;
    private String id;
    private final Context context;


    public DatabaseHelper(Context context, String table_name, String columnName) {
        this.context = context;
        DATABASE_NAME = "dbfile.db";
        id=columnName;
        DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
        this.columnNAME = columnName;
        this.TABLE_NAME = table_name;
    }

    public SQLiteDatabase openDatabase() {
        try {
            deleteExistingDatabase();
            copyDatabase();
            System.out.println("Opening database at: " + DATABASE_PATH);
            return SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (IOException e) {
            System.err.println("Error opening database: " + e.getMessage());
            throw new RuntimeException("Error opening database", e);
        }
    }

    private void deleteExistingDatabase() {
        File dbFile = new File(DATABASE_PATH);
        if (dbFile.exists()) {
            dbFile.delete();
            System.out.println("Deleted existing database: " + DATABASE_NAME);
        }
    }

    private void copyDatabase() throws IOException {
        File dbDir = new File(context.getDatabasePath(DATABASE_NAME).getParent());
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }

        InputStream input = context.getAssets().open(DATABASE_NAME);
        OutputStream output = new FileOutputStream(DATABASE_PATH);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
        System.out.println("Database copied successfully to " + DATABASE_PATH);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.openDatabase();
        int intId = Integer.parseInt(id); // Convert the string ID to an integer
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        return db.rawQuery(query, new String[]{String.valueOf(intId)});
    }

}
