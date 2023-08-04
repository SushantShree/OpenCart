package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class)
	public void test_loginDDT(String email,String password, String res)
	{
		logger.info("**** Starting TC_003_LoginDataDrivenTest ****");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		hp.clickLogin();
		logger.info("Clicked on Login Link");
		
		LoginPage lp=new LoginPage(driver);
		
		logger.info("Providing login details");
		lp.setEmail(email);                //valid email get it String value passes above from the excel
		lp.setPassword(password);          //valid password get it String value passes above from the excel
		lp.clickLogin();
		logger.info("Clicked on Login Button");
		
		//Positive and Negative test cases getting credential data from excel sheet 
		
		MyAccountPage macc=new MyAccountPage(driver);
	    boolean	targetpage=macc.isMyAccountPageExists();
		
	    if(res.equals("Valid"))
	    {
	    	if(targetpage==true)
	    	{
	    		macc.clickOnMyaccount();
	    		macc.clickOnLogout();
	    		Assert.assertTrue(true);
	    	}
	    	else
	    	{
	    		Assert.assertTrue(false);	
	    	}
		}
	    
	    if(res.equals("Invalid"))
	    {
	    	if(targetpage==true)
	    	{
	    		macc.clickOnMyaccount();
	    		macc.clickOnLogout();
	    		Assert.assertTrue(false);
	    	}
	    	else
	    	{
	    		Assert.assertTrue(true);	
	    	}	
        }
		}catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("**** Finished TC_003_LoginDataDrivenTest ****");
	}
}
   


