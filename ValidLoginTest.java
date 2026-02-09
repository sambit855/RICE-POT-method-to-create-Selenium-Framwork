package com.salesforce.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.salesforce.pages.LoginPage;

public class ValidLoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testValidLogin() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateToLoginPage();
            
            String pageTitle = loginPage.getCurrentPageTitle();
            Assert.assertNotNull(pageTitle, "Page title should not be null");
            Assert.assertTrue(pageTitle.contains("Salesforce"), "Page should be Salesforce login page");
            
            loginPage.performLogin("test.user@example.com", "ValidPassword123");
            
            Thread.sleep(3000);
            
            String currentURL = loginPage.getCurrentPageURL();
            Assert.assertFalse(currentURL.contains("login.salesforce.com"), 
                "URL should not contain login page after successful login");
            
        } catch (Exception e) {
            throw new Exception("Test failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify login with remember me checkbox")
    public void testLoginWithRememberMe() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateToLoginPage();
            
            loginPage.clickRememberMeCheckbox();
            loginPage.performLogin("test.user@example.com", "ValidPassword123");
            
            Thread.sleep(3000);
            
            String currentURL = loginPage.getCurrentPageURL();
            Assert.assertFalse(currentURL.contains("login.salesforce.com"), 
                "URL should not contain login page after successful login with remember me");
            
        } catch (Exception e) {
            throw new Exception("Test failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify login page loads successfully")
    public void testLoginPageLoad() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateToLoginPage();
            
            String pageURL = loginPage.getCurrentPageURL();
            Assert.assertTrue(pageURL.contains("login.salesforce.com"), 
                "Page should be Salesforce login page");
            
            String pageTitle = loginPage.getCurrentPageTitle();
            Assert.assertNotNull(pageTitle, "Page title should be available");
            
        } catch (Exception e) {
            throw new Exception("Test failed: " + e.getMessage(), e);
        }
    }
}
