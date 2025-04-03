package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage
{
	WebDriver driver;
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="/html/body/div[2]/div/div/div/div[1]")
	private WebElement confmsg;
	
	@FindBy(xpath="//span[text()='Add Product']")
    private WebElement AddProductBtn;
	
	public WebElement getAddProductBtn() {
		return AddProductBtn;
	}
	
	public WebElement getConfMsg() 
	{
	 // TODO Auto-generated method stub
		return confmsg;
	}
}
