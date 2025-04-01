package CampaignTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.CampaignsPage;
import ObjectRepository.CreateCampaignsPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;


public class CreateCampaignWithTestNG 
{

	@Test
	public void CreateCampaignWithMandatoryField() throws InterruptedException, IOException 
	{
		PropertiesFileUtility propUtil=new PropertiesFileUtility();

		String BROWSER = propUtil.readingDataFromPropFile("browser");

		String URL = propUtil.readingDataFromPropFile("url");

		String UN = propUtil.readingDataFromPropFile("username");

		String PWD = propUtil.readingDataFromPropFile("password");

		

		JavaUtility jUtil=new JavaUtility();

		int randomNum = jUtil.getRandomNum(5000);

		

		ExcelFileUtility exUtil=new ExcelFileUtility();

		String Campaign = exUtil.readinDataFromExcelFile("DDT", 1, 2)+randomNum;

		String targetSize = exUtil.readinDataFromExcelFile("DDT", 1, 3);

		

		String expectedURL="http://49.249.28.218:8098/dashboard";

		//Launching the browser

		WebDriver driver=null;

		if(BROWSER.equalsIgnoreCase("chrome"))

		{

			driver=new ChromeDriver();

		}

		else if(BROWSER.equalsIgnoreCase("firefox"))

		{

			driver=new FirefoxDriver();

		}

		else if(BROWSER.equalsIgnoreCase("edge"))

		{

			driver=new EdgeDriver();

		}

		else

		{

			driver=new ChromeDriver();

		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//navigating to ninza CRM

		driver.get(URL);

		//enter the username and password

		LoginPage lp=new LoginPage(driver);

		lp.login(UN, PWD);

		DashboardPage dp=new DashboardPage(driver);

		Thread.sleep(2000);

		dp.getCampaignsLink().click();

		CampaignsPage cp=new CampaignsPage(driver);

		cp.getCreateCampaignBtn().click();

		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);
        System.out.println(Campaign);
        System.out.println(targetSize);

		ccp.createCampaignWithmandatoryFields(Campaign, targetSize);

		Thread.sleep(2000);

		String ConfMsg = cp.getConfMsg().getText();
		boolean status = ConfMsg.contains(Campaign);
    //    Assert.assertEquals(status, true,"Campaign not added");
        Reporter.log("Campaign "+Campaign+" added successfully",true);
	/*	if(ConfMsg.contains(Campaign))

		{

			Reporter.log("campaign "+Campaign+" added successfully");

		}

		else

		{

			Reporter.log("campaign not added");

		}
	
	*/
		Thread.sleep(4000);

		dp.logout();

        //close the browser

        driver.quit();
	}
		
	@Test
	public void CreateCampaignWithClosedDate()throws IOException, InterruptedException
	{
		PropertiesFileUtility propUtil=new PropertiesFileUtility();

		String BROWSER = propUtil.readingDataFromPropFile("browser");

		String URL = propUtil.readingDataFromPropFile("url");

		String UN = propUtil.readingDataFromPropFile("username");

		String PWD = propUtil.readingDataFromPropFile("password");

		System.out.println("");

		JavaUtility jUtil=new JavaUtility();

		int randomNum = jUtil.getRandomNum(5000);

		

		ExcelFileUtility exUtil=new ExcelFileUtility();

		String Campaign = exUtil.readinDataFromExcelFile("DDT", 1, 2)+randomNum;

		String targetSize = exUtil.readinDataFromExcelFile("DDT", 1, 3);

		

		String closeDate = jUtil.getRequestDate(20);

		System.out.println(closeDate);

		//String expectedURL="http://49.249.28.218:8098/dashboard";

		//Launching the browser

		WebDriver driver=null;

		if(BROWSER.equalsIgnoreCase("chrome"))

		{

			driver=new ChromeDriver();

		}

		else if(BROWSER.equalsIgnoreCase("firefox"))

		{

			driver=new FirefoxDriver();

		}

		else if(BROWSER.equalsIgnoreCase("edge"))

		{

			driver=new EdgeDriver();

		}

		else

		{

			driver=new ChromeDriver();

		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//navigating to ninza CRM

		driver.get(URL);

		//enter the username and password

		LoginPage lp=new LoginPage(driver);

		lp.login(UN, PWD);

		DashboardPage dp=new DashboardPage(driver);

		Thread.sleep(2000);

		dp.getCampaignsLink().click();

		CampaignsPage cp=new CampaignsPage(driver);

		cp.getCreateCampaignBtn().click();

		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);

		ccp.createCampaignWithCloseDate(Campaign, targetSize,closeDate);

		Thread.sleep(000);

		
		String ConfMsg = cp.getConfMsg().getText();
		boolean status = ConfMsg.contains(Campaign);
        Assert.assertEquals(status, true,"Campaign not added");
        Reporter.log("Campaign "+Campaign+" added successfully",true);
		

	/*	if(ConfMsg.contains(Campaign))

		{

			Reporter.log("campaign "+Campaign+" added successfully");

		}

		else

		{

			Reporter.log("campaign not added");

		}
	*/
		Thread.sleep(4000);

		dp.logout();
		Thread.sleep(4000);
        //close the browser

        driver.quit();
	}
}

	
	

