package PankilSharma.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PankilSharma.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".infoWrap h3")
	List<WebElement> cartItems;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;

	public boolean isItemPresentInCart(String productName) {
		return cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
	}

	public void checkOut() {
		waitForElementToAppear(checkoutButton);
		checkoutButton.click();
	}

}
