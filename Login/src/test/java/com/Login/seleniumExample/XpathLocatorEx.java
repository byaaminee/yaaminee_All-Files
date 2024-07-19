package com.Login.seleniumExample;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class XpathLocatorEx {
	
	WebDriver driver;
	
  @Test
  public void f() throws InterruptedException {
	  
	  driver.findElement(By.xpath("//input[@id='my-text-id']")).sendKeys("Yaam");
	  driver.findElement(By.xpath("//input[contains(@name,'my-pass')]")).sendKeys("Bala");
	  //from parent element to child element.. within label tag "" given inside it is text
	  driver.findElement(By.xpath("//label[contains(text(),'Textare')]/textarea")).sendKeys("XPath example");
	  
	  Thread.sleep(3000);
	  
	  //driver.findElement(By.id("my-text-id")).clear();
	  driver.findElement(By.xpath("//input[@id='my-text-id']")).clear();
	  driver.findElement(By.xpath("//input[contains(@name,'my-pass')]")).clear();
	  
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//input[@type='text' and @name ='my-text-id'")).sendKeys("Yaam New");
	  driver.findElement(By.xpath("//input[@type='text' and @name ='my-password'")).sendKeys("BalaNew");
	  
	  
	  //driver.findElement(By.xpath("//input[@type='text' and @name='my-text']")).sendKeys("Srikant_new");
	  //driver.findElement(By.xpath("//input[@name='my-password' or @type='password']")).sendKeys("Srikant_new");
	  
  }
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  driver = new ChromeDriver();
	  driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
	  Thread.sleep(3000);
	  
  }
  
  

  @AfterMethod
  public void afterMethod() {
  }

}
