package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void verify_Account_Regstration() {

		logger.info("***** Starting TC001_AccountRegistrationTest ***");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("***** Clicked on My Account link ***");
			hp.clickRegister();
			logger.info("***** Clicked on Register link ***");

			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

			logger.info("***** Providing customer details ***");
			regPage.setFirstName(randomString().toUpperCase());
			regPage.setLastName(randomString().toUpperCase());
			regPage.setEmail(randomString() + "@gmail.com");
			regPage.setTelephone(randomNumeric());

			String password = randomAlphaNumeric();

			regPage.setPassword(password);
			regPage.setConfirmPassword(password);
			regPage.setPrivacyPolicy();
			regPage.clickContinue();

			logger.info("***** Validating expected message ***");
			String cnfmsg = regPage.getConfirmationMsg();
			Assert.assertEquals(cnfmsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("test failed");
			logger.debug("debug logs");
			Assert.fail();
		}
		logger.info("***** Finished TC001_AccountRegistrationTest ***");
	}

}
