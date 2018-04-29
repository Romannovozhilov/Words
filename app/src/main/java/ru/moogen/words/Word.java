package ru.moogen.words;

import android.support.annotation.NonNull;

import java.util.Date;

public class Word implements Comparable<Word> {

    private int id;
    private long date;
    private String name;
    private String description;
    private boolean isFavourite;

    public Word(int id, long date, String name, String description, boolean isFavourite) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.isFavourite = isFavourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id - ").append(id).append("; date - ").append(new Date(date).toString())
                .append("; name - ").append(name).append("; description - ").append(description)
                .append("; favourite - ").append(isFavourite);
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(@NonNull Word o) {
        if (o.date < date){
            return 1;
        }
        if (o.date > date){
            return -1;
        }
        return 0;
    }
}
