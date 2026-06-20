package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends BasePage {
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement MyAccountHeading;

	@FindBy(linkText = "Logout")
	WebElement BtnLogout;

	public void ClkLogout() {
		BtnLogout.click();
	}

	public boolean myAccountExistenceCheck() {
		try {
			return MyAccountHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}