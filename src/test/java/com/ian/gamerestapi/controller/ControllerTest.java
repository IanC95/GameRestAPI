package com.ian.gamerestapi.controller;

import com.ian.gamerestapi.exception.IdNotFoundException;
import com.ian.gamerestapi.exception.InvalidIdException;
import com.ian.gamerestapi.model.Game;
import com.ian.gamerestapi.model.Report;
import com.ian.gamerestapi.service.GameService;
import com.ian.gamerestapi.service.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    private GameService gameService;

    @Mock
    private ReportService reportService;

    private Controller controller;

    @Before
    public void init(){
        controller = new Controller(gameService, reportService);
    }

    @Test
    public void getGameTest() throws IOException, InvalidIdException {
        Game game = new Game();
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                game,
                HttpStatus.OK
        );
        Mockito.when(gameService.getGame(Mockito.anyString())).thenReturn(game);

        ResponseEntity<Object> response = controller.getGame("1");

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getGameTestIdNotFound() throws IOException, InvalidIdException {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                String.format("No game found with id %d", 1),
                HttpStatus.NOT_FOUND
        );
        Mockito.when(gameService.getGame(Mockito.anyString())).thenThrow(new IdNotFoundException(
                String.format("No game found with id %d", 1)
        ));

        ResponseEntity<Object> response = controller.getGame("1");

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getGameTestIOException() throws IOException, InvalidIdException {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        Mockito.when(gameService.getGame(Mockito.anyString())).thenThrow(new IOException());

        ResponseEntity<Object> response = controller.getGame("1");

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getGameTestNoGameReturned() throws IOException, InvalidIdException {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                "Please provide an id that is an integer greater than 0",
                HttpStatus.BAD_REQUEST
        );
        Mockito.when(gameService.getGame(Mockito.anyString())).thenThrow(new InvalidIdException());

        ResponseEntity<Object> response = controller.getGame("INVALID_ID");

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getReportTest() throws IOException{
        Report report = new Report();
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                report,
                HttpStatus.OK
        );
        Mockito.when(reportService.getReport()).thenReturn(report);

        ResponseEntity<Object> response = controller.getReport();

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getReportTestIOException() throws IOException{
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        Mockito.when(reportService.getReport()).thenThrow(new IOException());

        ResponseEntity<Object> response = controller.getReport();

        assertEquals(expectedResponse, response);
    }
}