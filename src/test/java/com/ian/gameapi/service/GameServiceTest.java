package com.ian.gameapi.service;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class GameServiceTest {

    public static final String VALID_ID = "1";
    public static final String INVALID_ID_STRING = "NOTANID";
    public static final String INVALID_ID_NEGATIVE_INT = "-2";

    @Test
    public void getGameTestInvalidIdString() {
        ResponseEntity<Object> response = GameService.getInstance().getGame(INVALID_ID_STRING);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getGameTestInvalidIdNegativeInt() {
        ResponseEntity<Object> response = GameService.getInstance().getGame(INVALID_ID_NEGATIVE_INT);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getGameTestValidId() {
        ResponseEntity<Object> response = GameService.getInstance().getGame(VALID_ID);
        //TODO: Mock Repo
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getGameTestValidIdGameNotFound() {
        ResponseEntity<Object> response = GameService.getInstance().getGame(VALID_ID);
        //TODO: Mock Repo to throw file not found
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getGameTestValidIdIoException() {
        ResponseEntity<Object> response = GameService.getInstance().getGame(VALID_ID);
        //TODO: Mock Repo to throw IO exception
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}