package com.ian.gameapi.controller;

import com.ian.gameapi.service.GameService;
import com.ian.gameapi.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getGame(@PathVariable String id){
        return GameService.getInstance().getGame(id);
    }

    @GetMapping("/report")
    public ResponseEntity<Object> getReport(){
        return ReportService.getInstance().getReport();
    }
}
