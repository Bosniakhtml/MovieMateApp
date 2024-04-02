package com.example.moviemate;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie implements Parcelable {

    // Movie attributes
    @PrimaryKey
    private int Id;
    private String title;
    private int userScore;
    private String date;
    private String description;
    private int runTimeInMinutes;
    private String trailerUrl;
    private int age;
    private String poster;
    private int budget;
    private long revenue;
    private String originalLanguage;
    private String genre; // Added genre attribute

    // Default Movie constructor
    public Movie() {
    }

    // Movie constructor with attributes
    public Movie(int id, String title, int userScore, String date, String description, int runTimeInMinutes, String trailerUrl, int age, String poster, int budget, long revenue, String originalLanguage, String genre) {
        this.Id = id;
        this.title = title;
        this.userScore = userScore;
        this.date = date;
        this.description = description;
        this.runTimeInMinutes = runTimeInMinutes;
        this.trailerUrl = trailerUrl;
        this.age = age;
        this.poster = poster;
        this.budget = budget;
        this.revenue = revenue;
        this.originalLanguage = originalLanguage;
        this.genre = genre; // Set genre attribute
    }

    protected Movie(Parcel in) {
        Id = in.readInt();
        title = in.readString();
        userScore = in.readInt();
        date = in.readString();
        description = in.readString();
        runTimeInMinutes = in.readInt();
        trailerUrl = in.readString();
        age = in.readInt();
        poster = in.readString();
        budget = in.readInt();
        revenue = in.readLong();
        originalLanguage = in.readString();
        genre = in.readString(); // Read genre from parcel
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // Getters and setters for all attributes including genre

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRunTimeInMinutes() {
        return runTimeInMinutes;
    }

    public void setRunTimeInMinutes(int runTimeInMinutes) {
        this.runTimeInMinutes = runTimeInMinutes;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(title);
        dest.writeInt(userScore);
        dest.writeString(date);
        dest.writeString(description);
        dest.writeInt(runTimeInMinutes);
        dest.writeString(trailerUrl);
        dest.writeInt(age);
        dest.writeString(poster);
        dest.writeInt(budget);
        dest.writeLong(revenue);
        dest.writeString(originalLanguage);
        dest.writeString(genre); // Write genre to parcel
    }
}
