package com.ian.gamerestapi.service;

import com.ian.gamerestapi.exception.InvalidIdException;
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

    /**
     * Returns a game from the repo
     * @param id id of desired game
     * @return Game, if found
     * @throws IOException if id not found or repo errors
     * @throws InvalidIdException if if cannot be converted in to a valid in
     */
    public Game getGame(String id) throws IOException, InvalidIdException {
        if(!isValidId(id)){
            throw new InvalidIdException();
        }

        int idAsInt = Integer.parseInt(id);

        return gameRepo.findGameById(idAsInt);
    }

    /**
     * Checks if input string is a valid id (any int > 0)
     * @param s String to be checked
     * @return true  if id is valid, false otherwise
     */
    private boolean isValidId(String s){
        if(!isInteger(s)){
            return false;
        }

        int integer = Integer.parseInt(s);

        return integer > 0;
    }

    /**
     * Checks if provided string is a valid int
     * @param s String to be checked
     * @return true if String is integer
     */
    private boolean isInteger(String s){
        if(s == null || s.isEmpty()){
            return false;
        }

        try(Scanner sc = new Scanner(s.trim())) {

            if (!sc.hasNextInt()) {
                return false;
            }

            sc.nextInt();
            return !sc.hasNext();
        }
    }
}
