package com.example.quotegenerator;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quotes_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_QUOTES = "quotes";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create quotes table
        String createTableQuery = "CREATE TABLE " + TABLE_QUOTES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "quote TEXT NOT NULL)";
        db.execSQL(createTableQuery);

        // Insert the 20 motivational quotes
        insertQuotes(db);
    }

    private void insertQuotes(SQLiteDatabase db) {
        String[] quotes = {
                "Believe you can and you're halfway there.",
                "Your limitation—it's only your imagination.",
                "Push yourself, because no one else is going to do it for you.",
                "Great things never come from comfort zones.",
                "Dream it. Wish it. Do it.",
                "Success doesn't just find you. You have to go out and get it.",
                "The harder you work for something, the greater you'll feel when you achieve it.",
                "Dream bigger. Do bigger.",
                "Don't stop when you're tired. Stop when you're done.",
                "Wake up with determination. Go to bed with satisfaction.",
                "Do something today that your future self will thank you for.",
                "Little things make big days.",
                "It's going to be hard, but hard does not mean impossible.",
                "Don't wait for opportunity. Create it.",
                "Sometimes we're tested not to show our weaknesses, but to discover our strengths.",
                "The key to success is to focus on goals, not obstacles.",
                "Dream it. Believe it. Build it.",
                "The only limit to our realization of tomorrow is our doubts of today.",
                "Act as if what you do makes a difference. It does.",
                "Success is not how high you have climbed, but how you make a positive difference to the world."
        };

        ContentValues values = new ContentValues();
        for (String quote : quotes) {
            values.clear();
            values.put("quote", quote);
            db.insert(TABLE_QUOTES, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists and recreate
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTES);
        onCreate(db);
    }
}

