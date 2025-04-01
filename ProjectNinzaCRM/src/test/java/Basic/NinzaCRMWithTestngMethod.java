package Basic;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import GenericListenerUtility.ListenerImplementation;
import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import ObjectRepository.CampaignsPage;
import ObjectRepository.CreateCampaignsPage;
import ObjectRepository.CreateProductPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.ProductPage;
@Listeners(ListenerImplementation.class)
public class NinzaCRMWithTestngMethod extends BaseClass
{
	@Test(retryAnalyzer = GenericListenerUtility.RetryListenerImplem.class)

	public void createCampaignTest() throws InterruptedException, IOException {

		

		JavaUtility jUtil=new JavaUtility();

		int randomNum = jUtil.getRandomNum(10000);

		

		ExcelFileUtility exUtil=new ExcelFileUtility();

		String Campaign = exUtil.readinDataFromExcelFile("DDT", 1, 2)+randomNum;

		String targetSize = exUtil.readinDataFromExcelFile("DDT", 1, 3);

		

		String expectedURL="http://49.249.28.218:8098/dashboard";
		//Launching the browser

		

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		

		DashboardPage dp=new DashboardPage(driver);

		Thread.sleep(2000);

		dp.getCampaignsLink().click();

		CampaignsPage cp=new CampaignsPage(driver);

		cp.getCreateCampaignBtn().click();

		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);

		ccp.createCampaignWithmandatoryFields(Campaign, targetSize);

		Thread.sleep(5000);

		String ConfMsg = cp.getConfMsg().getText();

		boolean status = ConfMsg.contains(Campaign);

		Assert.assertEquals(status, true, "campaign not added");

		//Assert.assertTrue(status, "Campaign not added");

		Reporter.log("Campaign "+Campaign+" added successfully",true);

		Thread.sleep(5000);
    }

    @Test()

	public void createCampaignWithDateTest() throws InterruptedException, IOException

	{
        JavaUtility jUtil=new JavaUtility();

		int randomNum = jUtil.getRandomNum(10000);
		ExcelFileUtility exUtil=new ExcelFileUtility();

		String Campaign = exUtil.readinDataFromExcelFile("DDT", 1, 2)+randomNum;

		String targetSize = exUtil.readinDataFromExcelFile("DDT", 1, 3);

		

		String closeDate = jUtil.getRequestDate(30);

		System.out.println(closeDate);

		String expectedURL="http://49.249.28.218:8098/dashboard";

		

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		

		DashboardPage dp=new DashboardPage(driver);

		Thread.sleep(2000);

		dp.getCampaignsLink().click();

		CampaignsPage cp=new CampaignsPage(driver);

		cp.getCreateCampaignBtn().click();

		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);

		ccp.createCampaignWithCloseDate(Campaign, targetSize,closeDate);

		Thread.sleep(5000);

		String ConfMsg = cp.getConfMsg().getText();

		boolean status = ConfMsg.contains(Campaign);

		Assert.assertEquals(status, true, "Campaign not added");

		//Assert.assertTrue(status, "Campaign not added");

	    Reporter.log("campaign "+Campaign+" added successfully",true);

		Thread.sleep(5000);
	}
//	@Test
	public void createProductTest() throws IOException, InterruptedException {

		JavaUtility jutil=new JavaUtility();

		int randNum = jutil.getRandomNum(10000);

		ExcelFileUtility excelUtil=new ExcelFileUtility();

		String prodName = excelUtil.readinDataFromExcelFile("Product", 1, 1)+randNum;

		String quantity = excelUtil.readinDataFromExcelFile("Product", 1, 2);

		String price = excelUtil.readinDataFromExcelFile("Product", 1, 3);

		

	//	String expectedURL="http://49.249.28.218:8098/dashboard";

		

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//navigating to ninza CRM
		

		Thread.sleep(2000);

		//verification of dashboard

		DashboardPage dp=new DashboardPage(driver);

		dp.getProductsLink().click();

		ProductPage pp=new ProductPage(driver);

		pp.getAddProductBtn().click();

		CreateProductPage cpp=new CreateProductPage(driver);

		cpp.addProduct(prodName, quantity, price);

		Thread.sleep(5000);

		String confMsg = pp.getConfMsg().getText();

		boolean status = confMsg.contains(prodName);

		Assert.assertTrue(status);

		Thread.sleep(5000);

		//logout

		

	}


}
