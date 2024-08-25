package PankilSharma.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PankilSharma.PageObjects.CartPage;
import PankilSharma.PageObjects.OrdersPage;

public class AbstractComponent {

	public CartPage cartPage;
	public OrdersPage ordersPage;
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "button[routerlink*=cart]")
	WebElement cartButton;
	
	@FindBy(css = "button[routerlink*=myorders]")
	WebElement ordersButton;
	
	public CartPage goToCart() {
		cartButton.click();
		cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrders() {
		ordersButton.click();
		ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
	
	
	public void waitForElementToAppear(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForURLToBe(String url) {
		wait.until(ExpectedConditions.urlToBe(url));
	}
	
	
}
