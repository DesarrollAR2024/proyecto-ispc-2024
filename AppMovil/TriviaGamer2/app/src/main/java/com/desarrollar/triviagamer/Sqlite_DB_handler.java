package com.desarrollar.triviagamer;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Sqlite_DB_handler extends SQLiteOpenHelper {
    private static final String DB_NAME = "users_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String PASSWORD_COL = "password";
    private static final String SCORE_COL = "score";


    public Sqlite_DB_handler(Context context)
    {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+ TABLE_NAME +" ("+ ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME_COL+" TEXT,"+PASSWORD_COL+" TEXT,"+SCORE_COL+" INTEGER" + ")";

        db.execSQL(query);
        fillUserTable();
    }

    public void fillUserTable() {
        String[] names = new String[]{"aaa", "bbb", "ccc", "ddd", "eee"};
        String[] passwords = new String[]{"aaa", "bbb", "ccc", "ddd", "eee"};
        int[] scores = new int[]{13213,564,6848,8494,321};

        for (int i = 0; i < 5; i++) {
            addUsers(names[i], passwords[i], scores[i]);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void addUsers(String username, String password, int... score)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL,username);
        values.put(PASSWORD_COL,password);
        values.put(SCORE_COL, score.length > 0 ? score[0] : 0 );

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public boolean checkUsernamePassword(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from users where name = ? and password = ?", new String[]{username,password});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else return false;
    }

    @SuppressLint("Range")
    public ArrayList<RankingUser> getRankingList() {
        ArrayList<RankingUser> rankingList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor= db.rawQuery("select * from users order by score desc", null);

        while(cursor.moveToNext()){
            RankingUser user = new RankingUser();
            user.setName(cursor.getString(cursor.getColumnIndex(NAME_COL)));
            user.setScore(cursor.getInt(cursor.getColumnIndex(SCORE_COL)));
            rankingList.add(user);
        }

        cursor.close();
        return rankingList;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
