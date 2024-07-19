package com.Login.seleniumExample;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class LocatorsNew {
	
	WebDriver driver;
//	@Test
//	public void login() {
//		WebElement uName = driver.findElement(null)
//	}
	
  @Test (enabled = false)
  public void byID() {
	  WebElement userNameElment = driver.findElement(By.id("email"));
	  userNameElment.sendKeys("Yaaminee");
	  
	  //instead of above can be used below too
	  //driver.findElement(By.id("email")).sendKeys("Yaaminee");
  }
  
  @Test (enabled = false)
  public void byName() {
	  WebElement passwordElement = driver.findElement(By.name("password"));
	  passwordElement.sendKeys("Yaampassword");
  }
  
  @Test (enabled = false)
  public void byLink() throws InterruptedException {
	  WebElement link = driver.findElement(By.linkText("REGISTER"));
	  link.click();
	  Thread.sleep(3000);
  }
  
  @Test (enabled = false)
  public void byPartialLink() throws InterruptedException {
	  WebElement parlink = driver.findElement(By.partialLinkText("SIGN"));
	  parlink.click();
	  Thread.sleep(3000);
  }
  
  @Test
  public void cssSelectorEx() {
	  driver.findElement(By.cssSelector("input#email")).sendKeys("byaamee");
  }
  
  @Test
  public void cssSelectorByClass() {
	  driver.findElement(By.cssSelector("input.oxd-input oxd-input--active")).sendKeys("user");
  }
  
  
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  Thread.sleep(3000);
  }

  @AfterMethod
  public void afterMethod() {
	 
  }

  @BeforeClass
  public void beforeClass() {
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  //driver.get("https://demo.guru99.com/test/newtours/register.php");
	  //driver.get("https://demo.guru99.com/test/newtours/login.php");
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
  }

  @AfterClass
  public void afterClass() {
	// driver.quit();
  }

}
