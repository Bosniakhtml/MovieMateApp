package com.example.moviemate.data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MovieAPITask extends AsyncTask<String, Void, ArrayList<String>> {
    private final static String LOG_TAG = "MovieAPITask";
    private OnMovieAPITaskListener listener;

    public MovieAPITask(OnMovieAPITaskListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> returnList = new ArrayList<>();
        String urlString = null;
        String action = null;

        if (params[0] != null) {
            urlString = params[0];
        }

        if (params[1] != null) {
            action = params[1];
        }

        InputStream inputStream = null;
        int responsCode = -1;
        String response = null;

        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection)) {
                return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            responsCode = httpConnection.getResponseCode();

            if (responsCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.d(LOG_TAG, "response = " + response);


            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "doInBackground MalformedURLEx " + e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e(LOG_TAG, "doInBackground IOException " + e.getLocalizedMessage());
            return null;
        }

        returnList.add(action);
        returnList.add(response);

        return returnList;
    }

    @Override
    protected void onPostExecute(ArrayList<String> response) {
        super.onPostExecute(response);
        Log.i(LOG_TAG, "onPostExecute: action = "+ response.get(0));

        switch (response.get(0)) {
            case "fetchAllMovies":
                listener.onFetchAllMoviesAvailable(response.get(1));
                break;
            case "fetchMovie":
                listener.onFetchMovieAvailable(response.get(1));
                break;
            default:
                break;
        }
    }


    public interface OnMovieAPITaskListener {
        public void onFetchAllMoviesAvailable(String response);

        public void onFetchMovieAvailable(String response);
    }

    private static String getStringFromInputStream(InputStream inputStream) {
        Log.i(LOG_TAG, "getStringFromInputStream");
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        String line;
        try {

            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}