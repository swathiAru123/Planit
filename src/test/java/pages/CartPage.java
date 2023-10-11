package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.BaseClass;

public class CartPage extends BaseClass {

	@FindBy(xpath="//span[contains(@class,'cart-count')]")
	WebElement txt_cartTotalCount;
	
	@FindBy(xpath="//strong[contains(text(),'Total')]")
	WebElement txt_totalPrice;
	
	public CartPage(WebDriver driver) {
		super(driver);
	}

	public String getTotalNumOFItemsInCart()
	{
		String totalCartItems=getText(txt_cartTotalCount);
		return totalCartItems;
	}
	
	public String getTotalValueOfProductPrice()
	{
		String totalValue=getText(txt_totalPrice);
		String totalProductPrice = totalValue.replaceAll("Total: ", "");
		return totalProductPrice;
	}
}
