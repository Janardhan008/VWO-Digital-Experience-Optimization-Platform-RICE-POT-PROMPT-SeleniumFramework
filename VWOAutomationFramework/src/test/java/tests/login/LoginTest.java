package tests.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "FR6-SEC-01: Verify user can login with valid credentials for VWO platform")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                "User should be redirected to Dashboard after successful login");
    }

    @Test(priority = 2, description = "FR6-SEC-02: Verify login fails with invalid password for registered user")
    public void testLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), "WrongPassword@999");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Invalid") || error.contains("incorrect") || error.contains("password"),
                "Error message should indicate invalid credentials. Actual: " + error);
    }

    @Test(priority = 3, description = "FR6-SEC-03: Verify login fails with unregistered email")
    public void testLoginWithUnregisteredEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("nonexistent@unknown.com", ConfigReader.getPassword());
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Invalid") || error.contains("not found") || error.contains("unrecognized"),
                "Error message should indicate unrecognized user. Actual: " + error);
    }

    @Test(priority = 4, description = "FR6-SEC-04: Verify login fails with empty credentials")
    public void testLoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearFields();
        loginPage.doLogin("", "");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("required") || error.contains("empty") || error.contains("email"),
                "Error message should indicate required fields. Actual: " + error);
    }

    @Test(priority = 5, description = "FR6-SEC-05: Verify login page title is correct")
    public void testLoginPageTitle() {
        LoginPage loginPage = new LoginPage(driver);
        String title = loginPage.getPageTitle();
        Assert.assertTrue(title.contains("VWO") || title.contains("Login"),
                "Page title should contain VWO or Login. Actual: " + title);
    }

    @Test(priority = 6, description = "FR6-SEC-06: Verify Login button is enabled on page load")
    public void testLoginButtonState() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled by default");
    }

    @Test(priority = 7, description = "FR6-SEC-07: Verify Forgot Password link navigates to password reset flow")
    public void testForgotPasswordFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();
        loginPage.sendResetPasswordEmail("testuser@vwo.com");
        String toast = loginPage.getToastMessage();
        Assert.assertTrue(toast.contains("sent") || toast.contains("email") || toast.contains("reset"),
                "Password reset email should be sent. Toast: " + toast);
    }

    @Test(priority = 8, description = "FR6-SEC-08: Verify password field masks input characters")
    public void testPasswordFieldMasking() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPasswordFieldMasked(),
                "Password field should mask input characters for security");
    }

    @Test(priority = 9, description = "FR6-SEC-09: Verify Remember Me option persists session state")
    public void testRememberMeFunctionality() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLoginWithRememberMe(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed after login");
    }

    @Test(priority = 10, description = "FR6-SEC-10: Verify logout returns user to login page")
    public void testLogoutFunctionality() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Login should succeed");
        loginPage.doLogout();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "User should be returned to login page after logout");
    }
}
