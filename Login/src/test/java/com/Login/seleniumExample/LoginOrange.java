package com.Login.seleniumExample;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginOrange {
  
	WebDriver driver;
	
	@Test
  public void loginFunction () {
		
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		WebElement userNameElement = driver.findElement(By.name("username"));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("username")))).sendKeys("Admin");
		
		userNameElement.sendKeys("Admin");
		
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys("admin123");
		
		
		driver.findElement(By.className("orangehrm-login-button")).click();
		
		}
  
	@BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  	  
	  driver = new ChromeDriver();
	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  //Thread.sleep(1000);
  }
  

  @AfterMethod
  public void afterMethod() {
	 //driver.quit();
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("This is my Before Class Method");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("This is my After Class Method");
	  }

}
