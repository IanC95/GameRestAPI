package com.ian.gamerestapi.service;

import com.ian.gamerestapi.exception.IdNotFoundException;
import com.ian.gamerestapi.exception.InvalidIdException;
import com.ian.gamerestapi.model.Game;
import com.ian.gamerestapi.repository.GameRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    private static final String VALID_ID = "1";
    private static final String ID_ZERO = "0";
    private static final String INVALID_ID_STRING = "NOTANID";
    private static final String INVALID_ID_NEGATIVE_INT = "-2";

    @Mock
    private GameRepo gameRepo;

    private GameService gameService;

    private Game expectedGame;

    @Before
    public void init(){
        gameService = new GameService(gameRepo);
        expectedGame = new Game();
        expectedGame.setTitle("Test game title");
    }

    @Test(expected = InvalidIdException.class)
    public void getGameTestInvalidIdString() throws IOException, InvalidIdException {
        gameService.getGame(INVALID_ID_STRING);
    }

    @Test(expected = InvalidIdException.class)
    public void getGameTestInvalidIdNegativeInt() throws IOException, InvalidIdException{
        gameService.getGame(INVALID_ID_NEGATIVE_INT);
    }

    @Test(expected = InvalidIdException.class)
    public void getGameTestInvalidIdZero() throws IOException, InvalidIdException{
        gameService.getGame(ID_ZERO);
    }

    @Test
    public void getGameTestValidId() throws IOException, InvalidIdException{
        Mockito.when(gameRepo.findGameById(Mockito.anyInt())).thenReturn(expectedGame);

        Game game = gameService.getGame(VALID_ID);

        assertEquals(expectedGame, game);
    }

    @Test(expected = IdNotFoundException.class)
    public void getGameTestValidIdGameNotFound() throws IOException, InvalidIdException{
        Mockito.when(gameRepo.findGameById(Integer.parseInt(VALID_ID))).thenThrow(new IdNotFoundException());

        gameService.getGame(VALID_ID);
    }

    @Test(expected = IOException.class)
    public void getGameTestValidIdIoException() throws IOException, InvalidIdException{
        Mockito.when(gameRepo.findGameById(Integer.parseInt(VALID_ID))).thenThrow(new IOException());

        gameService.getGame(VALID_ID);
    }
}