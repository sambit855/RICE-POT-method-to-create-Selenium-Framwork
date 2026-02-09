package com.salesforce.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.salesforce.utilities.DriverManager;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        try {
            driver = DriverManager.initializeDriver();
        } catch (Exception e) {
            throw new Exception("Setup failed: " + e.getMessage(), e);
        }
    }

    @AfterTest
    public void tearDown() {
        try {
            DriverManager.quitDriver();
        } catch (Exception e) {
            System.err.println("Teardown failed: " + e.getMessage());
        }
    }
}
