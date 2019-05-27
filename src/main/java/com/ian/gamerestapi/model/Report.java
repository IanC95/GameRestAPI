package com.ian.gamerestapi.model;

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
}
