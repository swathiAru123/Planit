package utilities;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	WebDriver driver;

	public BaseClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	

	public void clickElement(WebElement element) {
		element.click();
	}

	public void enterText(WebElement element, String keys) {
		element.clear();
		element.sendKeys(keys);
	}

	public String getText(WebElement element)
	{
		String text=element.getText();
		return text;
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} 
		catch (NoSuchElementException e) {		
			return false;
		}
	}

	public WebElement waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}	

	public List<String> getErrorMessages(List<WebElement> errors)
	{		
		List<String> errorMessages = new ArrayList<>();
		for (WebElement error : errors) {
			if (error.isDisplayed()) {
				String actualErrorMessage = error.getText().trim();
				errorMessages.add(actualErrorMessage);
			}
		}    
		return errorMessages;
	}
}