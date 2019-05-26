package com.ian.gameapi.service;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class ReportServiceTest {

    @Test
    public void getReportTestIOException() {
        ResponseEntity<Object> response = ReportService.getInstance().getReport();

        //TODO: Mock GameRepo to throw IOException

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void getReportTestNoComments() {
        ResponseEntity<Object> response = ReportService.getInstance().getReport();

        //TODO: Mock getComments to return an empty List

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getReportTestNoGames() {
        ResponseEntity<Object> response = ReportService.getInstance().getReport();

        //TODO: Mock GameRepo to return an empty array

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getReportTest() {
        ResponseEntity<Object> response = ReportService.getInstance().getReport();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}