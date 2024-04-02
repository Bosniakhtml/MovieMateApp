package com.example.moviemate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.moviemate.data.MovieAPI;
import com.example.moviemate.data.MovieAPITask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAPITask.OnMovieAPITaskListener {

    String LOG_TAG = "MovieMate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieAPI.fetchAllMovies(this);
    }

    @Override
    public void onFetchAllMoviesAvailable(String response) {
        Log.i(LOG_TAG, response);
    }

    @Override
    public void onFetchMovieAvailable(String response) {
        Log.i(LOG_TAG, response);
    }
}