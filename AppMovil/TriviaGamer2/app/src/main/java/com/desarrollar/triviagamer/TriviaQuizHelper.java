package com.desarrollar.triviagamer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.desarrollar.triviagamer.TriviaQuizContract.*;

import java.util.ArrayList;

public class TriviaQuizHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TriviaQuiz.db";
    private static final int DATBASE_VERSION = 15;

    private SQLiteDatabase db;


    TriviaQuizHelper(Context context) {
        super(context, DATABASE_NAME,null, DATBASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " TEXT, " +
                QuestionTable.COLUMN_CATEGORY + " TEXT " +
                ")";


        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }


    private void fillQuestionsTable()
    {

        TriviaQuestion q1 = new TriviaQuestion(" ¿Como se llama el creador original de la idea de pokemon?","Shigeru miyamoto","Satoshi tajiri","Junichi Masuda","Ken Sugimori","Satoshi tajiri",TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q1);

        TriviaQuestion q2 = new TriviaQuestion("¿Cual es el nombre del hijo de kratos?","Atreus","Sindri","Tyr","Thor","Atreus",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q2);

        TriviaQuestion q3= new TriviaQuestion(" ¿Como se llamaban las maquinas arcade lanzadas por Nintendo con juegos de NES?","Nintendo playchoice-10","Nintendo MMW","Superstar Arcade","Galaxy Game","Nintendo playchoice-10",TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q3);

        TriviaQuestion q4= new TriviaQuestion(" ¿Quien fue el primer S.T.A.R.S. que se enfrento a nemesis?","Lion Kennedy ","Chris Redfield","Jill valentine","Albert Wesker","Jill valentine",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q4);

        TriviaQuestion q5= new TriviaQuestion("¿Como se llama el protagonista de Red Dead Redemption?","susan grimshaw","Sadie Adler","John Marston","Arthur Morgan ","John Marston",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q5);

        TriviaQuestion q6= new TriviaQuestion("¿De que deporte e-sport son pro-players los jugadores Faker , rookie, uzi ?","League of Legends ","Valorant","Csgo","Fortnite","League of Legends ",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q6);


        TriviaQuestion q7= new TriviaQuestion("¿En cual consola salio originalmente el juego “ Leyenda de Zelda : La ocarina del tiempo?","NintendoDS","Gamecube","xbox","Nintendo 64","Nintendo 64",TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q7);

        TriviaQuestion q8= new TriviaQuestion(" ¿Cual es la profesion de vivi en Final Fantasy IX?","Soladado","Mago Negro","Ladron","Mago blanco","Mago Negro",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q8);

        TriviaQuestion q9= new TriviaQuestion("¿Como se llama el primer videojuego del mundo ?","tetris","Pac-Man","Spaces Invaders","Pong","Pong",TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q9);

        TriviaQuestion q10= new TriviaQuestion("¿Que enemigo de Minecraft explota cuando se acerca a su objetivo ? ","Creeper","Blaze","Bruja","Zombie","Creeper",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q10);

        TriviaQuestion q11= new TriviaQuestion("Si llegas al campo de batalla en bus volador , ¿A que juego estas jugando? ","Apex Legends","Valorant","Pubg Mobile","Fornite","Fornite",TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q11);

        TriviaQuestion q12= new TriviaQuestion("¿Como se llama el primer videojuego que se jugo en el espacio exterior?","Ping Pong","Sonic The Hedgehog","Super Mario Bros","Tetris","Tetris",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q12);


    }

    private void addQuestions(TriviaQuestion question){

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_CATEGORY,question.getCategory());
        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }

    public ArrayList<TriviaQuestion> getAllQuestions() {

        ArrayList<TriviaQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };



        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                TriviaQuestion question = new TriviaQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getString(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;
    }

    public ArrayList<TriviaQuestion> getQuestionsWithCategory(String Category) {

        ArrayList<TriviaQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_CATEGORY
        };

        String selection = QuestionTable.COLUMN_CATEGORY + " = ? ";
        String selectionArgs[] = {Category};


        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selectionArgs,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                TriviaQuestion question = new TriviaQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getString(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;
    }
}
