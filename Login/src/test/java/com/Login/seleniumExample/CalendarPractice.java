package com.Login.seleniumExample;

import org.testng.annotations.Test;

import com.lowagie.text.List;

import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
	

public class CalendarPractice {
  
	WebDriver driver;
	
	@Test
  public void f() {
	  
	  driver.switchTo().frame(driver.findElement(By.xpath("//iFrame[@class='demo-frame']")));
	 // WebElement dateText = driver.findElement(By.xpath("//input[@id='datepicker']"));
	  
	  //List<WebElement> dates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody//td/a"));
	  
  }
  @BeforeClass
  public void beforeClass() {
	  
	  driver = new ChromeDriver();
	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	  driver.get("https://jqueryui.com/datepicker/");
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
  }

}
