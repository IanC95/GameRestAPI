package com.ian.gameapi.service;

import com.ian.gameapi.pojos.Game;
import com.ian.gameapi.repository.GameRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GameService {
    private static GameService ourInstance = new GameService();

    public static GameService getInstance() {
        return ourInstance;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private GameService() {
    }

    public ResponseEntity<Object> getGame(String id){
        if(!isValidId(id)){
            return new ResponseEntity<>(
                    "Id must be positive integer",
                    HttpStatus.BAD_REQUEST
            );
        }

        int idAsInt = Integer.parseInt(id);
        Game game;
        try{
            game = GameRepo.getInstance().findGameById(idAsInt);

        }catch(FileNotFoundException e){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.NOT_FOUND
            );
        }
        catch(IOException e){
            String errorMessage = "IO Exception thrown while accessing file";
            logger.debug(errorMessage);
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                game,
                HttpStatus.OK
        );
    }

    private boolean isValidId(String s){
        if(!isInteger(s)){
            return false;
        }

        int integer = Integer.parseInt(s);

        return integer >= 0;
    }

    private boolean isInteger(String s){
        if(s == null || s.isEmpty()){
            return false;
        }

        Scanner sc = new Scanner(s.trim());

        if(!sc.hasNextInt()){
            return false;
        }

        sc.nextInt();
        return !sc.hasNext();
    }
}
