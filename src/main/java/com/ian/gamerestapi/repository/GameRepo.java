package com.ian.gamerestapi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.gamerestapi.exception.IdNotFoundException;
import com.ian.gamerestapi.model.Game;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;

import java.io.IOException;

@Repository
public class GameRepo {
    private static final String JSON_FILE_PATH = "classpath:static/games.json";
    private File jsonFile;

    private ObjectMapper objectMapper;

    public GameRepo() throws IOException{
        this.objectMapper = new ObjectMapper();
        jsonFile = ResourceUtils.getFile(JSON_FILE_PATH);
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public File getJsonFile() {
        return jsonFile;
    }

    public Game findGameById(int id) throws IOException {
        int idToIndex = id - 1;
        Game[] games = findAllGames();

        if(id > games.length){
            throw new IdNotFoundException();
        }else{
            return games[idToIndex];
        }
    }

    public Game[] findAllGames() throws IOException{
        return objectMapper.readValue(jsonFile, Game[].class);
    }
}
