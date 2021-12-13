package com.eksamen.projectcalculator.repository;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        //Load driver
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void googleSearch() {
        // Launch website
        driver.navigate().to("localhost:8080/");
        WebElement element = driver.findElement(By.id("password"));
        element.click();
        element = driver.findElement(By.name("password"));
        element.sendKeys("123321");
        element.submit();

        element = driver.findElement(By.id("email"));
        element.click();
        element = driver.findElement(By.name("email"));
        element.sendKeys("admin@hotmail.dk");
        element.submit();


        assertEquals("Cheese! - Google-søgning", driver.getTitle());
    }
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

