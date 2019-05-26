package com.ian.gameapi.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class ReportServiceTest {

    @Autowired
    private ReportService reportService;
    @Test
    public void getReportTestIOException() {
        ResponseEntity<Object> response = reportService.getReport();

        //TODO: Mock GameRepo to throw IOException

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void getReportTestNoComments() {
        ResponseEntity<Object> response = reportService.getReport();

        //TODO: Mock getComments to return an empty List

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getReportTestNoGames() {
        ResponseEntity<Object> response = reportService.getReport();

        //TODO: Mock GameRepo to return an empty array

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getReportTest() {
        ResponseEntity<Object> response = reportService.getReport();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}