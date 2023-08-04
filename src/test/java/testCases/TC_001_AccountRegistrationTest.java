package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups = {"Regression","Master"})
    public void test_account_Registration()
	{
	 
		//Here you can use multiple log levels All < Trace < Debug < Info < Warn < Error < Fatal < Off 
		logger.debug("Application logs...................");
		logger.info("**** Starting TC_001_AccountRegistrationTest ****");
		
		try {
		HomePage hp =new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		
		hp.clickRegister();	
		logger.info("Clicked on register link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer data");
		regpage.setFirstName(randomString().toUpperCase());
		
		regpage.setLastName(randomString().toUpperCase());
		
		regpage.setEmail(randomString()+"@gmail.com");    //Randomly generated--> every time we want change something otherwise due to same data test case should be failed.
		
		regpage.setPhone(randomNumber());
		
		String password=randomNumber();
		
     	regpage.setPassword(password);         //Random Password
		
     	regpage.setCnfmPassword(password);
     	
		regpage.setPrivacyPlicy();
		
		regpage.clickContinue();
		logger.info("Clicked on continue");
		
     	String confmsg = regpage.getConfirmationMsg();
     	
     	logger.info("Validating expected message");
     	Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Test Failed");
	
	    }catch (Exception e)
        {
	    	logger.error("Test Failed");
		Assert.fail();
	    }	
		logger.info("**** Finished TC_001_AccountRegistrationTest ****");
   }
}
