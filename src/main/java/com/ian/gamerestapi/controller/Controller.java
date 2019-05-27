package com.ian.gamerestapi.controller;

import com.ian.gamerestapi.service.GameService;
import com.ian.gamerestapi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private GameService gameService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getGame(@PathVariable String id){
        return gameService.getGame(id);
    }

    @GetMapping("/report")
    public ResponseEntity<Object> getReport(){
        return reportService.getReport();
    }
}
