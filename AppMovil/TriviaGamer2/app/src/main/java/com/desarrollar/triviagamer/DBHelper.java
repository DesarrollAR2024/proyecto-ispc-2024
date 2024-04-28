package com.desarrollar.triviagamer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public String getUserPassword(int userId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + PASSWORD_COL + " FROM " + TABLE_NAME + " WHERE " + ID_COL + " = ?", new String[]{String.valueOf(userId)});
        String password = null;
        if (cursor.moveToFirst()) {
            password = cursor.getString(0);
        }
        cursor.close();
        return password;
    }

    public boolean deleteUser(int userId) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(TABLE_NAME, ID_COL + " = ?", new String[]{String.valueOf(userId)});
        return rowsAffected > 0;
    }

    public boolean updateUserPassword(int userId, String newPassword) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD_COL, newPassword);
        int rowsAffected = db.update(TABLE_NAME, values, ID_COL + " = ?", new String[]{String.valueOf(userId)});
        return rowsAffected > 0;
    }

    // Setter para Nombre
    public boolean updateUsername(int userId, String newUsername) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, newUsername);
        int rowsAffected = db.update(TABLE_NAME, values, ID_COL + " = ?", new String[]{String.valueOf(userId)});
        return rowsAffected > 0;
    }

    // Setter para Puntaje
    public boolean updateUserScore(int userId, int newScore) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SCORE_COL, newScore);
        int rowsAffected;
        rowsAffected = db.update(TABLE_NAME, values, ID_COL + " = ?", new String[]{String.valueOf(userId)});
        return rowsAffected > 0;
    }

    public int getUserId(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + ID_COL + " FROM " + TABLE_NAME + " WHERE " + NAME_COL + " = ?", new String[]{username});
        int userId = -1;
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        return userId;
    }

    // Getter para Nombre
    public String getUsername(int userId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + NAME_COL + " FROM " + TABLE_NAME + " WHERE " + ID_COL + " = ?", new String[]{String.valueOf(userId)});
        String username = null;
        if (cursor.moveToFirst()) {
            username = cursor.getString(0);
        }
        cursor.close();
        return username;
    }


    // Getter para Puntaje
    public int getUserScore(int userId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + SCORE_COL + " FROM " + TABLE_NAME + " WHERE " + ID_COL + " = ?", new String[]{String.valueOf(userId)});
        int userScore = -1;
        if (cursor.moveToFirst()) {
            userScore = cursor.getInt(0);
        }
        cursor.close();
        return userScore;
    }


    public static final String DBNAME = "Login.db";

    public static final int DB_VERSION = 10;
    public static final String TABLE_NAME = "users";
    public static final String ID_COL = "id";
    public static final String NAME_COL = "username";
    public static final String PASSWORD_COL = "password";
    public static final String SCORE_COL = "score";

    public DBHelper(Context context) {
        super(context, "Login.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String query="CREATE TABLE "+ TABLE_NAME +" ("+ ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME_COL+" TEXT,"+PASSWORD_COL+" TEXT,"+SCORE_COL+" INTEGER" + ")";

        MyDB.execSQL(query);
        fillUserTable(MyDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);
    }

    public void fillUserTable(SQLiteDatabase MyDB) {
        String[] names = new String[]{"aaa", "bbb", "ccc", "ddd", "eee"};
        String[] passwords = new String[]{"aaa", "bbb", "ccc", "ddd", "eee"};
        int[] scores = new int[]{1366213,564,6848,8494,321};

        for (int i = 0; i < 5; i++) {
            addUsers(MyDB, names[i], passwords[i], scores[i]);
        }

    }

    public void addUsers(SQLiteDatabase MyDB, String username, String password, int... score)
    {

        ContentValues values = new ContentValues();

        values.put(NAME_COL,username);
        values.put(PASSWORD_COL,password);
        values.put(SCORE_COL, score.length > 0 ? score[0] : 0 );

        MyDB.insert(TABLE_NAME,null,values);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COL, username);
        contentValues.put(PASSWORD_COL, password);
        contentValues.put(SCORE_COL, 0);
        long result = MyDB.insert(TABLE_NAME, null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    @SuppressLint("Range")
    public ArrayList<RankingUser> getRankingList() {
        ArrayList<RankingUser> rankingList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor= db.rawQuery("select * from users order by score desc limit 100", null);

        while(cursor.moveToNext()){
            RankingUser user = new RankingUser();
            user.setName(cursor.getString(cursor.getColumnIndex(NAME_COL)));
            user.setScore(cursor.getInt(cursor.getColumnIndex(SCORE_COL)));
            rankingList.add(user);
        }

        cursor.close();
        return rankingList;
    }
}