package com.example.moviemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private OnItemClickListener listener;

    // Constructor to initialize the adapter with required parameters
    public MovieAdapter(Context context, List<Movie> movieList, OnItemClickListener listener) {
        this.context = context;
        this.movieList = movieList;
        this.listener = listener;
    }

    // onCreateViewHolder method inflates the item view layout
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // Inflate an item view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_movie_layout, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);
        return holder;
    }

    // onBindViewHolder method binds data to the view elements
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.tvMovieName.setText(movieList.get(position).getTitle());
        holder.tvMovieRating.setText(String.valueOf(movieList.get(position).getUserScore()));
        holder.tvMovieLength.setText(String.valueOf(movieList.get(position).getRunTimeInMinutes()));
        holder.tvMovieReleaseDate.setText(movieList.get(position).getDate());
        holder.tvMovieCategory.setText(movieList.get(position).getGenre());
        Glide.with(this.context).load(movieList.get(position).getPoster()).into(holder.ivMoviePicture);
    }

    // getItemCount method returns the total number of items in the list
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // setData method updates the data list and notifies the adapter of changes
    public void setData(List<Movie> movies) {
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView tvMovieName;
        TextView tvMovieRating;
        TextView tvMovieLength;
        TextView tvMovieReleaseDate;
        TextView tvMovieCategory;
        ImageView ivMoviePicture;

        // Constructor initializes view elements and sets click listener
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.tv_movieName);
            tvMovieRating = itemView.findViewById(R.id.tv_movieRating);
            tvMovieLength = itemView.findViewById(R.id.tv_movieLength);
            tvMovieReleaseDate = itemView.findViewById(R.id.tv_movieReleaseDate);
            tvMovieCategory = itemView.findViewById(R.id.tv_movieCategory);
            ivMoviePicture = itemView.findViewById(R.id.iv_MoviePicture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(movieList.get(position));
                    }
                }
            });
        }
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    // Method to clear all data from the adapter
    public void clearData() {
        movieList.clear();
        notifyDataSetChanged();
    }
}
