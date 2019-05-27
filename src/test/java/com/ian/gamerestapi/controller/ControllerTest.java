package com.ian.gamerestapi.controller;

import com.ian.gamerestapi.exception.IdNotFoundException;
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
    public void getGameTest() throws IOException {
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
    public void getGameTestIdNotFound() throws IOException {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                null,
                HttpStatus.NOT_FOUND
        );
        Mockito.when(gameService.getGame(Mockito.anyString())).thenThrow(new IdNotFoundException());

        ResponseEntity<Object> response = controller.getGame("1");

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getGameTestIOException() throws IOException {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                null,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        Mockito.when(gameService.getGame(Mockito.anyString())).thenThrow(new IOException());

        ResponseEntity<Object> response = controller.getGame("1");

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getGameTestNoGameReturned() throws IOException {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                null,
                HttpStatus.BAD_REQUEST
        );
        Mockito.when(gameService.getGame(Mockito.anyString())).thenReturn(null);

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
    public void getReportIOException() throws IOException{
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
                null,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        Mockito.when(reportService.getReport()).thenThrow(new IOException());

        ResponseEntity<Object> response = controller.getReport();

        assertEquals(expectedResponse, response);
    }
}