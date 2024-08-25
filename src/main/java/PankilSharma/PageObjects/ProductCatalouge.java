package PankilSharma.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PankilSharma.AbstractComponent.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {

	WebDriver driver;

	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productList;

	By productsLocator = By.cssSelector(".mb-3");
	
	By addToCartLocator = By.cssSelector(".fa-shopping-cart");
	
	@FindBy(css = "#toast-container")
	WebElement toastContainerElement;
	
	@FindBy(css = "div.toast-message")
	WebElement toastMsgElement;
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsLocator);
		return productList;
	}

	public WebElement getProductItem(String productName) {
		return productList.stream().filter(prod -> prod.findElement(By.tagName("b")).getText().contains(productName))
				.findFirst().orElse(null);
	}
	
	public String getAlertMessage() {
		return toastMsgElement.getText();
	}
	
	public void addProductToCart(String productName) {
		getProductItem(productName).findElement(addToCartLocator).click();
		waitForElementToAppear(toastContainerElement);
		waitForElementToDisappear(toastContainerElement);
	}
	
	

}
