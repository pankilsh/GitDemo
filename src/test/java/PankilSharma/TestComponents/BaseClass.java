package PankilSharma.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PankilSharma.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public LoginPage loginPage;
	Properties property;
	FileInputStream fis;

	public BaseClass() throws FileNotFoundException {
		property = new Properties();
		String currentWorkingDir = System.getProperty("user.dir");
		String propertyPath = currentWorkingDir + "\\src\\main\\java\\PankilSharma\\Resources\\GlobalData.properties";
		fis = new FileInputStream(propertyPath);
	}

	public WebDriver initializeBrowser() throws IOException {

		property.load(fis);

		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: property.getProperty("browser");

		if (browser.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			if (browser.contains("headless")) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("Headless");
				driver = new EdgeDriver(options);
				driver.manage().window().setSize(new Dimension(1440, 900));
			} else {
				driver = new EdgeDriver();
			}
		} else if (browser.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (browser.contains("Headless")) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("Headless");
				driver = new FirefoxDriver(options);
				driver.manage().window().setSize(new Dimension(1440, 900));
			} else {
				driver = new FirefoxDriver();
			}
		} else {
			WebDriverManager.chromedriver().setup();
			if (browser.contains("Headless")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("Headless");
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440, 900));
			} else {
				driver = new ChromeDriver();
			}

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApp() throws IOException {

		property.load(fis);
		String url = property.getProperty("url");

		initializeBrowser();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		loginPage = new LoginPage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<HashMap<String, String>>> typeRef = new TypeReference<List<HashMap<String, String>>>() {
		};
		List<HashMap<String, String>> map = mapper.readValue(jsonContent, typeRef);

		return map;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		String screenshotPath = System.getProperty("user.dir") + "\\reports\\screenshots\\" + testCaseName + ".png";
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dstn = new File(screenshotPath);
		FileUtils.copyFile(scr, dstn);
		return screenshotPath;
	}

}
