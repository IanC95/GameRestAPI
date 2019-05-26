package com.ian.gameapi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameRepoTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private GameRepo gameRepo;

    @Test
    public void findGameByIdTest() {

    }

    @Test
    public void findGameByIdTestNoGame() {
    }

    @Test
    public void findAllGamesTest() {
    }

    @Test
    public void findAllGamesTestIOException() {
    }

    @Test
    public void findAllGamesTestNoGamesFound() {

    }
}