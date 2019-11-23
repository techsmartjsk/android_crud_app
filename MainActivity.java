package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.lang.String;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class java extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Users.db";//Users.db can be fetched by using Device File Explorer(View > Tool windows > Device File Explorer).
    public static final String TABLE_NAME = "Data";//Inside Device File Explorer data>data>Package_name>databases>Users.db
    public static final String COL_1="NAME";
    public static final String COL_2="LOCATION";
    public static final String COL_3="INTERESTS";
    public static final String COL_4 ="ID";


    public java(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+TABLE_NAME+ "(NAME TEXT,LOCATION TEXT,INTERESTS TEXT ,ID INTEGER PRIMARY KEY AUTOINCREMENT )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
onCreate(db);

    }
    public boolean insertData(String name, String location,String interests){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,name);
            contentValues.put(COL_2,location);
            contentValues.put(COL_3,interests);
            long result = db.insert(TABLE_NAME,null,contentValues);
            if(result == -1){
                return false;
            }else{
                return true;
            }

    }
public Cursor view(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
}
    public boolean updateData(String name, String location,String interests,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,location);
        contentValues.put(COL_3,interests);
        contentValues.put(COL_4,id);
        db.update(TABLE_NAME,contentValues,"ID= ?",new String[] {id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});

    }

}