package Basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateCampaignUsingExcelDDT {

	public static void main(String[] args) throws InterruptedException,IOException,FileNotFoundException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\ProjectNinzaCRM\\src\\test\\resources\\NinzaCrm.properties");

		Properties prop=new Properties();

		prop.load(fis);

		String BROWSER = prop.getProperty("browser");

		String URL = prop.getProperty("url");

		String UN = prop.getProperty("username");

		String PWD = prop.getProperty("password");

		System.out.println(BROWSER);

		System.out.println(URL);

		System.out.println(UN);

		System.out.println(PWD);

		String expectedURL="http://49.249.28.218:8098/dashboard";

        Random r=new Random();
        int randomNum= r.nextInt(100);

        FileInputStream fis1=new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\ProjectNinzaCRM\\src\\test\\resources\\TestNinzaCrm.xlsx");

        Workbook wb =WorkbookFactory.create(fis1);
		String Campaign = wb.getSheet("DDT").getRow(1).getCell(2).getStringCellValue()+randomNum;
		
		String targetSize = wb.getSheet("DDT").getRow(1).getCell(3).getStringCellValue();
		
		System.out.println(Campaign);
		
		System.out.println(targetSize);
		
		Date dateObj=new Date();
		
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-YYYY");
		
		String todaysDate = sim.format(dateObj);
		
		System.out.println(todaysDate);
		
		
		
		Calendar cal = sim.getCalendar();
		
		cal.add(Calendar.DAY_OF_MONTH, 30);
		
		String closeDate = sim.format(cal.getTime());
		
		System.out.println(closeDate);
		
		
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
		
		driver.findElement(By.linkText("Campaigns")).click();
		
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		
		driver.findElement(By.name("targetSize")).clear();
		
		driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		
		driver.findElement(By.name("expectedCloseDate")).sendKeys(closeDate);
		
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		Thread.sleep(2000);
		
		String ConfMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		
		if(ConfMsg.contains(Campaign))
		
		{
		
			System.out.println("campaign added successfully");
		
		}
		
		else
		
		{
		
			System.out.println("campaign not added");
		
		}
		
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		
		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		
		Actions action=new Actions(driver);
		
		action.moveToElement(logoutBtn).click().perform();
		
		//close the browser
		
		driver.quit();
    }

}
