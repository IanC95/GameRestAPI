package com.ian.gamerestapi.model;

import java.util.Arrays;

public class Report {
    private String user_with_most_comments;
    private String highest_rated_game;
    private GameLikes[] average_likes_per_game;

    public Report() {
    }

    public String getUser_with_most_comments() {
        return user_with_most_comments;
    }

    public String getHighest_rated_game() {
        return highest_rated_game;
    }

    public GameLikes[] getAverage_likes_per_game() {
        return average_likes_per_game;
    }

    public void setUser_with_most_comments(String user_with_most_comments) {
        this.user_with_most_comments = user_with_most_comments;
    }

    public void setHighest_rated_game(String highest_rated_game) {
        this.highest_rated_game = highest_rated_game;
    }

    public void setAverage_likes_per_game(GameLikes[] average_likes_per_game) {
        this.average_likes_per_game = average_likes_per_game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (getUser_with_most_comments() != null ? !getUser_with_most_comments().equals(report.getUser_with_most_comments()) : report.getUser_with_most_comments() != null)
            return false;
        if (getHighest_rated_game() != null ? !getHighest_rated_game().equals(report.getHighest_rated_game()) : report.getHighest_rated_game() != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getAverage_likes_per_game(), report.getAverage_likes_per_game());
    }

    @Override
    public int hashCode() {
        int result = getUser_with_most_comments() != null ? getUser_with_most_comments().hashCode() : 0;
        result = 31 * result + (getHighest_rated_game() != null ? getHighest_rated_game().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getAverage_likes_per_game());
        return result;
    }
}
