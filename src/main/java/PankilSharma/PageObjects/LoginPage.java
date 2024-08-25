package PankilSharma.PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PankilSharma.AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css = ".forgot-password-link")
	WebElement forgotPassword;
	
	@FindBy(css = ".login-wrapper-footer-text")
	WebElement registerHere;
	
	@FindBy(css = ".ng-trigger-flyInOut")
	WebElement errorMsg;
	
	public void loginApp(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
	}
	
	public String getErrorMsg() {
		waitForElementToAppear(errorMsg);
		return errorMsg.getText();
	}

}
