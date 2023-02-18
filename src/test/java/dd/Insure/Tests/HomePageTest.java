package dd.Insure.Tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import dd.Insure.TestComponents.BaseTest;

public class HomePageTest extends BaseTest{
	
	//Check if url is navigating the original url or not
	@Test(priority = 1)
	public void urlNavigation() {
		
		SoftAssert sa = new SoftAssert();
		String expectedURL = properties.getProperty("url");
		String actualURL = driver.getCurrentUrl();
		sa.assertTrue(expectedURL.equalsIgnoreCase(actualURL), "URL Modified");
	}
	
	//Check if logo is displayed or not
	@Test(priority = 2)
	public void Logo() {
		WebElement logo = homePage.LogoCheck();
		Assert.assertTrue(logo.isDisplayed(), "Logo image is not displayed");	
	}
	
	//Check if welcome message is displayed on landing page or not
	@Test(priority = 3)
	public void WelcomeMessage() throws InterruptedException {
		
		String actualMessage = "Welcome";
		String welcomeMessage = homePage.getMessage();
		Assert.assertTrue(welcomeMessage.contains(actualMessage), "Welcome Message Check Unsuccessful");	
	}
	
	//Check if need help link is dispalyed and enabled or not
	@Test(priority = 4)
	public void NeedHelpLink() {
		
		WebElement needHelpLink = homePage.NeedHelpLinkCheck();
		Assert.assertTrue(needHelpLink.isDisplayed(), "Need Help button is not displayed");
		Assert.assertTrue(needHelpLink.isEnabled(), "Need Help button is not enabled");
		needHelpLink.click();
	}
	
	//Check if need help link click shows dialog and have support emmail id or not
	@Test(dependsOnMethods = "NeedHelpLink", priority = 5)
	public void NeedHelpDialog() {
		
		WebElement needHelpDialog = homePage.NeedHelpDialogCheck();
		Assert.assertTrue(needHelpDialog.isDisplayed(), "Need Help Dialog is not displayed");
		
		WebElement needHelpEmailId = homePage.NeedHelpEmailId();
		Assert.assertTrue(needHelpEmailId.getText().contains("yoursupport@insure.com"));
	}
	
}
