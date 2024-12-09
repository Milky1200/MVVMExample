package com.example.mvvmexample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quote")
public class Quote {
    @PrimaryKey
    @NonNull
    public int id;

    @NonNull
    @ColumnInfo(name = "text") // Matches the 'text' column
    public String text;

    @NonNull
    @ColumnInfo(name = "author") // Matches the 'author' column
    public String author;

    @Override
    public String toString() {
        return "Quote{text='" + text + "', author='" + author + "'}";
    }
}
