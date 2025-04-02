package ProductTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Reporter;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.CreateProductPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;
import ObjectRepository.ProductPage;

public class ProductTest 
{
	public static void main(String []args) throws IOException, InterruptedException {

		PropertiesFileUtility propUtil=new PropertiesFileUtility();

		String BROWSER = propUtil.readingDataFromPropFile("browser");

		//String BROWSER=browser;

		String URL = propUtil.readingDataFromPropFile("url");

		String UN = propUtil.readingDataFromPropFile("username");

		String PWD = propUtil.readingDataFromPropFile("password");
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);


		JavaUtility jutil=new JavaUtility();

		int randNum = jutil.getRandomNum(2000);
		System.out.println(randNum);

		ExcelFileUtility excelUtil=new ExcelFileUtility();

		String prodName = excelUtil.readinDataFromExcelFile("Product", 1, 1)+randNum;

		String quantity = excelUtil.readinDataFromExcelFile("Product", 1, 2);

		String price = excelUtil.readinDataFromExcelFile("Product", 1, 3);
		System.out.println(prodName);
		System.out.println(quantity);
		System.out.println(price);
		WebDriver driver=null;

		ChromeOptions Coption=new ChromeOptions();

		FirefoxOptions Foption=new FirefoxOptions();

		EdgeOptions Eoption=new EdgeOptions();


		
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
		System.out.println(URL+1);
		
		Thread.sleep(2000); 
			//enter the username and password

		LoginPage lp=new LoginPage(driver);

		lp.login(UN, PWD);

		Thread.sleep(5000);

			//verification of dashboard

		DashboardPage dp=new DashboardPage(driver);
		System.out.println(dp.getProductsLink().getText());

		dp.getProductsLink().click();
		
		ProductPage pp=new ProductPage(driver);

		pp.getAddProductBtn().click();
		Thread.sleep(5000);


		CreateProductPage cpp=new CreateProductPage(driver);

		cpp.addProduct(prodName, quantity, price,"Electricals","VID_001");

		Thread.sleep(10000);

		String confMsg = pp.getConfMsg().getText();
		Thread.sleep(5000);
		if(confMsg.contains(prodName))

		{

			Reporter.log("Product "+prodName+"added successfully",true);

		}

		else

		{

			Reporter.log("Product not added",true);

		}

		Thread.sleep(5000);

		//logout

		dp.logout();

        //close the browser

        driver.quit();


	}

}
