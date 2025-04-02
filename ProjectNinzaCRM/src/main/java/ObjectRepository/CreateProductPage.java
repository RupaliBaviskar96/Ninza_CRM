package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage 
{
	WebDriver driver;
	public CreateProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@name='productName']")
	private WebElement productname;
	@FindBy(xpath="//input[@name='quantity']")
	
	private WebElement quantity1;
	public WebDriver getDriver() {
		return driver;
	}


	public WebElement getProductname() {
		return productname;
	}


	public WebElement getQuantity() {
		return quantity1;
	}


	public WebElement getPrice() {
		return price1;
	}


	

	public WebElement getAddProductBtn() {
		return AddProductBtn;
	}

	@FindBy(xpath="//input[@name='price']")
	private WebElement price1;
	@FindBy(xpath="//button[text()='Add']")
	private WebElement AddProductBtn;
	@FindBy(xpath="//select[@name='productCategory']")
	private WebElement productCtgy;
	public WebElement getProductCtgy() {
		return productCtgy;
	}


	public WebElement getVendorname() {
		return vendorname;
	}

	@FindBy(xpath="//select[@name='vendorId']")
	private WebElement vendorname;
	public void addProduct(String prodName, String quantity, String price,String category,String vendor)
	{
		productname.sendKeys(prodName);
		quantity1.sendKeys(quantity);
		price1.sendKeys(price);
		productCtgy.sendKeys(category);
		vendorname.sendKeys(vendor);
		AddProductBtn.click();
	}
}
