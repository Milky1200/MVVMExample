package com.example.mvvmexample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Quote.class,version = 1,exportSchema = true)
public abstract class QuoteDatabase extends RoomDatabase {
    public abstract QuoteDao getDao();
    private static volatile QuoteDatabase quoteDatabase;
    public static QuoteDatabase getQuoteDatabase(Context context){
        if(quoteDatabase==null){
            synchronized (QuoteDatabase.class){
                if (quoteDatabase==null) {
                    quoteDatabase = Room.databaseBuilder(context.getApplicationContext(), QuoteDatabase.class, "quote_database")
                            .createFromAsset("quote.db").
                            fallbackToDestructiveMigration().
                            allowMainThreadQueries().build();
                }
            }
        }
        return quoteDatabase;
    }
}
