package com.desarrollar.triviagamer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Atributos de clase
    public static final String AVATAR_COL = "avatar";

    // Constantes de la base de datos
    public static final String DBNAME = "Login.db";
    public static final int DB_VERSION = 12;
    public static final String TABLE_NAME = "users";
    public static final String ID_COL = "id";
    public static final String NAME_COL = "username";
    public static final String PASSWORD_COL = "password";
    public static final String SCORE_COL = "score";
    private static final String PLAYED_COL = "played";

    // Constructores
    public DBHelper(Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }

    // Métodos de instancia
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

    public boolean updateUserProfile(int userId, String newUsername, String selectedAvatar) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, newUsername);
        values.put(AVATAR_COL, selectedAvatar); // Si tienes un campo para el avatar en la tabla de usuarios
        int rowsAffected = db.update(TABLE_NAME, values, ID_COL + " = ?", new String[]{String.valueOf(userId)});
        return rowsAffected > 0;
    }

    public void incrementPlayCount(int userId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        MyDB.execSQL("UPDATE users SET played = played + 1 WHERE id = ?", new String[]{String.valueOf(userId)});
        Cursor c = MyDB.rawQuery("select played from users where id = ?", new String[]{String.valueOf(userId)});
        if (c.moveToFirst()) {
            int played = c.getInt(0);
        }
    }

    public RankingUser getUserDetails(int userId) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where id = ?", new String[]{String.valueOf(userId)});

        if (cursor.getCount() == 1 && cursor.moveToFirst()) {
            int nameCol = cursor.getColumnIndex(NAME_COL) > 0 ? cursor.getColumnIndex(NAME_COL) : 0;
            int scoreCol = cursor.getColumnIndex(SCORE_COL) > 0 ? cursor.getColumnIndex(SCORE_COL) : 0;
            int playedCol = cursor.getColumnIndex(PLAYED_COL) > 0 ? cursor.getColumnIndex(PLAYED_COL) : 0;
            int idCol = cursor.getColumnIndex(ID_COL) > 0 ? cursor.getColumnIndex(ID_COL) : 0;

            RankingUser user = new RankingUser(cursor.getString(nameCol), cursor.getInt(scoreCol), cursor.getInt(playedCol), cursor.getInt(idCol));

            return user;
        }
        return new RankingUser();
    }

    @SuppressLint("Range")
    public ArrayList<RankingUser> getRankingList() {
        ArrayList<RankingUser> rankingList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from users order by score desc limit 100", null);

        while (cursor.moveToNext()) {
            RankingUser user = new RankingUser();
            user.setName(cursor.getString(cursor.getColumnIndex(NAME_COL)));
            user.setScore(cursor.getInt(cursor.getColumnIndex(SCORE_COL)));
            rankingList.add(user);
        }

        cursor.close();
        return rankingList;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COL + " TEXT, " +
                PASSWORD_COL + " TEXT, " +
                SCORE_COL + " INTEGER, " +
                PLAYED_COL + " INTEGER, " +
                AVATAR_COL + " TEXT)";
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
        int[] scores = new int[]{1366213, 564, 6848, 8494, 321};
        int[] played = new int[]{1305, 20, 75, 125, 15};

        for (int i = 0; i < 5; i++) {
            addUsers(MyDB, names[i], passwords[i], scores[i], played[i]);
        }

    }

    public void addUsers(SQLiteDatabase MyDB, String username, String password, int score, int played) {

        ContentValues values = new ContentValues();

        values.put(NAME_COL, username);
        values.put(PASSWORD_COL, password);
        values.put(SCORE_COL, score);
        values.put(PLAYED_COL, played);

        MyDB.insert(TABLE_NAME, null, values);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COL, username);
        contentValues.put(PASSWORD_COL, password);
        contentValues.put(SCORE_COL, 0);
        contentValues.put(PLAYED_COL, 0);
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

    public Boolean checkEmail(String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
