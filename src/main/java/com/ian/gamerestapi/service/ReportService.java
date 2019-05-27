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
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    private final GameRepo gameRepo;

    public ReportService(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    public Report getReport() throws IOException{
        Report report = new Report();
        Game[] games;

        games = gameRepo.findAllGames();

        report.setUser_with_most_comments(this.calculateUserWithMostComments(games));
        report.setHighest_rated_game(this.gameWithHighestSumLikes(games));
        report.setAverage_likes_per_game(this.averageLikesPerGame(games));

        return report;
    }

    private String calculateUserWithMostComments(Game[] games){
        HashMap<String, Integer> usersComments = new HashMap<>();

        if(games == null || games.length == 0){
            return "";
        }

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
                (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) -> e1.getValue()
                .compareTo(e2.getValue()));
        return maxEntry.getKey();
    }

    private String gameWithHighestSumLikes(Game[] games){
        if(games == null || games.length == 0){
            return "";
        }

        String currentLeader = "";
        Integer currentMax = 0;
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

            if(game.getComments() != null && !game.getComments().isEmpty()) {
                for (Comment comment : game.getComments()) {
                    sumLikes += comment.getLike();
                    numComments++;
                }


                int avgLikes = 0;

                if (numComments == 0) {
                    gameLikes.setAverage_likes(avgLikes);
                } else {
                    avgLikes = (int) Math.ceil(sumLikes / (numComments * 1.0f));
                    gameLikes.setAverage_likes(avgLikes);
                }
            }
        }

        return gameLikesArrayList.toArray(new GameLikes[0]);
    }
}
