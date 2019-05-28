package com.ian.gamerestapi.service;

import com.ian.gamerestapi.model.Comment;
import com.ian.gamerestapi.model.Game;
import com.ian.gamerestapi.model.GameLikes;
import com.ian.gamerestapi.model.Report;
import com.ian.gamerestapi.repository.GameRepo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    private final GameRepo gameRepo;

    public ReportService(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    /**
     * Generates a report based on all games from the repo
     * @return a report
     * @throws IOException if there is an issue with the repo
     */
    public Report getReport() throws IOException{
        Report report = new Report();
        Game[] games;

        games = gameRepo.findAllGames();

        report.setUser_with_most_comments(this.calculateUserWithMostComments(games));
        report.setHighest_rated_game(this.gameWithHighestSumLikes(games));
        report.setAverage_likes_per_game(this.averageLikesPerGame(games));

        return report;
    }

    /**
     * Returns the User with the most comments
     * @param games the array of games you want to evaluate
     * @return User's name with most comments
     */
    private String calculateUserWithMostComments(Game[] games){
        if(games == null || games.length == 0){
            return "";
        }

        HashMap<String, Integer> usersComments = new HashMap<>();

        for(Game game: games) {
            if(game.getComments() != null && !game.getComments().isEmpty()) {
                for (Comment comment : game.getComments()) {
                    String user = comment.getUser();
                    if (usersComments.containsKey(user)) {
                        Integer newAmountOfComments = usersComments.get(user) + 1;
                        usersComments.put(user, newAmountOfComments);
                    } else {
                        usersComments.put(user, 1);
                    }
                }
            }
        }

        if(usersComments.isEmpty()){
            return "";
        }

        Map.Entry<String, Integer> maxEntry = Collections.max(usersComments.entrySet(),
                Comparator.comparing((Map.Entry::getValue)));
        return maxEntry.getKey();
    }

    /**
     * Returns title of game with most likes from comments
     * @param games the array of games you want to evaluate
     * @return title of game with largest sum of likes
     */
    private String gameWithHighestSumLikes(Game[] games){
        if(games == null || games.length == 0){
            return "";
        }

        String currentLeader = "";
        int currentMax = 0;

        for(Game game : games){
            if(game.getComments() != null && !game.getComments().isEmpty()) {
                int gamesLikes = 0;
                for (Comment comment : game.getComments()) {
                    gamesLikes += comment.getLike();
                }
                if (gamesLikes > currentMax) {
                    currentMax = gamesLikes;
                    currentLeader = game.getTitle();
                }
            }
        }

        return currentLeader;
    }

    /**
     * Returns the average likes from comments for each game in games
     * @param games the array of games you want to evaluate
     * @return average likes from comments for each game in games
     */
    private GameLikes[] averageLikesPerGame(Game[] games){
        if(games == null || games.length == 0){
            return new GameLikes[0];
        }

        ArrayList<GameLikes> gameLikesArrayList = new ArrayList<>();

        for(Game game : games){
            GameLikes gameLikes = new GameLikes();

            gameLikesArrayList.add(gameLikes);
            gameLikes.setTitle(game.getTitle());

            int sumLikes = 0;
            int numComments = 0;
            int avgLikes = 0;

            if(game.getComments() != null && !game.getComments().isEmpty()) {
                for (Comment comment : game.getComments()) {
                    sumLikes += comment.getLike();
                    numComments++;
                }
            }

            if (numComments == 0) {
                gameLikes.setAverage_likes(avgLikes);
            } else {
                avgLikes = (int) Math.ceil(sumLikes / (numComments * 1.0f));
                gameLikes.setAverage_likes(avgLikes);
            }
        }

        return gameLikesArrayList.toArray(new GameLikes[0]);
    }
}
