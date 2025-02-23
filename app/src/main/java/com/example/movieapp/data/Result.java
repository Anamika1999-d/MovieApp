package com.example.movieapp.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Result implements Parcelable {
    private String backdrop_path;
    private int id;
    private String title;
    private String original_title;
    private String overview;
    private String poster_path;
    private String media_type;
    private Boolean adult;
    private String original_language;
    private List genre_ids;
    private double popularity;
    private String release_date;
    private Boolean video;
    private double vote_average;
    private int vote_count;


    public Result(boolean adult, String backdrop_path, List<Integer> genreIds, int id,
                  String originalLanguage, String originalTitle, String overview, double popularity,
                  String poster_path, String mediaType, String releaseDate, String title, boolean video,
                  double voteAverage, int voteCount) {
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.title = title;
        this.original_title = originalTitle;
        this.overview = overview;
        this.poster_path = poster_path;
        this.media_type = mediaType;
        this.adult = adult;
        this.original_language = originalLanguage;
        this.genre_ids = genreIds;
        this.popularity = popularity;
        this.release_date = releaseDate;
        this.video = video;
        this.vote_average = voteAverage;
        this.vote_count = voteCount;
    }

    protected Result(Parcel in) {
        backdrop_path = in.readString();
        id = in.readInt();
        title = in.readString();
        original_title = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        media_type = in.readString();
        adult = in.readByte() != 0;
        original_language = in.readString();
        genre_ids = in.readArrayList(Integer.class.getClassLoader());
        popularity = in.readDouble();
        release_date = in.readString();
        video = in.readByte() != 0;
        vote_average = in.readDouble();
        vote_count = in.readInt();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<Integer> getGenreIds() {
        return genre_ids;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genre_ids = (ArrayList) genreIds;
    }

    public int getId() {
        return -1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.original_language = originalLanguage;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.original_title = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(double voteAverage) {
        this.vote_average = voteAverage;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(int voteCount) {
        this.vote_count = voteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(backdrop_path);
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(original_title);
        parcel.writeString(overview);
        parcel.writeString(poster_path);
        parcel.writeString(media_type);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(original_language);
        parcel.writeList(genre_ids);
        parcel.writeDouble(popularity);
        parcel.writeString(release_date);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeDouble(vote_average);
        parcel.writeInt(vote_count);
    }
}
