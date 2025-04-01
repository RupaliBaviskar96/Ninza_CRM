package Basic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginTest
{

	public static void main(String[] args) throws InterruptedException
	{

		String expectedURL="http://49.249.28.218:8098/dashboard";

		//Launching the browser

		WebDriver driver=new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//navigating to ninza CRM

		driver.get("http://49.249.28.218:8098/");

		//enter the username and password

		driver.findElement(By.id("username")).sendKeys("rmgyantra");

		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");

		//click on sign in button

		WebElement signin= driver.findElement(By.xpath("//button[text()='Sign In']"));
		signin.click();

		Thread.sleep(2000);

		//verification of dashboard

		String actualURL=driver.getCurrentUrl();

		if(actualURL.equals(expectedURL))

		{

			System.out.println("Validation is pass");

		}

		else

		{

			System.out.println("validation is failed");

		}

		//logout

		driver.findElement(By.xpath("//div[@class='user-icon']")).click();

        WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));

        Actions action=new Actions(driver);

        action.moveToElement(logoutBtn).click().perform();

        //close the browser

        driver.quit();

	}
}

