package com.example.mvvmexample;

import androidx.lifecycle.LiveData;

import java.util.List;
public class QuoteRepository{
    private QuoteDao dao;
    public QuoteRepository(QuoteDao dao) {
        this.dao=dao;
    }
    public void insertQuote(Quote quote) {
        dao.insertQuote(quote);
    }
    public LiveData<List<Quote>> getQuotes() {
        return dao.getQuotes();
    }
}
