package PankilSharma.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import PankilSharma.PageObjects.CartPage;
import PankilSharma.PageObjects.CheckOutPage;
import PankilSharma.PageObjects.OrdersPage;
import PankilSharma.PageObjects.ProductCatalouge;
import PankilSharma.PageObjects.SummaryPage;
import PankilSharma.TestComponents.BaseClass;

public class SubmitOrderTest extends BaseClass {
	public SubmitOrderTest() throws IOException {
		super();
	}

	@DataProvider(name = "productsDataProvider")
	public Object[][] productData() {
		return new Object[][] { { "ADIDAS ORIGINAL" }, { "ZARA COAT 3" }, { "IPHONE 13 PRO" } };
	}

	@DataProvider
	public Object[][] loginDataProvider() throws IOException {
		String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\PankilSharma\\Data\\LoginDetails.json";
		List<HashMap<String, String>> map = getJsonDataToMap(jsonFilePath);
		return new Object[][] {{map.get(0)},{map.get(1)},{map.get(2)}};
	}

	@Test(dataProvider = "loginDataProvider")
	public void loginTest(HashMap<String, String> input) throws IOException {
		loginPage.loginApp(input.get("email"), input.get("password"));
	}

	@Test(dataProvider = "productsDataProvider")
	public void testCaseOne(String productName) throws IOException {
		String email = "pankil@automation.com";
		String password = "Password@123";
		String cardNum = "4565 9924 9231 2126";
		String country = "India";
		String expectedMsg = "Thankyou for the order.";

		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		CartPage cartPage = new CartPage(driver);
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		SummaryPage summaryPage = new SummaryPage(driver);

		loginPage.loginApp(email, password);

		productCatalouge.addProductToCart(productName);
		productCatalouge.goToCart();

		Assert.assertTrue(cartPage.isItemPresentInCart(productName));
		cartPage.checkOut();

		checkOutPage.enterCardNumber(cardNum);
		checkOutPage.enterCountry(country);
		checkOutPage.placeOder();

		String actualMsg = summaryPage.getSummaryMsg();

		Assert.assertTrue(actualMsg.equalsIgnoreCase(expectedMsg));
	}

	public void orderDetails() {
		String productName = "ADIDAS ORIGINAL";
		OrdersPage ordersPage = new OrdersPage(driver);
		Assert.assertTrue(ordersPage.isItemPresentInOrder(productName));

	}

}
