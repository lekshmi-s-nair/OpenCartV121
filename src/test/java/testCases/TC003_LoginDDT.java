package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven") // getting dp from
																										// diff class
	public void verify_loginDDT(String email, String pwd, String expected) {

		logger.info("***starting TC003_LoginDDT****");
		// HomePage
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			// MyAccountPage

			MyAccountPage map = new MyAccountPage(driver);
			boolean targetPage = map.myAccountExistenceCheck();

			/*
			 * Data is valid - login success - test pass- logout login failed - test fail
			 * 
			 * 
			 * Data is invalid- login success- test fail- logout login failed- test pass
			 */

			// valid login-pass
			if (expected.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					map.ClkLogout();
					Assert.assertTrue(true);

				} else {
					Assert.assertTrue(false);
				}
			}
			// invalid login- fail
			if (expected.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					map.ClkLogout();
					Assert.assertTrue(false);

				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("***** Finished TC003_LoginDDT ******");
	}
}
