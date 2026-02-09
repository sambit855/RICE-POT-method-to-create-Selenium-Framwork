package com.salesforce.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.salesforce.pages.LoginPage;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "Verify login fails with invalid username")
    public void testLoginWithInvalidUsername() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateToLoginPage();
            
            loginPage.performLogin("invalid.user@example.com", "ValidPassword123");
            
            Thread.sleep(2000);
            
            boolean isErrorDisplayed = loginPage.isInvalidUsernameErrorDisplayed();
            Assert.assertTrue(isErrorDisplayed, "Invalid username error should be displayed");
            
            String currentURL = loginPage.getCurrentPageURL();
            Assert.assertTrue(currentURL.contains("login.salesforce.com"), 
                "Should remain on login page with invalid username");
            
        } catch (Exception e) {
            throw new Exception("Test failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify login fails with invalid password")
    public void testLoginWithInvalidPassword() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateToLoginPage();
            
            loginPage.performLogin("test.user@example.com", "InvalidPassword123");
            
            Thread.sleep(2000);
            
            boolean isErrorDisplayed = loginPage.isInvalidPasswordErrorDisplayed();
            Assert.assertTrue(isErrorDisplayed, "Invalid password error should be displayed");
            
            String currentURL = loginPage.getCurrentPageURL();
            Assert.assertTrue(currentURL.contains("login.salesforce.com"), 
                "Should remain on login page with invalid password");
            
        } catch (Exception e) {
            throw new Exception("Test failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify login fails with both invalid credentials")
    public void testLoginWithBothInvalidCredentials() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateToLoginPage();
            
            loginPage.performLogin("invalid.user@example.com", "InvalidPassword123");
            
            Thread.sleep(2000);
            
            String currentURL = loginPage.getCurrentPageURL();
            Assert.assertTrue(currentURL.contains("login.salesforce.com"), 
                "Should remain on login page with invalid credentials");
            
            boolean isUsernameErrorDisplayed = loginPage.isInvalidUsernameErrorDisplayed();
            boolean isPasswordErrorDisplayed = loginPage.isInvalidPasswordErrorDisplayed();
            Assert.assertTrue(isUsernameErrorDisplayed || isPasswordErrorDisplayed, 
                "Error message should be displayed for invalid credentials");
            
        } catch (Exception e) {
            throw new Exception("Test failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify login fails with empty credentials")
    public void testLoginWithEmptyCredentials() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateToLoginPage();
            
            loginPage.enterUsername("");
            loginPage.enterPassword("");
            loginPage.clickLoginButton();
            
            Thread.sleep(2000);
            
            String currentURL = loginPage.getCurrentPageURL();
            Assert.assertTrue(currentURL.contains("login.salesforce.com"), 
                "Should remain on login page with empty credentials");
            
        } catch (Exception e) {
            throw new Exception("Test failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify login fails with only username")
    public void testLoginWithOnlyUsername() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateToLoginPage();
            
            loginPage.enterUsername("test.user@example.com");
            loginPage.enterPassword("");
            loginPage.clickLoginButton();
            
            Thread.sleep(2000);
            
            String currentURL = loginPage.getCurrentPageURL();
            Assert.assertTrue(currentURL.contains("login.salesforce.com"), 
                "Should remain on login page when password is empty");
            
        } catch (Exception e) {
            throw new Exception("Test failed: " + e.getMessage(), e);
        }
    }
}
