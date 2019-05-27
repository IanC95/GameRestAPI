package com.ian.gamerestapi.service;

import com.ian.gamerestapi.model.Game;
import com.ian.gamerestapi.repository.GameRepo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

@Service
public class GameService {
    private final GameRepo gameRepo;

    public GameService(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    public Game getGame(String id) throws IOException{
        if(!isValidId(id)){
            return null;
        }

        int idAsInt = Integer.parseInt(id);

        return gameRepo.findGameById(idAsInt);
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
