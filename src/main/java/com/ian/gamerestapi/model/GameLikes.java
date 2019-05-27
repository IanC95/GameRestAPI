package com.ian.gamerestapi.model;

public class GameLikes {
    private String title;
    private int average_likes;

    public GameLikes() {
    }

    public GameLikes(String title, int average_likes) {
        this.title = title;
        this.average_likes = average_likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAverage_likes() {
        return average_likes;
    }

    public void setAverage_likes(int average_likes) {
        this.average_likes = average_likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameLikes gameLikes = (GameLikes) o;

        if (getAverage_likes() != gameLikes.getAverage_likes()) return false;
        return getTitle() != null ? getTitle().equals(gameLikes.getTitle()) : gameLikes.getTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + getAverage_likes();
        return result;
    }
}
