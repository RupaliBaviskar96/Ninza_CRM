package ContactTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.CampaignsPage;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateCampaignsPage;
import ObjectRepository.CreateContactPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;

public class CreateContactWithTESTNG 
{
	
	@Test
	public void addContactWithCampaign()throws IOException, InterruptedException {

		PropertiesFileUtility propUtil=new PropertiesFileUtility();

		String BROWSER = propUtil.readingDataFromPropFile("browser");

		String URL = propUtil.readingDataFromPropFile("url");

		String UN = propUtil.readingDataFromPropFile("username");

		String PWD = propUtil.readingDataFromPropFile("password");

		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);

		JavaUtility jUtil=new JavaUtility();

		int randomNum = jUtil.getRandomNum(2000);

		

		ExcelFileUtility exUtil=new ExcelFileUtility();

		String Campaign = exUtil.readinDataFromExcelFile("DDT", 1, 2)+randomNum;

		String targetSize = exUtil.readinDataFromExcelFile("DDT", 1, 3);

		String organization = exUtil.readinDataFromExcelFile("Contact", 1, 2)+randomNum;

		String title = exUtil.readinDataFromExcelFile("Contact", 1, 3);

		String contactName = exUtil.readinDataFromExcelFile("Contact", 1, 4)+randomNum;

		String mobile = exUtil.readinDataFromExcelFile("Contact", 1, 5);

        

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

		driver.get(URL);

		LoginPage lp=new LoginPage(driver);

		lp.login(UN, PWD);

		

		DashboardPage dp=new DashboardPage(driver);

		Thread.sleep(2000);

		dp.getCampaignsLink().click();

        CampaignsPage cp=new CampaignsPage(driver);

		cp.getCreateCampaignBtn().click();

				

		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);

		ccp.createCampaignWithmandatoryFields(Campaign, targetSize);

		Thread.sleep(10000);

		

		WebElement contactLink = dp.getContactsLink();

		WebDriverUtility Wutil=new WebDriverUtility();

		Wutil.waitForElementToBeClickable(driver, contactLink,20);

		contactLink.click();

		

		Thread.sleep(5000);

		ContactsPage ccp1=new ContactsPage(driver);

		WebElement createContactBtn = ccp1.getCreateContactBtn();

		Wutil.waitForElementToBeClickable(driver, createContactBtn,20);

	    createContactBtn.click();

	    Thread.sleep(5000);
		

		CreateContactPage cct=new CreateContactPage(driver);
		Thread.sleep(10000);
		cct.createContactWithCampaign(organization, title, contactName, mobile, "selectCampaign", "create-contact", Campaign);
		Thread.sleep(5000);
        String ConfMsg = ccp1.getConfMsg().getText();
        
        boolean status = ConfMsg.contains(contactName);
        Assert.assertTrue(status);
        Reporter.log("Campaign "+Campaign+" "+contactName+" added successfully",true);
        System.out.println(" "+contactName+" added successfully");
        Thread.sleep(5000);
   /*     if(ConfirmationMsg.contains(contactName))

        {

        	Reporter.log("Contact added Successfully");

        }

        else

        {

        	Reporter.log("Contact not added");

        }
*/
       Thread.sleep(10000);

       dp.logout();
       Thread.sleep(2000);
       driver.quit();
       Thread.sleep(2000);
	}

}
