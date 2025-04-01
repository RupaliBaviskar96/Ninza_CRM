package Login;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtility.PropertiesFileUtility;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;

public class LoginWithTESTNG
{
	//@Parameters("browser")

		@Test(groups= {"SmokeTest"})

		public void loginTest() throws InterruptedException, IOException {

			PropertiesFileUtility propUtil=new PropertiesFileUtility();

			String BROWSER = propUtil.readingDataFromPropFile("browser");

			String URL = propUtil.readingDataFromPropFile("url");

			String UN = propUtil.readingDataFromPropFile("username");

			String PWD = propUtil.readingDataFromPropFile("password");

			

			String expectedURL="http://49.249.28.218:8098/dashboard";

			//Launching the browser

			WebDriver driver=null;

			ChromeOptions Coption=new ChromeOptions();

			FirefoxOptions Foption=new FirefoxOptions();

			EdgeOptions Eoption=new EdgeOptions();

			Coption.addArguments("--headless");

			Foption.addArguments("--headless");

			Eoption.addArguments("--headless");

			if(BROWSER.equalsIgnoreCase("chrome"))

			{

				driver=new ChromeDriver(Coption);

			}

			else if(BROWSER.equalsIgnoreCase("firefox"))

			{

				driver=new FirefoxDriver(Foption);

			}

			else if(BROWSER.equalsIgnoreCase("edge"))

			{

				driver=new EdgeDriver(Eoption);

			}

			else

			{

				driver=new ChromeDriver(Coption);

			}

			driver.manage().window().maximize();

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			//navigating to ninza CRM

			driver.get(URL);

			//enter the username and password

			LoginPage lp=new LoginPage(driver);

			lp.login(UN, PWD);

			Thread.sleep(2000);

			//verification of dashboard

			String actualURL=driver.getCurrentUrl();

			Assert.assertEquals(actualURL, expectedURL,"Validation is failed");

			Reporter.log("Validation is passed",true);

			//logout

			DashboardPage dp=new DashboardPage(driver);

			dp.logout();

	        //close the browser

	        driver.quit();

		}



}


//Assert.assertEquals(actualURL, expectedURL,"Validation is failed");

//Reporter.log("Validation is passed",true);
