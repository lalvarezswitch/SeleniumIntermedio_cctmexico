package org.testerselenium.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG {
	String baseUrl = "http://newtours.demoaut.com/index.php";
	WebDriver driver;
	String expectedResult = "";
	String actualResult = "";
	String chromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
	
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();		
	}
	
	@BeforeMethod
	public void verifyHomePageTitle() {
		expectedResult = "Welcome: Mercury Tours";
		actualResult = driver.getTitle();
		Assert.assertEquals(actualResult, expectedResult, "Title is not equals");
	}
	
	@AfterMethod
	public void goBakToHomePage() {
		driver.findElement(By.linkText("Home")).click();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void register() {
		driver.findElement(By.xpath("//td[@class='mouseOut']/a[contains(text(),'REGISTER')]")).click();
		expectedResult = "Register: Mercury Tours";
		actualResult = driver.getTitle();
		Assert.assertEquals(actualResult, expectedResult, "Title is not equals");		
	}
	
	@Test
	public void support() {
		driver.findElement(By.xpath("//td[@class='mouseOut']/a[contains(text(),'SUPPORT')]")).click();
		expectedResult = "Under Construction: Mercury Tours";
		actualResult = driver.getTitle();
		Assert.assertEquals(actualResult, expectedResult, "Title is not equals");
	}

}
