package com.example.mvvmexample;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmexample.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel viewModel;
    ActivityMainBinding binding;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        context=MainActivity.this;
        try {
            InputStream inputStream = context.getAssets().open("quote.db");
            Log.e("Database Check", "Database file found in assets.");
        } catch (IOException e) {
            Log.e("Database Check", "Database file not found in assets.", e);
        }
        QuoteDatabase db=QuoteDatabase.getQuoteDatabase(this);
        QuoteDao dao=db.getDao();
        QuoteRepository repository=new QuoteRepository(dao);
        MainViewModelFactory factory=new MainViewModelFactory(repository);
        viewModel = new ViewModelProvider(this,factory).get(MainViewModel.class);
        viewModel.getQuote().observe(this, new Observer<List<Quote>>() {
            @Override
            public void onChanged(List<Quote> quotes) {
                if (quotes != null && !quotes.isEmpty()) {
                    // Display the first quote for simplicity

                    binding.setQuotes(quotes.toString());
                    Log.e("Quote", "onChanged: "+quotes.toString() );
                } else {
                    binding.setQuotes("No quotes available");
                }

            }
        }) ;
        Quote quote=new Quote();
        quote.author="Aditya";
        quote.text="The Project is Ready";
        binding.btnAddQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        viewModel.insertQuote(quote);
                    }
                }).start();
            }
        });
    }


}