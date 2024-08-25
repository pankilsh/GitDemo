@tag
Feature: Purchase Order form the Ecommerce site
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce page

  @tag2
  Scenario Outline: Title of your scenario outline
    Given logged in with <username> and <password>
    When I add product <productName> to cart
    And checkout <productName> and submit
    Then "THANKYOU FOR THE ORDER." message is displayed in confirmation page.

    Examples: 
      | username  							| password 						| productName	|
      | "Pankil@Automation.com" | "password@123"     	| ZARA COAT 3	|
