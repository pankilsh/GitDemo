package PankilSharma.PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import PankilSharma.AbstractComponent.AbstractComponent;

public class SummaryPage extends AbstractComponent{

	WebDriver driver;
	Actions act;
	WebDriverWait wait;
	
	public SummaryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		act = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(css = ".hero-primary")
	WebElement summaryMsg;
	
	public String getSummaryMsg() {
		return summaryMsg.getText();
	}

}
