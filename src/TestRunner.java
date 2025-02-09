package com.tests;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class TestRunner {
	private JsonNode testData; // Declare testData as a class-level variable
	private Logintests loginTests; // Create an instance of Logintests class
	//For TestNG
	private WebDriver driver;
	//driver = new ChromeDriver();

	
	// When running as Java Application
//	public static void main(String[] args) {
//		TestRunner testRunner = new TestRunner();
//		testRunner.loginfromjson(); // Load JSON data first
//
//		WebDriver driver = new ChromeDriver();
//		try {
//			driver.get("https://www.saucedemo.com/");
//
//			// Run both valid and invalid credentials tests using Logintests methods
//			testRunner.runTest(driver);
//			// ***Call the productListingPageTests method here***
//			testRunner.productListingPageTests(driver);
//			//Call the testadd to cart method here
//			testRunner.testAddtoCart(driver);
//		} finally {
//			driver.quit();
//		}
//	}
	
	//When running as TestNG
	@BeforeClass
	public void setUp() {
		//initialize Webdriver
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		loginfromjson();
	}
	
	

	// Load test data from JSON file
	public void loginfromjson() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			testData = objectMapper.readTree(new File("D:\\Electronic Store Workspace\\OrangeHRMS\\src\\TestData\\Credentials.json"));
			System.out.println(testData);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error Loading JSON File: " + e.getMessage());
		}

		// Initialize Logintests class
		loginTests = new Logintests();
		System.out.println("Logintests initilaized");
	}

	// Run test cases for both valid and invalid credentials
	@Test(priority = 1)
	public void runTest() {
		if (testData == null) {
			Assert.fail("TestData is null, check the JSON file");
			
		//	System.err.println("TestData is null, check the JSON file");  //For running as java application
			return;
		}

		// **Test Invalid Credentials**
		JsonNode invalidCredentials = testData.get("invalidCredentials");
		if (invalidCredentials != null) {
			loginTests.testInvalidCredentials(driver, invalidCredentials);
		}

		// **Test Valid Credentials**
		JsonNode validCredentials = testData.get("validCredentials");
		if (validCredentials != null) {
			loginTests.testValidCredentials(driver, validCredentials);
		}
	}
	//Verify the first product name and price
	@Test(priority = 2)
	public void productListingPageTests(){
		ProductListingPage plp = new ProductListingPage();

		// Verify product name
		if (plp.verifyFirstProductName(driver)) {
			System.out.println("Test Passed: First product is 'Sauce Labs Backpack'.");
		} else {
			System.out.println("Test Failed: First product is not 'Sauce Labs Backpack'.");
		}
		//Verify Product name using Assert
		//Assert.assertTrue(plp.verifyFirstProductName(driver), "Test Failed: First product is not 'Sauce Labs Backpack'.");

		// Verify product price
		if (plp.verifyFirstProductPrice(driver)) {
			System.out.println("Test Passed: Price is $29.99.");
		} else {
			System.out.println("Test Failed: Price is not $29.99.");
		}
		// Verify Product price using Assert
		//Assert.assertTrue(plp.verifyFirstProductPrice(driver), "Test Failed: Price is not $29.99.");


	}
	@Test(priority = 3)
	public void testAddtoCart() {
		// WebDriver driver = new ChromeDriver();
		AddToCartFromPLP addToCartPage = new AddToCartFromPLP();
		addToCartPage.addToCart(driver);
		Assert.assertTrue(addToCartPage.isRemoveButtonDisplayed(driver), "Remove button is not displayed after adding to cart.");
        System.out.println("Added the product(Sauce Labs Backpack) to cart successfully ");
		addToCartPage.removeFromCart(driver);
		Assert.assertTrue(addToCartPage.isAddToCartButtonDisplayed(driver), "Add to cart button is not displayed after removing from cart.");
		System.out.println("Removed the product(Sauce Labs Backpack) from cart successfully ");
	}
	@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
	
}



