package com.ian.gameapi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.gameapi.model.Game;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;

import java.io.IOException;
import java.util.NoSuchElementException;

@Repository
public class GameRepo {

    private final ObjectMapper objectMapper;

    public GameRepo() {
        this.objectMapper = new ObjectMapper();
    }

    public Game findGameById(int id) throws IOException {
        int idToIndex = id - 1;
        Game[] games = findAllGames();

        if(id > games.length){
            throw new NoSuchElementException();
        }else{
            return games[idToIndex];
        }
    }

    public Game[] findAllGames() throws IOException{
        File file = ResourceUtils.getFile("classpath:static/games.json");
        return objectMapper.readValue(file, Game[].class);
    }
}
