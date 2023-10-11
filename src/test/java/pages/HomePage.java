package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class HomePage extends BaseClass {
	
	@FindBy(xpath="//a[text()='Contact']")
	WebElement link_Contact;
	
	@FindBy(xpath="//a[text()='Shop']")
	WebElement link_Shop;
	
	@FindBy(xpath="//a[contains(@href,'cart')]")
	WebElement link_Cart;
	
	public HomePage(WebDriver driver)
	{
	super(driver);
	}
	
	public String getHomePageTitle()
	{
	String title=getPageTitle();
	return title;
	}
	
	public void clickContactLink()
	{
		clickElement(link_Contact);
	}
	
	public void clickShopLink()
	{
		clickElement(link_Shop);
	}
	
	public void clickCartLink()
	{
		clickElement(link_Cart);
	}
}
