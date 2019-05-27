package com.ian.gamerestapi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.gamerestapi.exception.IdNotFoundException;
import com.ian.gamerestapi.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GameRepoTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private GameRepo gameRepo;

    @Test
    public void findGameByIdTest() throws IOException {
        Game[] games = new Game[1];
        Game expectedGame = new Game();
        games[0] = expectedGame;

        Mockito.when(objectMapper.readValue(gameRepo.getJsonFile(), Game[].class)).thenReturn(games);

        Game returnedGame = gameRepo.findGameById(1);

        assertEquals(expectedGame, returnedGame);
    }

    @Test(expected = IdNotFoundException.class)
    public void findGameByIdTestIdNotFound() throws IOException {
        Game[] games = new Game[1];
        Game expectedGame = new Game();
        games[0] = expectedGame;

        Mockito.when(objectMapper.readValue(gameRepo.getJsonFile(), Game[].class)).thenReturn(games);

        gameRepo.findGameById(99000);
    }

    @Test
    public void findAllGamesTest() throws IOException{
        Game[] expectedGames = new Game[1];
        Mockito.when(objectMapper.readValue(gameRepo.getJsonFile(), Game[].class)).thenReturn(expectedGames);

        Game[] returnedGames = gameRepo.findAllGames();

        assertArrayEquals(expectedGames, returnedGames);
    }
}