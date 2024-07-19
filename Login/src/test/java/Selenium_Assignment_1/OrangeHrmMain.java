package Selenium_Assignment_1;

import java.time.Duration;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.chrome.ChromeDriver;

public class OrangeHrmMain {
	
	public static WebDriver driver;
	
	public static void main(String args[]) throws InterruptedException {
		
		System.out.println("Test Case 1: "); 
		wrongUNwrongPWD();
		System.out.println("\n" + "Test Case 2: ");
		correctUNWrongPWD();
		System.out.println( "\n" + "Test Case 3: ");
		wrongUNcorrectPWD();
		System.out.println( "\n" + "Test Case 4: ");
		correctUNcorrectPWD();
		
	}

	public static void wrongUNwrongPWD() throws InterruptedException {
		
		userLoginCredentials("user", "admin123");
				
		WebElement errMsg = driver.findElement(By.xpath("//div[contains(@class,'oxd-alert--error')]"));
		boolean errDisplay = errMsg.isDisplayed();
		
		if(errDisplay) {
			String errMsgContent = driver.findElement(By.xpath("//p[contains(@class,'content-text')]")).getText();
			
			if (errMsgContent.contentEquals("Invalid credentials")) {
				System.out.println("PASSED : System successfully displayed valid error message as following when the user entered both wrong username and pasword: " + errMsgContent);
			}
			
			else {
				System.out.println("FAILED: System displayed error message but the message is invalid when the user entered both wrong username and pasword");
			}
		}
		
		else
			System.out.println("FAILED: System Does not display the error message when the user entered both wrong username and pasword!!!!");
		
		driver.quit();
		Thread.sleep(2000);
	}
	
	public static void correctUNWrongPWD() throws InterruptedException {
		
		Thread.sleep(1000);
		userLoginCredentials("Admin", "user123");
		
		WebElement errMsg = driver.findElement(By.xpath("//div[contains(@class,'oxd-alert--error')]"));
		boolean errDisplay = errMsg.isDisplayed();
		
		if(errDisplay) {
			String errMsgContent = driver.findElement(By.xpath("//p[contains(@class,'content-text')]")).getText();
			
			if (errMsgContent.contentEquals("Invalid credentials")) {
				System.out.println("PASSED : System successfully displayed valid error message as following when the user entered Correct username and Wrong password : " + errMsgContent);
			}
			
			else {
				System.out.println("FAILED: System displayed error message but the message is invalid when the user entered Correct username and Wrong password ");
			}
		}
		
		else
			System.out.println("FAILED: System Does not display the error message when the user entered Correct username and Wrong password !!!!");
		
		driver.quit();
		Thread.sleep(2000);
	}
	

	
	public static void wrongUNcorrectPWD() throws InterruptedException {
		
		userLoginCredentials("Admin", "user123");
				
		WebElement errMsg = driver.findElement(By.xpath("//div[contains(@class,'oxd-alert--error')]"));
		boolean errDisplay = errMsg.isDisplayed();
				
		if(errDisplay) {
			String errMsgContent = driver.findElement(By.xpath("//p[contains(@class,'content-text')]")).getText();
			
			if (errMsgContent.contentEquals("Invalid credentials")) {
				System.out.println("PASSED : System successfully displayed valid error message as following when the user entered Wrong username and Correct password : " + errMsgContent);
			}
			
			else {
				System.out.println("FAILED: System displayed error message but the message is invalid  when the user entered Wrong username and Correct password ");
			}
		}
		
		else
			System.out.println("FAILED: System Does not display the error message when the user entered Wrong username and Correct password !!!!");
		
		driver.quit();
		Thread.sleep(2000);
	}
	
	public static void correctUNcorrectPWD() throws InterruptedException {
		
		userLoginCredentials("Admin", "admin123");
		
		String expectedTitle = "OrangeHRM";
		String title = driver.getTitle();
		
	
		if (title.equals(expectedTitle)) {
			System.out.println("PASSED : In the Dashboard page, System successfully displays the title as " + title + " when the user logged in with the valid credentials");
		} else {
			System.out.println("FAILED: System displays invalid title as " + title + " instead of " + expectedTitle + " when the user logged in with the valid credentials");
		}
		
		WebElement user = driver.findElement(By.xpath("//span[@class= 'oxd-userdropdown-tab']"));
		user.click();
		WebElement logoutUser = user.findElement(By.xpath("//a[text()='Logout']"));
		logoutUser.click();
		
		String url = driver.getCurrentUrl();
		String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		url = driver.getCurrentUrl();
		
		if (url.equals(expectedURL)) {
			System.out.println("User successfully logged out from the system!!!");
		} else {
			System.out.println("User does not successfully logged out from the system!!!");
		}
		
		driver.quit();
		Thread.sleep(2000);
	}
	
	public static void userLoginCredentials(String un, String pwd) throws InterruptedException {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(un);
		
		WebElement password = driver.findElement(By.xpath("//input[@type = 'password']"));
		password.sendKeys(pwd);
		
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();
	}
}
