package com.ian.gameapi.service;

public class GameService {
    private static GameService ourInstance = new GameService();

    public static GameService getInstance() {
        return ourInstance;
    }

    private GameService() {
    }

    //TODO move all logic regards to getting games here
}
