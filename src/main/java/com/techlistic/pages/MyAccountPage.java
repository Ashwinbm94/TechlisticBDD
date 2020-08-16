/**
 * 
 */
package com.techlistic.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.techlistic.util.Utility;

/**
 * @author Ashwin BM
 *
 */
public class MyAccountPage extends BasePage{


	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	private By menuWomen = By.xpath("//a[@title='Women']");
	private By womenTshirtsSubMenu = By.xpath("//ul[@class='submenu-container clearfix first-in-line-xs']//a[@title='T-shirts']");
	
	
	public String getMyAccountPageTitle() {
		return getPageTitle();
	}
	
	public ProductsListPageTShirts clickOnWomensTshirts() {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(menuWomen)).build().perform();
		waitForWebElementVisibility(womenTshirtsSubMenu);
		getWebElement(womenTshirtsSubMenu).click();
		driver.manage().timeouts().pageLoadTimeout(Utility.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		
		return getInstance(ProductsListPageTShirts.class);
	}
	
	

}
