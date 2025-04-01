package GenericUtility;

import java.time.Duration;
import java.util.Set;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverUtility 
{
	public void waitForUtility(WebDriver driver,WebElement element)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public void waitForElementToBeClickable(WebDriver driver,WebElement element,long sec)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	public void switchToWindow(WebDriver driver,String partialUrl)
	{
		Set<String> allWindowId= driver.getWindowHandles();
		for(String window:allWindowId)
		{
			driver.switchTo().window(window);
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains(partialUrl))
			{
				break;
			}
		}
	}
	public void switchToFrame(WebDriver driver , int index)
	
	{
		driver.switchTo().frame(index);
		
	}
	public void switchToFrame(WebDriver driver ,WebElement element)
	
	{
		driver.switchTo().frame(element);
		
	}
	public void switchToFrame(WebDriver driver , String value)

	{
		driver.switchTo().frame(value);
	
	}
	public void select (WebElement element ,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
		
	}
	public void select (WebElement element ,String value)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
		
	}
	public void select(String visibilitytext,WebElement element)
	{
		Select sel=new Select(element);
		sel.deselectByVisibleText(visibilitytext);
	}
	public void moveToElement(WebDriver driver, WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).click().perform();
		
	}
	public void actionDoubleClick(WebDriver driver,WebElement element)
	{
		Actions action =new Actions(driver);
		action.doubleClick(element);
		
	}
	public void actionRightClick(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	public void takingScreenshot(WebDriver driver,String screnshotname) throws IOException, InterruptedException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(".//Errshots//"+screnshotname+".jpeg");
		try
		{
		    System.out.println("Screenshot is taken");
			FileHandler.copy(temp,dest );
			Thread.sleep(3000);
			
			
			}
			catch(IOException e)
			{
				e.printStackTrace(); 
			}
	}
	
	//click
	//contextclick
	//moveToElement
	//dropdown
	
}
