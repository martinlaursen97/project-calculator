package com.eksamen.projectcalculator.repository;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class UiTest {


    @Test
    public static void setup() {

        //Load driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jakob M\\Downloads");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.dk");
        driver.quit();

    }


}

