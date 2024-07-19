package com.Login.seleniumExample;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNGExample {
  @Test
  public void myFirstTest() {
	  System.out.println("This is my Main Test Method");
  }
  
  @Test
  public void mySecondTest() {
	  System.out.println("This is my Second Test Method");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("This is my beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("This is my After Method");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("This is my Before Class");	  
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("This is my After Class");	  
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("This is my Before Test");	  
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("This is my After Test");	  
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("This is my Before Suite");	  
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("This is my After Suite");	    
  }

}



class TestNGExample2 {
  @Test
  public void myFirstTestE2() {
	  System.out.println("This is my Main Test Method E2");
  }
  
  @Test
  public void mySecondTestE2() {
	  System.out.println("This is my Second Test Method E2");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("This is my beforeMethod E2");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("This is my After Method E2");
  }

//  @BeforeClass
//  public void beforeClass() {
//	  System.out.println("This is my Before Class");	  
//  }

//  @AfterClass
//  public void afterClass() {
//	  System.out.println("This is my After Class");	  
//  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("This is my Before Test E2");	  
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("This is my After Test E2");	  
  }

  @BeforeSuite
  public void beforeSuiteE2() {
	  System.out.println("This is my Before Suite E2");	  
  }

  @AfterSuite
  public void afterSuiteE2() {
	  System.out.println("This is my After Suite E2");	    
  }

}