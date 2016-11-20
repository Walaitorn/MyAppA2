package com.a23279.egco428.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pam on 11/20/2016.
 */
public class LoginDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allCoumns = {MySQLiteHelper.COLUMN_ID ,MySQLiteHelper.COLUMN_USERNAME,MySQLiteHelper.COLUMN_PASSWORD,MySQLiteHelper.COLUMN_LATI,MySQLiteHelper.COLUMN_LONGI};

    public LoginDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public LoginMessage createFortune(String user,String pass,String lati,String longi){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_USERNAME,user);
        values.put(MySQLiteHelper.COLUMN_PASSWORD,pass);
        values.put(MySQLiteHelper.COLUMN_LATI,lati);
        values.put(MySQLiteHelper.COLUMN_LONGI,longi);

        long insertID = database.insert(MySQLiteHelper.TABLE_LOGIN,null,values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_LOGIN,allCoumns,MySQLiteHelper.COLUMN_ID+" = "+insertID,null,null,null,null);
        cursor.moveToFirst();
        LoginMessage newComment = cursorToComment(cursor);
        cursor.close();

        return newComment;

    }

    // delete first item
    public void deleteComment(LoginMessage comment){
        long id = comment.getId();
        System.out.println("Comment deleted with id: "+id);
        database.delete(MySQLiteHelper.TABLE_LOGIN,MySQLiteHelper.COLUMN_ID+" = "+id,null); // (comment,_id = id,null)
    }


    // open program and load all comment
    public List<LoginMessage> getAllComments(){
        List<LoginMessage> comments = new ArrayList<LoginMessage>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_LOGIN,allCoumns,null,null,null,null,null);
        cursor.moveToFirst();
        // get record and save in comment
        while(!cursor.isAfterLast()){
            LoginMessage comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }

        cursor.close();
        return comments;

    }

    public LoginMessage cursorToComment(Cursor cursor){
        LoginMessage comment = new LoginMessage();
        comment.setId(cursor.getLong(0)); // 0 = first column
        comment.setUsername(cursor.getString(1));
        comment.setPassword(cursor.getString(2));
        comment.setLatitude(cursor.getString(3));
        comment.setLongitude(cursor.getString(4));
        return comment;
    }
}
