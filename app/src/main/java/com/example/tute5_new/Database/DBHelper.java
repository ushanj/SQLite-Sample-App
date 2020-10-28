package com.example.tute5_new.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public  static final  String  DATABASE_NAME="userInfo.db";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES=
                "CREATE TABLE "+ UsersMasters.Users.TABLE_NAME + " (" +
                        UsersMasters.Users._ID + " INTEGER PRIMARY KEY," +
                        UsersMasters.Users.COLUMN_NAME_USERNAME + " TEXT," +
                        UsersMasters.Users.COLUMN_NAME_PASSWORD + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    public long addInfo(String userName,String password){
        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(UsersMasters.Users.COLUMN_NAME_USERNAME,userName);
        values.put(UsersMasters.Users.COLUMN_NAME_PASSWORD,password);

        long newRowID=db.insert(UsersMasters.Users.TABLE_NAME,null,values);
        return  newRowID;
    }

    public List readAllInfo(String req){
        SQLiteDatabase db=getReadableDatabase();
        String [] projection= {
                UsersMasters.Users._ID,
                UsersMasters.Users.COLUMN_NAME_USERNAME,
                UsersMasters.Users.COLUMN_NAME_PASSWORD
        };
        String sortOrder = UsersMasters.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor=db.query(
                UsersMasters.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List userNames=new ArrayList<>();
        List passwords=new ArrayList<>();


        while(cursor.moveToNext()){
            String username=cursor.getString(cursor.getColumnIndexOrThrow(UsersMasters.Users.COLUMN_NAME_USERNAME));
            String password=cursor.getString(cursor.getColumnIndexOrThrow(UsersMasters.Users.COLUMN_NAME_PASSWORD));
            userNames.add(username);
            passwords.add(password);
        }
        cursor.close();
        Log.i("msg","readAllInfo; "+userNames);
        if (req=="user"){
            return  userNames;
        }
        else if(req=="password"){
            return  passwords;
        }
        else {
            return  null;
        }
    }


    public  void deleteInfo(String userName){
        SQLiteDatabase db=getReadableDatabase();
        String selection= UsersMasters.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs={ userName };
        db.delete(UsersMasters.Users.TABLE_NAME,selection,selectionArgs);

    }

    public void updateInfo(String userName,String password){
        SQLiteDatabase db=getReadableDatabase();

        ContentValues values=new ContentValues();
        values.put(UsersMasters.Users.COLUMN_NAME_PASSWORD,password);

        String selection= UsersMasters.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs={ userName };

        int count=db.update(
                UsersMasters.Users.TABLE_NAME,
                values,
                selection,selectionArgs
        );
    }





}
