package StepDefinitions;
import static org.junit.Assert.assertTrue;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ShopPage;
import pages.CartPage;
import pages.ContactPage;
import pages.HomePage;

public class StepDefinition{

	WebDriver driver;
	HomePage homePage;
	ContactPage contactPage;
	ShopPage shopPage;
	CartPage cartPage;
	int totalQuantity;
	double totalSubtotal;

	@Before
	public void browserSetup()
	{
		System.getProperty("webdriver.chrome.driver", "/JupiterToysPlanit/src/test/resources/drivers/chromedriver.exe");
		driver=new ChromeDriver();	
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		homePage= new HomePage(driver);
		contactPage=new ContactPage(driver);
		shopPage=new ShopPage(driver);
		cartPage=new CartPage(driver);

	} 

	@Given("the user is on Home page")
	public void UserIsOnHomePage() {
		driver.get("http://jupiter.cloud.planittesting.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@And("^verify home page title is displayed (.*)$")
	public void verifyHomePageIsDisplayed(String expTitle) {
		String actualTitle=homePage.getHomePageTitle();
		assertTrue("HomePage is not displaying", expTitle.equals(actualTitle));
	}

	@When("user clicks Submit button without entering mandatory field")
	public void clickSubmitButton() {
		contactPage.clickSubmitBtn();
	}

	@Then("click Contact link")
	public void ClickContactLink() {
		homePage.clickContactLink();
	}

	@When("user enters mandatory feedback information (.*) (.*) (.*)$")
	public void enterFeedBackInformation(String name, String email, String message) {
		contactPage.enterMandatoryFeedBackDetails(name, email, message);			
	}

	@Then("the error messages should not display")
	public void errorMessageShouldNotDisplayOnEnteringMandatoryContactInfromation() {
		List<String> errorMessages=contactPage.getErrorMessages();
		assertTrue("Error messages should not be displayed after populating mandatory fields", errorMessages.isEmpty());
	}

	@Then("the following error messages should be displayed")
	public void theFollowingErrorMessagesShouldBeDisplayed(List<String> expectedErrorMessages) {
		List<String> actualErrorMessages=contactPage.getErrorMessages();
		Assert.assertEquals(expectedErrorMessages, actualErrorMessages);
	}

	@Then("verify success Message is displayed on submission (.*)$")
	public void VerifyFeedBackSuccessMsgIsDisplayed(String expectedText) {
		String actualText=contactPage.verifyFeedbackSuccessMsgIsDisplayed();
		Assert.assertEquals(expectedText, actualText);
	}

	@When("the user clicks Submit button")
	public void clicksSubmitButton() {
		contactPage.clickSubmitBtn();
	}

	@When("user clicks Shop link")
	public void clickShopLink() {
		homePage.clickShopLink();
	}

	@When("user clicks Cart link")
	public void clickCartLink() {
		homePage.clickCartLink();
	}

	@Then("user is on shop page")
	public void verifyShoppingProductsAreDisplayed() {
		boolean isProductsDisplayed= shopPage.verifyShoppingProductsAreDisplayed();
		Assert.assertTrue(isProductsDisplayed);
	}

	@And("the user adds the following items to the cart and gets total Price of all items")
	public void addProductsToTheCartAndGetTotalPriceOfAllItems(DataTable dataTable) {
		List<Map<String, String>> items = dataTable.asMaps(String.class, String.class);		
		for (Map<String, String> item : items) {
			String productToAdd = item.get("Product");
			int quantity = Integer.parseInt(item.get("Quantity"));
			totalQuantity += quantity;
			double total = shopPage.addProductsToCartAndGetCartSubtotal(productToAdd, quantity);
			totalSubtotal += total;			
		}System.out.println("Total sum of subtotal for all products is: $" + totalSubtotal);	
	}

	@And("verifies cart total quantity")
	public void verifyCartTotal() {
		int actualCartTotal = Integer.parseInt(cartPage.getTotalNumOFItemsInCart());
		Assert.assertEquals(totalQuantity, actualCartTotal);
	}

	@And("user verifies the product total is equal to the sum of subtotals")
	public void verifyProductTotalIsequalToSumOfSubtotal() {
		String actualTotalValueOfProductPrice = cartPage.getTotalValueOfProductPrice();
		Assert.assertEquals(String.valueOf(totalSubtotal).replace("$", ""), actualTotalValueOfProductPrice);
	}

	@After
	public void tearDown(Scenario scenario)
	{
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "FailedScenarioScreenshot");
		}	  
		driver.close();
		driver.quit();
	}
}
