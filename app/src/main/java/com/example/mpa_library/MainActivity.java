package com.example.mpa_library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Book> bookList;
    private BookListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookList = new ArrayList<>();
        adapter = new BookListAdapter(this, bookList);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        EditText titleEditText = findViewById(R.id.book_title);
        EditText authorEditText = findViewById(R.id.book_author);
        EditText pagesEditText = findViewById(R.id.book_pages);
        Button addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(add -> {
            String title = titleEditText.getText().toString();
            String author = authorEditText.getText().toString();
            String pagesString = pagesEditText.getText().toString();

            if (title.isEmpty() || author.isEmpty() || pagesString.isEmpty()) {
                Toast.makeText(this, "Minden mező kitöltése kötelező!", Toast.LENGTH_SHORT).show();
                return;
            }

            int pages = Integer.parseInt(pagesString);
                if (pages < 50) {
                    Toast.makeText(this, "Az oldal legalább 50 kell hogy legyen!", Toast.LENGTH_SHORT).show();
                    return;
                }


            bookList.add(new Book(title, author, pages));
            adapter.notifyDataSetChanged();
            titleEditText.setText("");
            authorEditText.setText("");
            pagesEditText.setText("");

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("BOOK_TITLE", book.getTitle());
                intent.putExtra("BOOK_AUTHOR", book.getAuthor());
                intent.putExtra("BOOK_PAGES", book.getPages());
                intent.putExtra("RANDOM_YEAR", new Random().nextInt(2024 - 1900) + 1900);
                startActivity(intent);
            }
        });
    }
}