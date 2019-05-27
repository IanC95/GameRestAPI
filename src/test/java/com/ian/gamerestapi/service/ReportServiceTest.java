package com.ian.gamerestapi.service;

import com.ian.gamerestapi.model.Comment;
import com.ian.gamerestapi.model.Game;
import com.ian.gamerestapi.model.GameLikes;
import com.ian.gamerestapi.model.Report;
import com.ian.gamerestapi.repository.GameRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

    @Mock
    private GameRepo gameRepo;

    private ReportService reportService;

    private Game testGame;
    private Game[] games;
    @Before
    public void init(){
        reportService = new ReportService(gameRepo);
        testGame = new Game();
        games = new Game[1];
    }

    @Test(expected = IOException.class)
    public void getReportTestIOException() throws IOException{
        Mockito.when(gameRepo.findAllGames()).thenThrow(new IOException());

        reportService.getReport();
    }

    @Test
    public void getReportTestNoComments() throws IOException {
        games[0] = testGame;

        Mockito.when(gameRepo.findAllGames()).thenReturn(games);
        Report report = reportService.getReport();

        assertEquals("", report.getUser_with_most_comments());
    }

    @Test
    public void getReportTest() throws IOException{
        testGame.setTitle("Title");
        testGame.setAge_rating("12");
        testGame.setBy("Company");
        testGame.setDescription("Desc");
        testGame.setLikes(5);

        testGame.setComments(new ArrayList<>(Arrays.asList(
                new Comment("User", "Message", 10000L, 5),
                new Comment("User", "Message2", 10000L, 5),
                new Comment("User2", "Message3", 20000L, 4)
        )));

        games[0] = testGame;

        Report expectedReport = new Report();

        expectedReport.setHighest_rated_game("Title");
        expectedReport.setUser_with_most_comments("User");
        expectedReport.setAverage_likes_per_game(new GameLikes[]{new GameLikes("Title", 5)});

        Mockito.when(gameRepo.findAllGames()).thenReturn(games);
        Report report = reportService.getReport();

        assertEquals(expectedReport, report);
    }
}