package com.example.mpa_library;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String title = getIntent().getStringExtra("BOOK_TITLE");
        String author = getIntent().getStringExtra("BOOK_AUTHOR");
        int pages = getIntent().getIntExtra("BOOK_PAGES", 0);
        int randomYear = getIntent().getIntExtra("RANDOM_YEAR", 0);


        TextView titleTextView = findViewById(R.id.detail_book_title);
        TextView authorTextView = findViewById(R.id.detail_book_author);
        TextView pagesTextView = findViewById(R.id.detail_book_pages);
        TextView yearTextView = findViewById(R.id.detail_book_year);
        Button buttonBack = findViewById(R.id.back_button);

        titleTextView.setText(title);
        authorTextView.setText(author);
        pagesTextView.setText(pages);
        yearTextView.setText(String.valueOf(randomYear));

        buttonBack.setOnClickListener(back -> finish());

    }
}
