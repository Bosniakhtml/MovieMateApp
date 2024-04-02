package com.example.moviemate.data;

import android.util.Log;

public class MovieAPI {
    private static String LOG_TAG = "MovieAPI";

    final private static String baseURL = "https://api.themoviedb.org/3";
    final private static String API_KEY = "1600b4a840ea493d1acb324fc49be52a";


    public MovieAPI() {
    }

    private static void fetchData(String path, String action, MovieAPITask.OnMovieAPITaskListener listener) {
        Log.i(LOG_TAG, "fetching data");
        MovieAPITask task = new MovieAPITask(listener);
        task.execute(baseURL + path + "?api_key=" + API_KEY, action);
    }

    public static void fetchAllMovies(MovieAPITask.OnMovieAPITaskListener listener) {
        fetchData("/discover/movie", "fetchAllMovies", listener);
    }

    public static void fetchMovie(String movieId, MovieAPITask.OnMovieAPITaskListener listener) {
        fetchData("/movie/" + movieId, "fetchMovie", listener);
    }

}
