package org.testerselenium.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavaScript {
	
	private WebDriver driver;
	String expectedResult = null;
	String actualResult = null;
	String baseUrl = "http://newtours.demoaut.com/";
	String chromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
	private JavascriptExecutor js;
	String pageLoadStatus = "";
	
	private boolean highLight(WebElement element) {
		js = (JavascriptExecutor)driver;
		for (int i = 0; i < 3; i++) {
			try {
				js.executeScript("arguments[0].setAttribute('style', 'background:red')", element);
				Thread.sleep(1000);
				js.executeScript("arguments[0].setAttribute('style', 'background:')", element);
			} catch (Exception e) {
				System.err.println("JavaScript | Method: highLight | Exception desc: " + e.getMessage());
				return false;
			}			
		}
		return true;
	}
	
	private boolean scrollWindow() {
		try {
			js = (JavascriptExecutor)driver;
			//Scroll up(0, -250)/down(0,250)
			js.executeScript("window.scrollBy(0,250)");
		} catch (Exception e) {
			System.err.println("JavaScript | Method: scrollWindow | Exception desc: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean waitForPageToLoad(){
		try {
			do {
				js = (JavascriptExecutor)driver;
				pageLoadStatus = (String)js.executeScript("return document.readyState");
			} while (!pageLoadStatus.equals("complete"));
			
		} catch (Exception e) {
			System.err.println("JavaScript | Method: waitForPageToLoad | Exception desc: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
		waitForPageToLoad();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 0)
	public void goToRegisterPage() {
		WebElement lnkRegister = driver.findElement(By.linkText("REGISTER"));
		Assert.assertTrue(highLight(lnkRegister));
		js.executeScript("arguments[0].click()", lnkRegister);
		expectedResult = "Register: Mercury Tours";
		actualResult = driver.getTitle();
		Assert.assertEquals(actualResult, expectedResult);
		Assert.assertTrue(scrollWindow());
	}
	
	@Test(priority = 1)
	public void registerAnUser() {
		try {
			WebElement txtFirstName = driver.findElement(By.name("firstName"));
			highLight(txtFirstName);
			txtFirstName.sendKeys("Gilberto S");
			
			WebElement ddlCountry = driver.findElement(By.name("country"));
			highLight(ddlCountry);
			new Select(ddlCountry).selectByVisibleText("AUSTRIA");
			
			highLight(driver.findElement(By.id("email")));
			driver.findElement(By.id("email")).sendKeys("gilberto@gmail.com");
			
			highLight(driver.findElement(By.name("password")));
			driver.findElement(By.name("password")).sendKeys("123");
			
			WebElement txtConfirmPass = driver.findElement(By.name("confirmPassword"));
			highLight(txtConfirmPass);
			txtConfirmPass.sendKeys("123");
			txtConfirmPass.submit();
			
			Assert.assertTrue(waitForPageToLoad());
			
			highLight(driver.findElement(By.xpath("//*[contains(text(),'Note')]")));
			
		} catch (NoSuchElementException ne) {
			Assert.fail("Test Failed!: element was not found");
		} catch (Exception e) {
			Assert.fail("Test failed! " + e.getMessage());
		}
	}
	
	
}
