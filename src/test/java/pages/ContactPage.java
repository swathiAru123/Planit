package pages;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.BaseClass;

public class ContactPage extends BaseClass{

	@FindBy(name="forename")
	WebElement txtBx_Forename;

	@FindBy(name="email")
	WebElement txtBx_Email;

	@FindBy(name="message")
	WebElement txtBx_Msg;

	@FindBy(xpath="//a[text()='Submit']")
	WebElement btn_Submit;

	@FindBy(xpath="//div[contains(@class,'success')]")
	WebElement feedbackMsg_Success;
	
	@FindBy(xpath="//span[contains(@id,'err')]")
	List<WebElement> errorElements;


	public ContactPage(WebDriver driver) {
		super(driver);
	}

	public void enterMandatoryFeedBackDetails(String foreName,String email,String message)
	{
		enterText(txtBx_Email, email);
		enterText(txtBx_Forename, foreName);
		enterText(txtBx_Msg, message);
	}

	public void clickSubmitBtn()
	{
		btn_Submit.click();
	}

	public List<String> getErrorMessages()
	{	
		List<String> actualErrorMessages = getErrorMessages(errorElements);
		return actualErrorMessages;
	}
	
	public String verifyFeedbackSuccessMsgIsDisplayed()
	{	
		String actualText="";
		waitForElementToBeVisible(feedbackMsg_Success,200);
		boolean isElementDisplayed=feedbackMsg_Success.isDisplayed();
		if(isElementDisplayed==true)
		{
		 actualText=getText(feedbackMsg_Success);
		}
		return actualText;
	}
}
