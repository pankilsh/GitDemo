package PankilSharma.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PankilSharma.AbstractComponent.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr.ng-star-inserted td:nth-child(3)")
	List<WebElement> orderList;
	
	public boolean isItemPresentInOrder(String productName) {
		goToOrders();
		return orderList.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
	}

}
