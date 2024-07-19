package quebecBonds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuebecHomePage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//a[@title = 'English']") WebElement engLang;
	@FindBy(xpath = "//a[contains(@title,'Fran')]") WebElement frenchLang;
	@FindBy(xpath = "//table[contains(@summary,'Bonds')]") WebElement bondsTable;
	
	//@FindBy(name = "password") WebElement txtPassword;
	By txtPassword = By.name("password");
	
	@FindBy(name = "submit") WebElement btnSubmit;
	
	QuebecHomePage(WebDriver rdriver){
		driver = rdriver;
		PageFactory.initElements(driver, this);
	}

	public void selectLanguage(String lang) {
		if (lang.equalsIgnoreCase("English")) {
			if (engLang.isEnabled()) {
				engLang.click();
			}
		}
		
		else if (lang.equalsIgnoreCase("French")) {
			if (frenchLang.isEnabled()) {
				frenchLang.click();
			}
		}
	}

	
}
