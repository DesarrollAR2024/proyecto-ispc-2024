package com.desarrollar.triviagamer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.desarrollar.triviagamer.TriviaQuizContract.*;

import java.util.ArrayList;

public class TriviaQuizHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TriviaQuiz.db";
    private static final int DATBASE_VERSION = 18;

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

        TriviaQuestion q1 = new TriviaQuestion("¿Como se llama el creador original de la idea de pokemon?","Shigeru Miyamoto","Satoshi tajiri","Junichi Masuda","Ken Sugimori","Satoshi tajiri",TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q1);

        TriviaQuestion q2 = new TriviaQuestion("¿Cual es el nombre del hijo de kratos?","Atreus","Sindri","Tyr","Thor","Atreus",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q2);

        TriviaQuestion q3= new TriviaQuestion("¿Como se llamaban las maquinas arcade lanzadas por Nintendo con juegos de NES?","Nintendo playchoice-10","Nintendo MMW","Superstar Arcade","Galaxy Game","Nintendo playchoice-10",TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q3);

        TriviaQuestion q4= new TriviaQuestion("¿Quien fue el primer S.T.A.R.S. que se enfrento a nemesis?","Lion Kennedy ","Chris Redfield","Jill Valentine","Albert Wesker","Jill valentine",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q4);

        TriviaQuestion q5= new TriviaQuestion("¿Como se llama el protagonista de Red Dead Redemption?","Susan Grimshaw","Sadie Adler","John Marston","Arthur Morgan ","John Marston",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q5);

        TriviaQuestion q6= new TriviaQuestion("¿De que deporte e-sport son pro-players los jugadores Faker , Rookie, Uzi ?","League of Legends ","Valorant","Csgo","Fortnite","League of Legends ",TriviaQuestion.CATEGORY_JUEGOSDEROL);
        addQuestions(q6);

        TriviaQuestion q7= new TriviaQuestion("¿En cual consola salio originalmente el juego “ Leyenda de Zelda : La ocarina del tiempo?","NintendoDS","Gamecube","Xbox","Nintendo 64","Nintendo 64",TriviaQuestion.CATEGORY_JUEGOSDEROL);
        addQuestions(q7);

        TriviaQuestion q8= new TriviaQuestion("¿Cual es la profesion de vivi en Final Fantasy IX?","Soladado","Mago Negro","Ladron","Mago blanco","Mago Negro",TriviaQuestion.CATEGORY_JUEGOSDEROL);
        addQuestions(q8);

        TriviaQuestion q9= new TriviaQuestion("¿Como se llama el primer videojuego del mundo ?","Tetris","Pac-Man","Spaces Invaders","Pong","Pong",TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q9);

        TriviaQuestion q10= new TriviaQuestion("¿Que enemigo de Minecraft explota cuando se acerca a su objetivo ? ","Creeper","Blaze","Bruja","Zombie","Creeper",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q10);

        TriviaQuestion q11= new TriviaQuestion("Si llegas al campo de batalla en bus volador , ¿A que juego estas jugando? ","Apex Legends","Valorant","Pubg Mobile","Fornite","Fornite",TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q11);

        TriviaQuestion q12= new TriviaQuestion("¿Como se llama el primer videojuego que se jugo en el espacio exterior?","Ping Pong","Sonic The Hedgehog","Super Mario Bros","Tetris","Tetris",TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q12);

        TriviaQuestion q13= new TriviaQuestion("¿Cuál es el videojuego más caro de todos los tiempos?", "Super Mario", "Pokemon", "Grand Theft Auto V", "Minecraft", "Grand Theft Auto V", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q13);

        TriviaQuestion q14= new TriviaQuestion("¿Cuál es la consola de videojuegos más vendida de todos los tiempos?", "PlayStation 1", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 2", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q14);

        TriviaQuestion q15= new TriviaQuestion("¿Cuál fue el primer trabajo de Super Mario?", "Ingeniero", "Granjero", "Profesor", "Carpintero", "Carpintero", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q15);

        TriviaQuestion q16= new TriviaQuestion("¿Cómo se llama el fantasma naranja en el videojuego Pac-Man?", "Jason", "Clyde", "Mark", "Daryn", "Clyde", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q16);

        TriviaQuestion q17= new TriviaQuestion("¿Cuál es el videojuego más vendido de todos los tiempos?", "Super Mario", "Minecraft", "Game of Thrones", "Dota", "Minecraft", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q17);

        TriviaQuestion q18= new TriviaQuestion("¿Qué videojuego de deportes más popular fue nombrado en honor a un entrenador profesional?", "John Madden", "Wii Sports", "Tetris", "Fifa 18", "John Madden", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q18);

        TriviaQuestion q19= new TriviaQuestion("¿Qué compañía colabora con SONY para la Playstation?", "Game Freak", "NAMCO", "PUBG Corp", "Nintendo", "Nintendo", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q19);

        TriviaQuestion q20= new TriviaQuestion("¿Cómo se llama el mundo de Super Mario?", "El Mundo Dinosaurio", "El Reino Champiñón", "El Dragonverso", "El Reino del Árbol", "El Mundo Champiñón", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q20);

        TriviaQuestion q21= new TriviaQuestion("¿Cuál fue la marca del primer casco de realidad virtual?", "Apple", "Samsung", "Eyephone", "LG", "Eyephone", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q21);

        TriviaQuestion q22= new TriviaQuestion("Qué juego introdujo por primera vez al mundo las travesuras de un gorila lanzador de barriles?", "Donkey Kong Country", "Mario Kart", "Banjo-Kazooie", "King of the Jungle", "Donkey Kong Country", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q22);

        TriviaQuestion q23= new TriviaQuestion("¿En qué icónico juego los jugadores luchan contra hordas de muertos vivientes en Raccoon City?", "Left 4 Dead", "Silent Hill", "Resident Evil", "Zombieville", "Resident Evil", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q23);

        TriviaQuestion q24= new TriviaQuestion("¿Cómo se llama el villano intergaláctico en la serie Metroid?", "Mother Brain", "King Koopa", "Dr. Wily", "Dark Samus", "Mother Brain", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q24);

        TriviaQuestion q25= new TriviaQuestion("¿A quién juegas en la serie de The Legend of Zelda?", "zelda", "Link", "Ganondorf", "Espona", "Link", TriviaQuestion.CATEGORY_JUEGOSDEROL);
        addQuestions(q25);

        TriviaQuestion q26= new TriviaQuestion("¿Qué sistema de juego introdujo primero a los jugadores al juego controlado por movimiento?", "Xbox Kinect", "Nintendo Wii", "PlayStation Move", "Sega Activator", "Nintendo Wii", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q26);

        TriviaQuestion q27= new TriviaQuestion("¿Cuál es el nombre del primer videojuego que se jugó en el espacio exterior?", "Atari ET", "Starcraft", "Star Wars", "Fortnite", "Starcraft", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q27);

        TriviaQuestion q28= new TriviaQuestion("Qué videojuego fue creado y diseñado para tratar la depresión?", "Joy", "Therapex", "Smilez", "Sparx", "Sparx", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q28);

        TriviaQuestion q29= new TriviaQuestion("En Call Of Duty: Advanced Warfare, ¿cómo se llama el mejor amigo de Jack Mitchell?", "Will Smith", "Will Turner", "Will Irons", "Will Cane", "Will Irons", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q29);

        TriviaQuestion q30= new TriviaQuestion("¿De qué deporte trata Fight Night 2004?", "Béisbol", "Baloncesto", "Boxeo", "Billar", "Boxeo", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q30);

        TriviaQuestion q31= new TriviaQuestion("¿Qué compañía lanzó y diseñó Pacman?", "Nintendo", "Sony", "Game Freak", "NAMCO", "NAMCO", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q31);

        TriviaQuestion q32= new TriviaQuestion("¿Cuál fue la primera consola de videojuegos fabricada en América?", "Sega Genesis", "Playstation Switch", "Xbox", "Nintendo Switch", "Xbox", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q32);

        TriviaQuestion q33= new TriviaQuestion("¿Quién es el dueño del castillo en Castlevania?", "Rey Felix", "Conde Drácula", "Príncipe Hector", "Conde de Montecristo", "Conde Drácula", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q33);

        TriviaQuestion q34= new TriviaQuestion("¿Cuál fue el primer juego 3D de la historia?", "Super Mario", "Pac-Man", "Battlezone", "Call of Duty", "Battlezone", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q34);

        TriviaQuestion q35= new TriviaQuestion("¿Cuál fue la segunda consola de videojuegos de Xbox creada por Microsoft?", "Xbox 360", "Xbox Series X", "Xbox Series S", "Xbox One X", "Xbox 360", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q35);

        TriviaQuestion q36= new TriviaQuestion("¿Cómo se llama el periférico de detección de movimiento para Xbox 360 y Xbox One?", "Connect", "Physical", "The Kinect", "Rockband", "The Kinect", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q36);

        TriviaQuestion q37= new TriviaQuestion("¿Qué intrépido cazador de tesoros hizo su debut en PlayStation en 1996?", "Nathan Drake", "Lara Croft", "Indiana Jones", "Rico Rodriguez", "Lara Croft", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q37);

        TriviaQuestion q38= new TriviaQuestion("¿Cuál es la puntuación más alta que puedes lograr en una sola ronda de Pac-Man?", "1.000 puntos", "3.333 puntos", "16.000 puntos", "20.000 puntos", "3.333 puntos", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q38);

        TriviaQuestion q39= new TriviaQuestion("¿Qué famoso juego obligaba a los jugadores a enfrentarse al Código Konami para obtener vidas extra?", "Contra", "Super Mario Bross", "Megaman", "Gradius", "Contra", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q39);

        TriviaQuestion q40= new TriviaQuestion("¿En qué juego aparece el luchador y estrella de cine Dwayne The Rock Johnson?", "WWE 2K18", "Doom", "SpyHunter: Nowhere to Run", "Fortnite", "SpyHunter: Nowhere to Run", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q40);

        TriviaQuestion q41= new TriviaQuestion("Qué infame videojuego es conocido por su desastrosa adaptación de E.T. el Extraterrestre?", "Atari 2600", "Sega Saturn", "Philips CD-i", "Virtual Boy", "Atari 2600", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q41);

        TriviaQuestion q42= new TriviaQuestion("¿Cuándo se lanzó Call of Duty por primera vez?", "2000", "2001", "2002", "2003", "2003", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q42);

        TriviaQuestion q43= new TriviaQuestion("¿Qué compañía inventó la primera consola de videojuegos?", "Nintendo", "Atari", "Sony", "NAMCO", "Atari", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q43);

        TriviaQuestion q44= new TriviaQuestion("¿Cuál fue el primer videojuego comercializado con éxito?", "Super Mario", "Plants vs. Zombies", "Tetris", "Pong", "Pong", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q44);

        TriviaQuestion q45= new TriviaQuestion("¿Cuántas versiones de Grand Theft Auto han sido lanzadas?", "13", "14", "15", "16", "15", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q45);

        TriviaQuestion q46= new TriviaQuestion("¿Quién prestó su voz como Pikachu en la película Pokemon Detective Pikachu?", "Ryan Gosling", "Ryan Seacrest", "Ryan Reynolds", "Ryan Adams", "Ryan Reynolds", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q46);

        TriviaQuestion q47= new TriviaQuestion("¿Qué significa la palabra Atari?", "Carisma", "Éxito", "Metas", "Desarrollo", "Éxito", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q47);

        TriviaQuestion q48= new TriviaQuestion("¿Qué videojuego involucra a un gorila lanzando barriles escaleras abajo?", "King Kong", "Donkey Kong", "Pong", "Pac-Man", "Donkey Kong", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q48);

        TriviaQuestion q49= new TriviaQuestion("¿Qué hermanos plomeros vestidos de colores están salvando el Reino Champiñón una tubería a la vez?", "Banjo & Kazooie", "Ratchet & Clank", "Mario & Luigi", "Sonic & Tails", "Mario & Luigi", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q49);

        TriviaQuestion q50= new TriviaQuestion("Completa este código de trucos clásico: IDKFA es a DOOM lo que ___ es a The Sims?", "Motherlode", "Godmode", "Rosebud", "InfiniteLives", "Rosebud", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q50);

        TriviaQuestion q51= new TriviaQuestion("¿Qué juego te tiene gestionando la vida en una isla desierta con algunos adorables vecinos animales?", "Zoo Tycoon", "Stranded Deep", "FarmVille", "Animal Crossing", "Animal Crossing", TriviaQuestion.CATEGORY_JUEGOSDEROL);
        addQuestions(q51);

        TriviaQuestion q52= new TriviaQuestion("¿Cuál de estas franquicias de videojuegos más vendidas no tiene un personaje principal y ha vendido más?", "Tetris", "Minecraft", "Los Sims", "Juego de Tronos", "Tetris", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q52);

        TriviaQuestion q53= new TriviaQuestion("¿Quién es el hermano de Liquid Snake?", "Príncipe Snake", "Gas Snake", "Solid Snake", "Bola de Snake", "Solid Snake", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q53);

        TriviaQuestion q54= new TriviaQuestion("¿Qué personaje de Mortal Kombat es un ninja?", "Jax", "Kung Lao", "Sub-Zero", "Sonya", "Sub-Zero", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q54);

        TriviaQuestion q55= new TriviaQuestion("¿Quién fue el creador de Tetris?", "Alexey Pajitnov", "Kylian Mbappé", "Toru Iwatani", "Bowser", "Alexey Pajitnov", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q55);

        TriviaQuestion q56= new TriviaQuestion("¿Cuántas versiones de Super Mario se han lanzado?", "3", "47", "10", "25", "47", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q56);

        TriviaQuestion q57= new TriviaQuestion("¿Quién creó la icónica serie de videojuegos Pac-Man?", "Alexey Pajitnov", "Kylian Mbappé", "Toru Iwatani", "Bowser", "Toru Iwatani", TriviaQuestion.CATEGORY_HISTORIA);
        addQuestions(q57);

        TriviaQuestion q58= new TriviaQuestion("¿Cuál es el primer enemigo que enfrentas en Super Mario?", "Drogo", "Bowser", "Trouser", "Jowser", "Bowser", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q58);

        TriviaQuestion q59= new TriviaQuestion("¿Qué power-up transforma a Mario en su versión voladora, similar a un mapache?", "Hoja Super", "Pluma Mágica", "Red Bull", "Champiñón de Vuelo", "Hoja Super", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q59);

        TriviaQuestion q60= new TriviaQuestion("En la serie de Legend of Zelda, ¿qué instrumento es un objeto mágico recurrente?", "Guitarra", "Ocarina", "Teclado", "Triangulo", "Ocarina", TriviaQuestion.CATEGORY_JUEGOSDEROL);
        addQuestions(q60);

        TriviaQuestion q61= new TriviaQuestion("¿Cuál es la comida favorita de cierto erizo azul muy rápido?", "Pizza", "Chili Dogs", "Tocino", "Espinacas", "Chili Dogs", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q61);

        TriviaQuestion q62= new TriviaQuestion("¿Qué juego presenta una batalla real en una isla que se hunde, supervisada por un misterioso gato llamado G-Man?", "Fortnight", "PUBG", "Apex Legends", "Gato Roboto", "Gato Roboto", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q62);

        TriviaQuestion q63= new TriviaQuestion("¿En qué juego de aventuras debes escapar de una mansión embrujada capturando fantasmas con una aspiradora?", "Resident Evil", "Ghostbusters: The Video Game", "La Mansión de Luigi", "Dust Sucker: Phantom Edition", "La Mansión de Luigi", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q63);

        TriviaQuestion q64= new TriviaQuestion("¿Quién es el personaje de videojuegos más famoso de todos los tiempos?", "Pikachu", "Mario", "Superman", "Sonic", "Mario", TriviaQuestion.CATEGORY_PERSONAJES);
        addQuestions(q64);

        TriviaQuestion q65= new TriviaQuestion("¿Que nombre oficial reciben los ejércitos del Risk?", "Soldados, caballos y cañones", "General, soldado a caballo y contraalmirante", "Infantería, caballería y artillería", "Soldados y artillería", "Infantería, caballería y artillería", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q65);

        TriviaQuestion q66= new TriviaQuestion("¿A que otro juego le debe su fama Monopoly?", "Superpoly", "Finanzas", "The Landlord's Game", "Estanciero", "The Landlord's Game", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q66);

        TriviaQuestion q67= new TriviaQuestion("¿Como se llama el juego en el que hay que perder para ganar?", "Hotel", "Tiburones de las Finanzas", "Bancarrota", "Prisionero", "Bancarrota", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q67);

        TriviaQuestion q68= new TriviaQuestion("¿Que personaje NO aparece en La Herencia de Tía Ágata?", "Tía Ágata", "El Perro", "La Doctora", "El Gato", "El Perro", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q68);

        TriviaQuestion q69= new TriviaQuestion("¿Quién es el autor de HeroQuest?", "Stephen Baker", "Pepe Pineda", "Charles Darrow", "Stephen Baker", "Emanuel Velez", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q69);

        TriviaQuestion q70= new TriviaQuestion("¿Hasta cuantos jugadores pueden enfrentarse en Quien es Quién?", "Dos", "Cuatro", "Seis", "Ocho", "Dos", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q70);

        TriviaQuestion q71= new TriviaQuestion("¿En qué juego se observan laminas en busca de objetos o seres?", "Blokus", "Kaleidos", "Pictionary", "Sokoban", "Kaleidos", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q71);

        TriviaQuestion q72= new TriviaQuestion("¿Que personaje de Nintendo cuenta con una versión oficial de Jenga?", "Donkey Kong", "Mario", "Luigi", "Bowser", "Donkey Kong", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q72);

        TriviaQuestion q73= new TriviaQuestion("¿En qué juego se marcan los fracasos en las manos de los jugadores con un sello?", "El Fantasma de la Opera", "Embrujada", "Inkognito", "Rummy", "El Fantasma de la Opera", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q73);

        TriviaQuestion q74= new TriviaQuestion("¿Con que color está representado Prado en el Cluedo tradicional?", "Amarillo", "Azul", "Rojo", "Verde", "Verde", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q74);

        TriviaQuestion q75= new TriviaQuestion("¿En qué juego de mesa las miniaturas llevan mochila para guardar joyas?", "Misterio", "El Puente Prohibido", "El Extraño Caso de la Calle Morgue", "Tabú", "El Puente Prohibido", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q75);

        TriviaQuestion q76= new TriviaQuestion("¿Que negocio aparece en Fin de Mes?", "Restaurantes Andantes", "Fruterías Elías", "Limusinas Josefina", "Kiosco Juanito", "Limusinas Josefina", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q76);

        TriviaQuestion q77= new TriviaQuestion("¿Que juego suele ocupar la otra cara del tablero del Parchís?", "Las Damas", "El Ajedrez", "Cluedo", "La Oca", "La Oca", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q77);

        TriviaQuestion q78= new TriviaQuestion("¿Quién es el ilustrador de En Busca del Imperio Cobra?", "Siscu Bellido", "Ramses Bosque", "Isidre Mones", "Giancarlo Esposito", "Isidre Mones", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q78);

        TriviaQuestion q79= new TriviaQuestion("¿Cuál es el objetivo del Stratego?", "Capturar la Bandera", "Descubrir al espía contrario", "Matar a los exploradores rivales", "Llegar hasta el fin del camino", "Capturar la Bandera", TriviaQuestion.CATEGORY_JUEGOSDEMESA);
        addQuestions(q79);

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

    @SuppressLint("Range")
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

    @SuppressLint("Range")
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
