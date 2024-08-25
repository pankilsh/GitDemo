package PankilSharma.StepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import PankilSharma.PageObjects.LoginPage;
import PankilSharma.TestComponents.BaseClass;
import io.cucumber.java.en.Given;

public class StepDefinitionImplementation extends BaseClass{
	
	public StepDefinitionImplementation() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginPage loginPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		loginPage = (LoginPage) initializeBrowser();
	}
	
	@Given("^logged in with (.+) and (.+)$")
	public void logged_in_with_username_and_password(String username, String Password) {
		
	}

}
