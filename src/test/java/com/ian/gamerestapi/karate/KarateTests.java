package com.ian.gamerestapi.karate;

import com.ian.gamerestapi.GameApiApplication;
import com.intuit.karate.junit4.Karate;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;

@RunWith(Karate.class)
public class KarateTests {

    @BeforeClass
    public static void init(){
        SpringApplication.run(GameApiApplication.class, "");
    }
}
