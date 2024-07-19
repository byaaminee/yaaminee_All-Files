package com.Login.seleniumExample;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.apache.commons.collections4.Get;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserControlEx {
	WebDriver driver;
  @Test (enabled = false)
  public void validatePageTitle() throws InterruptedException {
	  String title = driver.getTitle();
	  System.out.println("Title of the page is: " + title);
	  Thread.sleep(2000);
  }
  
  @Test (dependsOnMethods = "validatePageTitle", enabled = false) 
  public void getURL() {
	  
	  driver.findElement(By.xpath("//a[text()='WebTable']")).click();
	  
	  String url = driver.getCurrentUrl();
	  System.out.println("Current url: " + url );
  }
  
  
  @Test (dependsOnMethods = "getURL", enabled = false) 
  public void getSource() {
	  
	  
	  String getPageSourceVal = driver.getPageSource();
	  System.out.println("Page Source is: " + getPageSourceVal);
	  boolean result = getPageSourceVal.contains("Automation Demo Site");
	  
	  if(result) {
		  System.out.println("Message is present");
	  }
	  else {
		  System.out.println("Message is not present");  
	  }
	  
	  driver.findElement(By.xpath("//a[text()='WebTable']")).click();
	  
	  String url = driver.getCurrentUrl();
	  System.out.println("Current url: " + url );
  }
  
  
  @Test
 // public void 
  
  @BeforeClass
  public void beforeMethod() throws InterruptedException {
	  driver = new ChromeDriver();
	  driver.get("https://demo.automationtesting.in/Register.html");
	  Thread.sleep(3000);
  }

  @AfterClass
  public void afterMethod() {
  
	  driver.close(); //close only the particular tab
	  driver.quit();  //close all the tabs opened
  }

}
