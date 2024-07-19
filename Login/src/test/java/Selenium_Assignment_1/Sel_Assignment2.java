package Selenium_Assignment_1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@Test
public class Sel_Assignment2 {
	
	WebDriver driver, driver2;
	WebElement table2;
	int noOfRows;
	List<WebElement> rows;
	WebDriverWait wait;
	
@Test	(enabled = true)
  public void test1() {
	  
	  List<WebElement> rows = table2.findElements(By.tagName("tr"));
	  noOfRows = rows.size();
	  System.out.println("Total number of rows in Table2: " + noOfRows + "\n");
	  System.out.println("Test1: Read the data given in the Table2");
	  System.out.println("----------------------------------------");
	  
	  
	  //read table
	  for (int i = 0; i<noOfRows ; i++) {
		  List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
		  //System.out.println(columns);
		  
		  	for(WebElement column: columns) {
			  System.out.print(column.getText() + "\t");
		  	}
		  System.out.println();  
	  }
  }
  
@Test (dependsOnMethods = "test1", enabled = true)
  public void test2() throws InterruptedException {
	  
	  List<WebElement> rows = table2.findElements(By.tagName("tr"));
	  noOfRows = rows.size();
	  
	  System.out.println("\n" + "Test 2: Validate lastname, firstname, email and Due from the table");
	  System.out.println("------------------------------------------------------------------");
	  
	  
	  String[][] array = {{"Smith", "John", "jsmith@gmail.com", "$50.00"},{"Bachcc", "Frank", "fbach@yahoo.com", "$51.00"},{"Doe","Jason","jdoe@hotmail.com","$100.00"},{"Conway","Tim","tconway@earthlink.net","$50.00"}}; 
	  int arrySize = array.length;
	  //System.out.println("array length: " + arrySize + "\n");
	  //System.out.println(array[0][0]);
	  
	  for (int i = 1; i<noOfRows ; i++) {
		  List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
		  
		  WebElement lNameWE = columns.get(0);
		  String lastName = lNameWE.getText();
		  System.out.println("Last name from array is: "+ array[i-1][0] + " Last Name from table is: "+lastName);   
		  Assert.assertEquals(lastName, array[i-1][0]);
		  
		  WebElement fNameWE = columns.get(1);
		  String firstName = fNameWE.getText();
		  System.out.println("First name from array is: "+ array[i-1][1] + " First Name from table is: "+firstName);   
		  Assert.assertEquals(firstName, array[i-1][1]);
		  
		  WebElement emailWE = columns.get(2);
		  String email = emailWE.getText();
		  System.out.println("Email from array is: "+ array[i-1][2] + " Email from table is: "+email);   
		  Assert.assertEquals(email, array[i-1][2]);
	  
		  WebElement dueWE = columns.get(3);
		  String due = dueWE.getText();
		  System.out.println("Due from array is: "+ array[i-1][3] + " Due from table is: "+due);   
		  Assert.assertEquals(due, array[i-1][3]);
		  
		  System.out.println();  
	  }
}	  
  
@Test (enabled = true)
  public void test3() throws InterruptedException {
	  
	  String url = "http://www.frank.com";
	  
	  List<WebElement> rows = table2.findElements(By.tagName("tr"));
	  String urlInTable;
	  for (int i = 0; i<noOfRows ; i++) {
		  List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
		  
		  for(WebElement column: columns) {
			  urlInTable = column.getText();
		  		if(url.equals(urlInTable)) {
		  			driver2 = new ChromeDriver();
		  			driver2.get(urlInTable);
		  			String pageTitle = driver2.getTitle();
		  			System.out.println("\n" + "Test 3: Title of Web Page " + urlInTable + " is: " + pageTitle + "\n");
		  			break;
			  }
		  }  
	  }

  }
  

  @BeforeClass
  public void beforeClass() {
	  driver = new ChromeDriver();
	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	  driver.get("https://the-internet.herokuapp.com/tables");

	  table2 = driver.findElement(By.xpath("//table[@id='table2']"));
	  
  }

  @AfterClass
  public void afterClass() {
	 // driver.quit();
	 // driver2.quit();
  }


}
