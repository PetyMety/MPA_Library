package com.example.mpa_library;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookListAdapter extends ArrayAdapter<Book> {
    private final Context context;
    private final List<Book> books;

    public BookListAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        this.context = context;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Book book = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        }

        TextView titleTextview = convertView.findViewById(R.id.book_title);
        TextView authorTextview = convertView.findViewById(R.id.book_author);
        TextView pagesTextview = convertView.findViewById(R.id.book_pages);
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        titleTextview.setText(book.getTitle());
        authorTextview.setText(book.getAuthor());
        pagesTextview.setText(String.valueOf(book.getPages()));

        deleteButton.setOnClickListener(del -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Törlés megerősítése")
                    .setMessage("Biztosan törölni szeretné ezt a könyvet?")
                    .setPositiveButton("Igen", (dialog, which) -> {
                        books.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Nem", null)
                    .show();
        });

        return  convertView;

    }

}
