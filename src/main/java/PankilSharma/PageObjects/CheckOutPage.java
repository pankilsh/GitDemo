package PankilSharma.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PankilSharma.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;
	Actions act;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		act = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".field input")
	WebElement inputCardNumber;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(css = ".ta-results")
	WebElement countryContainer;

	@FindBy(css = ".ta-item")
	List<WebElement> countryResults;

	@FindBy(css = ".action__submit")
	WebElement placeOrderEl;

	public void enterCardNumber(String cardNum) {
		inputCardNumber.clear();
		act.sendKeys(inputCardNumber, cardNum).build().perform();
	}

	public void enterCountry(String country) {
		act.sendKeys(selectCountry, country).build().perform();
		waitForElementToAppear(countryContainer);
		WebElement foundCountry = countryResults.stream().filter(item -> item.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		foundCountry.click();
	}
	
	public void placeOder() {
		placeOrderEl.click();	
	}

}
