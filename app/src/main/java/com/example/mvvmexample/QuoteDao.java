package com.example.mvvmexample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuoteDao {
    @Insert
    void insertQuote(Quote quote);

    @Query("Select * from quote")
    LiveData<List<Quote>> getQuotes();

}
