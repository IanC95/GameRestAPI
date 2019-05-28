package com.ian.gamerestapi.controller;

import com.ian.gamerestapi.exception.IdNotFoundException;
import com.ian.gamerestapi.exception.InvalidIdException;
import com.ian.gamerestapi.model.Game;
import com.ian.gamerestapi.model.Report;
import com.ian.gamerestapi.service.GameService;
import com.ian.gamerestapi.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    private final GameService gameService;
    private final ReportService reportService;

    public Controller(GameService gameService, ReportService reportService) {
        this.gameService = gameService;
        this.reportService = reportService;
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getGame(@PathVariable String id){

        Game game;
        try{
            game = gameService.getGame(id);
        }catch (InvalidIdException e){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }catch(IdNotFoundException e){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.NOT_FOUND
            );
        }catch(IOException e){
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

    @GetMapping("/report")
    public ResponseEntity<Object> getReport(){
        Report report;
        try{
            report = reportService.getReport();
        }catch(IOException e){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                report,
                HttpStatus.OK
        );
    }
}
