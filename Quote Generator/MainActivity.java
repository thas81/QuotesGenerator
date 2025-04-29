package com.example.quotegenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.quotegenerator.R;

import com.example.quotegenerator.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView txtQuote;
    private Button btnGetQuote;
    private DatabaseHelper dbHelper;
    private List<String> quotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);
        quotesList = new ArrayList<>();

        // Find views
        txtQuote = findViewById(R.id.txtQuote);
        btnGetQuote = findViewById(R.id.btnGetQuote);

        // Load quotes from database
        loadQuotes();

        // Set click listener for button
        btnGetQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayRandomQuote();
            }
        });
    }

    private void loadQuotes() {
        quotesList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT quote FROM quotes", null);

        if (cursor.moveToFirst()) {
            do {
                String quote = cursor.getString(cursor.getColumnIndex("quote"));
                quotesList.add(quote);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void displayRandomQuote() {
        if (quotesList.size() > 0) {
            Random random = new Random();
            int index = random.nextInt(quotesList.size());
            txtQuote.setText(quotesList.get(index));
        } else {
            txtQuote.setText("No quotes available");
        }
    }
}
