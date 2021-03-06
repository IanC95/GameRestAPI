package com.ian.gamerestapi.model;

import java.util.List;

public class Game {
    private String title;
    private String description;
    private String by;
    private List<String> platform;
    private String age_rating;
    private Integer likes;
    private List<Comment> comments;

    public Game() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public List<String> getPlatform() {
        return platform;
    }

    public void setPlatform(List<String> platform) {
        this.platform = platform;
    }

    public String getAge_rating() {
        return age_rating;
    }

    public void setAge_rating(String age_rating) {
        this.age_rating = age_rating;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (getTitle() != null ? !getTitle().equals(game.getTitle()) : game.getTitle() != null) return false;
        if (getDescription() != null ? !getDescription().equals(game.getDescription()) : game.getDescription() != null)
            return false;
        if (getBy() != null ? !getBy().equals(game.getBy()) : game.getBy() != null) return false;
        if (getPlatform() != null ? !getPlatform().equals(game.getPlatform()) : game.getPlatform() != null)
            return false;
        if (getAge_rating() != null ? !getAge_rating().equals(game.getAge_rating()) : game.getAge_rating() != null)
            return false;
        if (getLikes() != null ? !getLikes().equals(game.getLikes()) : game.getLikes() != null) return false;
        return getComments() != null ? getComments().equals(game.getComments()) : game.getComments() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getBy() != null ? getBy().hashCode() : 0);
        result = 31 * result + (getPlatform() != null ? getPlatform().hashCode() : 0);
        result = 31 * result + (getAge_rating() != null ? getAge_rating().hashCode() : 0);
        result = 31 * result + (getLikes() != null ? getLikes().hashCode() : 0);
        result = 31 * result + (getComments() != null ? getComments().hashCode() : 0);
        return result;
    }
}
