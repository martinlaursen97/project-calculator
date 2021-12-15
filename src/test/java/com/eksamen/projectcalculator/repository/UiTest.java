package com.eksamen.projectcalculator.repository;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class UiTest {

    public static void main(String[] args) {
        setup();
    }


    @Test
    public static void setup() {

        //Load driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jakob M\\Downloads\\chromedriver_win32");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.dk");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.quit();

    }


}

