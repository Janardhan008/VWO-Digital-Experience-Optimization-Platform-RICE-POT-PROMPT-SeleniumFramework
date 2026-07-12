package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "login-email")
    private WebElement emailField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login-btn")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class, 'error-message')]")
    private WebElement errorMessage;

    @FindBy(linkText = "Forgot Password?")
    private WebElement forgotPasswordLink;

    @FindBy(id = "remember-me")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//a[contains(text(), 'Sign up')]")
    private WebElement signUpLink;

    @FindBy(xpath = "//div[contains(@class, 'two-factor')]//input")
    private WebElement twoFactorCodeInput;

    @FindBy(id = "verify-2fa-btn")
    private WebElement verify2FABtn;

    @FindBy(xpath = "//div[contains(@class, 'user-avatar')]")
    private WebElement userAvatar;

    @FindBy(linkText = "Log out")
    private WebElement logoutLink;

    @FindBy(xpath = "//div[contains(@class, 'toast-message')]")
    private WebElement toastMessage;

    @FindBy(id = "password-reset-email")
    private WebElement resetEmailField;

    @FindBy(id = "send-reset-link")
    private WebElement sendResetLinkBtn;

    @FindBy(xpath = "//div[contains(@class, 'sso-btn')]")
    private WebElement ssoLoginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void doLogin(String email, String password) {
        sendKeys(emailField, email);
        sendKeys(passwordField, password);
        clickElement(loginButton);
        waitForPageLoad();
    }

    public void doLoginWithRememberMe(String email, String password) {
        sendKeys(emailField, email);
        sendKeys(passwordField, password);
        if (!isElementSelected(rememberMeCheckbox)) {
            clickElement(rememberMeCheckbox);
        }
        clickElement(loginButton);
        waitForPageLoad();
    }

    public String getErrorMessage() {
        return isElementDisplayed(errorMessage) ? getText(errorMessage) : "";
    }

    public boolean isLoginButtonEnabled() {
        return isElementEnabled(loginButton);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginButton);
    }

    public void clickForgotPassword() {
        clickElement(forgotPasswordLink);
    }

    public void sendResetPasswordEmail(String email) {
        sendKeys(resetEmailField, email);
        clickElement(sendResetLinkBtn);
    }

    public String getToastMessage() {
        return isElementDisplayed(toastMessage) ? getText(toastMessage) : "";
    }

    public void enter2FACode(String code) {
        sendKeys(twoFactorCodeInput, code);
        clickElement(verify2FABtn);
        waitForPageLoad();
    }

    public boolean is2FAFieldDisplayed() {
        return isElementDisplayed(twoFactorCodeInput);
    }

    public void doLogout() {
        clickElement(userAvatar);
        clickElement(logoutLink);
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(userAvatar);
    }

    public void clearFields() {
        emailField.clear();
        passwordField.clear();
    }

    public void clickSSOLogin() {
        clickElement(ssoLoginButton);
    }

    public void navigateToSignUp() {
        clickElement(signUpLink);
    }

    public boolean isPasswordFieldMasked() {
        return getAttributeValue(passwordField, "type").equals("password");
    }
}
