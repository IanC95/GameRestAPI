package com.ian.gameapi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.gameapi.pojos.Game;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameRepo {
    private static GameRepo ourInstance = new GameRepo();

    public static GameRepo getInstance() {
        return ourInstance;
    }

    private final ObjectMapper objectMapper;

    private GameRepo() {
        this.objectMapper = new ObjectMapper();
    }

    public Game findGameById(int id) throws IOException {
        int idToIndex = id - 1;
        Game[] games = findAllGames();

        if(id > games.length){
            throw new FileNotFoundException();
        }else{
            return games[idToIndex];
        }
    }

    public Game[] findAllGames() throws IOException{
        File file = ResourceUtils.getFile("classpath:static/games.json");
        return objectMapper.readValue(file, Game[].class);
    }
}
