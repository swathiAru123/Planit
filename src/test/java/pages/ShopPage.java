package pages;

import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.BaseClass;

public class ShopPage extends BaseClass{

	@FindBy(xpath="//a[contains(text(),'Start Shopping')]")
	WebElement btn_StartShopping;

	@FindBy(xpath="//div[contains(@class,'products')]")
	WebElement productsDiv;

	@FindBy(xpath="//li[contains(@id,product)]/div/h4")
	List<WebElement> allProducts;

	@FindBy(xpath = "//li[contains(@id, 'product')]/div/h4/../p/a")
	List<WebElement> buyButtons;

	@FindBy(xpath = "//li[contains(@id, 'product')]/div/h4/../p/span")
	List<WebElement> productsPrice;
		
	public ShopPage(WebDriver driver) {
		super(driver);
	}

	public boolean verifyShoppingProductsAreDisplayed()
	{
		boolean val=isElementDisplayed(productsDiv);
		return val;
	}

	public double addProductsToCartAndGetCartSubtotal(String reqProduct,int quantity)
	{
		double totalSubtotal = 0.0;	
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		double productSubtotal = 0.0;
		for (int j=0;j<allProducts.size();j++) {
			if(allProducts.get(j).getText().equals(reqProduct))
			{				
				WebElement productPrice = productsPrice.get(j);
				String productPriceText = productPrice.getText();
				double actualproductPrice = Double.parseDouble(productPriceText.replace("$", "").trim());
				for (int i = 0; i < quantity; i++) {
					WebElement buyButton = buyButtons.get(j);
					buyButton.click();
					double totalPrice = actualproductPrice * (i + 1);
					productSubtotal=totalPrice;
					System.out.println("Total price for " + (i + 1) + " " + reqProduct + " is: $" + totalPrice);
				}				
				totalSubtotal =totalSubtotal+ productSubtotal;
				System.out.println("Total subtotal for all products is: $" + decimalFormat.format(totalSubtotal));					
			}			
		}
		return totalSubtotal;
	}	
}
