package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.techlistic.pages.IndexPage;
import com.techlistic.util.BaseTest;
import com.techlistic.util.ConfigFileReader;
import com.techlistic.util.ExcelFileReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaceAnOrderStepDefinition extends BaseTest {
	
	WebDriver driver;

	@Before
	public void setUpTest() {
		ConfigFileReader config = new ConfigFileReader();
		driver = startApplication(driver, config.getBrowserName(), config.getAppURL());
	}

	@After
	public void tearDown() {
		closeApplication(driver);
	}

	@Given("^User is on Techlistic Login page$")
	public void user_is_on_techlistic_login_page() {
		loginPage = page.getInstance(IndexPage.class).clickSignIn();
		String loginPageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals("Error occured while navigating to Login page", "Login - My Store", loginPageTitle);

	}

	@When("^^User clicks on sign in providing Username and Password$$")
	public void user_clicks_on_sign_in_providing_username_and_password() {
		ExcelFileReader excelReader = new ExcelFileReader();
		myAccountPage = loginPage.doLogin(excelReader.getUserName("Admin User"), excelReader.getPassword("Admin User"));
	}

	@Then("^User is on Homepage$")
	public void user_is_on_homepage() {
		String myAccountPageTitle = myAccountPage.getMyAccountPageTitle();
		Assert.assertEquals("Error occured while login to the techlistic application", "My account - My Store",
				myAccountPageTitle);
	}

	@When("^User clicks on T-Shirts submenu under Womens menu$")
	public void user_clicks_on_t_shirts_submenu_under_womens_menu() {
		productListPageTShirts = myAccountPage.clickOnWomensTshirts();
	}

	@Then("^User is on T-Shirts list page$")
	public void user_is_on_t_shirts_list_page() {
		String productListPageTitle = productListPageTShirts.getProductListPageTitle();
		Assert.assertEquals("Error occured while navigating to Products List Page", "T-shirts - My Store",
				productListPageTitle);
	}

	@When("^User clicks on More button on 1st T-Shirt$")
	public void user_clicks_on_more_button_on_1st_t_shirt() {
		customizeProductPage = productListPageTShirts.clickOnCustomizeProduct();

	}

	@Then("^User is on customize product page$")
	public void user_is_on_customize_product_page() {
		String customizeProductPageTitle = customizeProductPage.getCustomizeProductPageTitle();
		Assert.assertEquals("Error occured while navigating to Customize Product Page",
				"Faded Short Sleeve T-shirts - My Store", customizeProductPageTitle);
	}

	@When("^User adds T-Shirt by customizing and clicks on checkout$")
	public void user_adds_t_shirt_by_customizing_and_clicks_on_checkout() {
		checkOutPage = customizeProductPage.customizeAndAddProductToCart(1, "L", "Blue");

	}

	@Then("^User is on Checkout page$")
	public void user_is_on_checkout_page() {
		String checkOutPageTitle = checkOutPage.getCheckoutPageTitle();
		Assert.assertEquals("Error occured while product checkout", "Order - My Store", checkOutPageTitle);

	}

	@When("^User Checkout the T-Shirt by confirming$")
	public void user_checkout_the_t_shirt_by_confirming() {
		checkOutPage.orderCheckout();

	}

	@Then("^user should see Order Confirmation message$")
	public void user_should_see_order_confirmation_message() {
		String successMessage = checkOutPage.getSuccessMessage();
		Assert.assertEquals("Error occured confirming the Order", "Your order on My Store is complete.",
				successMessage);
	}

}
