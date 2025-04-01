package Login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import GenericUtility.ExcelFileUtility;
import GenericUtility.FileUtility;
import GenericUtility.JavaUtility;

public class LoginTest 
{
	public static void main(String[] args) throws InterruptedException,IOException,FileNotFoundException
	{
		FileUtility FUtil= new FileUtility();

		String BROWSER = FUtil.readingDataFromPropFile("browser");

		String URL = FUtil.readingDataFromPropFile("url");

		String UN = FUtil.readingDataFromPropFile("username");

		String PWD = FUtil.readingDataFromPropFile("password");

		System.out.println(BROWSER);

		System.out.println(URL);

		System.out.println(UN);

		System.out.println(PWD);

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
		
		driver.findElement(By.id("username")).sendKeys(UN);
		
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		
		//click on sign in button
		
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		Thread.sleep(2000);
		
		String actualurl = "http://49.249.28.218:8098/dashboard";
	
		Assert.assertEquals(actualurl,expectedURL,"Validation is failed");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		
		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		
		Actions action=new Actions(driver);
		
		action.moveToElement(logoutBtn).click().perform();
		
		//close the browser
		
		driver.quit();
    }

		

}
