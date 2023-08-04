package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;      //for log manager
import org.apache.logging.log4j.Logger;        //for logging


public class BaseClass {

	public static WebDriver driver;
	
	public Logger logger;             //for logging\
	
	public ResourceBundle rb;
	
	@BeforeClass(groups = {"Master","Sanity","Regression"})
	@Parameters("browser")                      //Parameters using for testNG.xml file
	public void setup(String br) 
	{
		rb=ResourceBundle.getBundle("config");         //load config.properties file
		
		logger=LogManager.getLogger(this.getClass());
		
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});                    //chrome open zalaya nantr varti message yeto na chrom to remove krnyasathi
		
		FirefoxOptions options2=new FirefoxOptions();
		
		EdgeOptions options3=new EdgeOptions();
		
		WebDriverManager.chromedriver().setup();                            //for java selenium latest versions need not WebDriverManager and chromeDriverOptions
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.edgedriver().setup();
		
		
		if(br.equals("chrome"))
		{
		driver=new ChromeDriver(options);
		}
		else if(br.equals("firefox"))
		{
			driver=new FirefoxDriver(options2);
		}
		else
		{
			driver=new EdgeDriver(options3);
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = {"Master","Sanity","Regression"})
    public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString() {
	      String generatedString = RandomStringUtils.randomAlphabetic(5);           //for generating 5 letters word randomly
	      return (generatedString);
	}
	
	public String randomNumber() {
	      String generatedString2 = RandomStringUtils.randomNumeric(10);            //for generating 10 numbers contact randomly
	      return (generatedString2);
	}
	
	public String randomAlphaNumeric() {
		
	      String st = RandomStringUtils.randomAlphabetic(4);            
	      String num =RandomStringUtils.randomNumeric(3);                      
	      
	      return (st+"@"+num);                                                         //for generate alphanumeric string, 5 alphabets and 5 number 
	}
	
	public String captureScreen(String tname) throws IOException {

//		SimpleDateFormat df =new SimpleDateFormat("yyyyMMddhhmmss");
//		Date dt=new Date();
//		String timestamp = df.format(dt);
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());         //  Above the Objects created in 3 Lines are in common single line
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
	}
}
