package PankilSharma.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	ExtentReports extent;
	
	@BeforeTest
	public void reportConfig()
	{
		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setDocumentTitle("Reports Demo");
		reporter.config().setReportName("Sample Report");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pankil Sharma");
	}
	
	
	@Test
	public void testCaseOne() {
		ExtentTest test = extent.createTest("testCaseOne", "test case one description");
		
		String url = "https://rahulshettyacademy.com/client";
		String username = "pankil@automation.com";
		String password = "Password@123";
		String productName = "ADIDAS ORIGINAL";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(url);

		WebElement userEmail = driver.findElement(By.id("userEmail"));
		WebElement userPass = driver.findElement(By.id("userPassword"));
		WebElement loginBtn = driver.findElement(By.id("login"));
		WebElement productElement;

		userEmail.clear();
		userEmail.sendKeys(username);

		userPass.clear();
		userPass.sendKeys(password);

		loginBtn.click();

		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));

		productElement = productList.stream()
				.filter(prod -> prod.findElement(By.tagName("b")).getText().contains(productName))
				.findFirst().orElse(null);
		productElement.findElement(By.cssSelector(".fa-shopping-cart")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		WebElement toastContainer = driver.findElement(By.cssSelector("#toast-container"));
		WebElement toastMsg = toastContainer.findElement(By.cssSelector("div.toast-message"));
		
		wait.until(ExpectedConditions.visibilityOf(toastContainer));
		
		Assert.assertEquals(toastMsg.getText(), "Product Added To Cart");
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toastContainer")));

		driver.findElement(By.cssSelector("button[routerlink*=cart]")).click();
		
		wait.until(ExpectedConditions.urlToBe("https://rahulshettyacademy.com/client/dashboard/cart"));
		
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".infoWrap h3"));
		boolean itemPresentInCart = cartItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		
		if(!itemPresentInCart) {
			test.fail("Item not present in cart");
		}
		Assert.assertTrue(itemPresentInCart);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		String cardNum = "4565 9924 9231 2126";
		String country = "India";
		
		WebElement inputCard = driver.findElement(By.cssSelector(".field input"));
		inputCard.clear();
		inputCard.sendKeys(cardNum);
		
		WebElement selectCountry = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
		Actions act = new Actions(driver);
		act.sendKeys(selectCountry, country).build().perform();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String expectedText = "Thankyou for the order.";
		String actualText = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertEquals(actualText.toUpperCase(), expectedText.toUpperCase());
		driver.close();
		extent.flush();
	}

}
