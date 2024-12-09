package com.example.mvvmexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final QuoteRepository quoteRepository;

    public MainViewModel(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public LiveData<List<Quote>> getQuote() {
        return quoteRepository.getQuotes();
    }

    public void insertQuote(Quote quote) {
        quoteRepository.insertQuote(quote);
    }
}
