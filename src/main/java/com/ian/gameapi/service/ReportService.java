package com.ian.gameapi.service;

import com.ian.gameapi.pojos.Comment;
import com.ian.gameapi.pojos.Game;
import com.ian.gameapi.pojos.GameLikes;
import com.ian.gameapi.pojos.Report;
import com.ian.gameapi.repository.GameRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReportService {
    private static ReportService ourInstance = new ReportService();

    public static ReportService getInstance() {
        return ourInstance;
    }

    private ReportService() {
    }

    public ResponseEntity<Object> getReport(){
        Report report = new Report();
        Game[] games;

        try {
            games = GameRepo.getInstance().findAllGames();
        }catch(IOException e){
            return null;
        }

        report.setUser_with_most_comments(this.calculateUserWithMostComments(games));
        report.setHighest_rated_game(this.gameWithHighestSumLikes(games));
        report.setAverage_likes_per_game(this.averageLikesPerGame(games));

        return new ResponseEntity<>(
                report,
                HttpStatus.OK
        );
    }

    private String calculateUserWithMostComments(Game[] games){
        HashMap<String, Integer> usersComments = new HashMap<>();

        for(Game game: games) {
            for(Comment comment : game.getComments()){
                String user = comment.getUser();
                if (usersComments.containsKey(user)){
                    Integer newAmountOfComments = usersComments.get(user) + 1;
                    usersComments.put(user, newAmountOfComments);
                }else{
                    usersComments.put(user, 1);
                }
            }
        }

        Map.Entry<String, Integer> maxEntry = Collections.max(usersComments.entrySet(),
                (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) -> e1.getValue()
                .compareTo(e2.getValue()));
        return maxEntry.getKey();
    }

    private String gameWithHighestSumLikes(Game[] games){
        String currentLeader = "";
        Integer currentMax = 0;
        for(Game game : games){
            int gamesLikes = 0;
            for(Comment comment: game.getComments()){
                gamesLikes += comment.getLike();
            }
            if(gamesLikes > currentMax){
                currentMax = gamesLikes;
                currentLeader = game.getTitle();
            }
        }

        return currentLeader;
    }

    private GameLikes[] averageLikesPerGame(Game[] games){
        ArrayList<GameLikes> gameLikesArrayList = new ArrayList<>();

        for(Game game : games){
            GameLikes gameLikes = new GameLikes();
            gameLikesArrayList.add(gameLikes);
            gameLikes.setTitle(game.getTitle());

            int sumLikes = 0;
            int numComments = 0;

            for(Comment comment : game.getComments()){
                sumLikes += comment.getLike();
                numComments++;
            }

            int avgLikes = 0;

            if(numComments == 0){
                gameLikes.setAverage_likes(avgLikes);
            }else{
                avgLikes = (int)Math.ceil(sumLikes / (numComments * 1.0f));
                gameLikes.setAverage_likes(avgLikes);
            }
        }

        return gameLikesArrayList.toArray(new GameLikes[0]);
    }
}
