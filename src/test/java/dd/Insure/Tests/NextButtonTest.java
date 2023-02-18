package dd.Insure.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dd.Insure.PageObjects.NextButton;
import dd.Insure.TestComponents.BaseTest;

public class NextButtonTest extends BaseTest {
	
	//public NextButton nextButton;
	
	//Check which button displayed Get started button, start a new app button or continue buton is displayed
	@Test(priority = 6, enabled=true)
	public void GetStartedButtonCheck() throws InterruptedException {
		
		WebElement getStartedButton = homePage.GetStartedButton();
		WebElement getContinueAppButton = homePage.GetContinueAppButton();
		WebElement getStartNewAppButton = homePage.GetStartNewAppButton();
		
		if(getStartedButton.isDisplayed())
		{
			Assert.assertTrue(getStartedButton.isDisplayed(), "Get Started button is not displayed");
			Assert.assertTrue(getStartedButton.isEnabled(), "Get Started button is not enabled");
			Assert.assertTrue(getStartedButton.getText().equalsIgnoreCase("Get Started"), "Buttom name mismatch");
			nextButton = homePage.GetStartedButtonClick();
		}
		
		else 
		{
			Assert.assertTrue(getContinueAppButton.isDisplayed(), "Continue Application button is not displayed");
			Assert.assertTrue(getContinueAppButton.isEnabled(), "Continue Application button is not enabled");
			Assert.assertTrue(getContinueAppButton.getText().equalsIgnoreCase("Continue application"), "Continue Application Buttom name mismatch");
			
			Assert.assertTrue(getStartNewAppButton.isDisplayed(), "Start a new button is not displayed");
			Assert.assertTrue(getStartNewAppButton.isEnabled(), "Start a new button is not enabled");
			Assert.assertTrue(getStartNewAppButton.getText().equalsIgnoreCase("Start a new application"), "Start a new button name mismatch");
			getStartNewAppButton.click();
		}
		
		title = GetPageTitle(nextButton.GetNextbutton());
		System.out.println("GetStartedButtonCheck****"+title);
	}
	
	
	//Select applicant type & check if all radio buttons are enabled or not
	@Test(dependsOnMethods = "GetStartedButtonCheck", enabled=true, priority = 7)
	public void ApplicantTypePage() throws InterruptedException {
		
		if(title.equalsIgnoreCase("Applicant Type"))
		{
			List<WebElement> applicantRadioButtons = nextButton.GetapplicantRadioButtons();
			SelectRadioButton(applicantRadioButtons, properties.getProperty("applicantType"));
			title = GetPageTitle(nextButton.GetNextbutton());
		}
		
		else
			//Thread.sleep(2000);
			title = GetPageTitle(nextButton.GetNextbutton());
		System.out.println("ApplicantTypePage****"+title);
	}
	
	//Select Product type and check if all list items are enabled or not
	@Test(enabled=true, priority = 8)
	public void ProductSelect() throws InterruptedException {
		
		if(title.equalsIgnoreCase("Product"))
		{
			List<WebElement> productListItems = nextButton.GetProductCheckboxes();
			SelectCheckBox(productListItems, properties.getProperty("product"));
			WebElement nextbutton = nextButton.GetNextbutton();
			nextbutton.click();
			title = GetPageTitle(nextButton.GetNextbutton());
		}
		
		else
			title = GetPageTitle(nextButton.GetNextbutton());
		System.out.println("ProductSelect****"+title);
	}
	
	
	//Fill Names
	@Test(priority = 9, dataProvider = "NameDataProvider", enabled=true)
	public void NameInfoValidation(String first, String last) throws InterruptedException {
		
		if(title.equalsIgnoreCase("Name"))
		{
			nextButton.AddNames(first, last);		
			title = GetPageTitle(nextButton.GetNextbutton());
		}	
		
		else
			title = GetPageTitle(nextButton.GetNextbutton());
		System.out.println("NameInfoValidation*********"+title);
	}
	
	
	//Fill email id
	@Test(priority = 10, dataProvider = "EmailDataProvider", enabled=true)
	public void EmailIdValidation(String emailid) throws InterruptedException {
		
		if(title.equalsIgnoreCase("Email"))
		{
			nextButton.AddEmailId(emailid);		
			title = GetPageTitle(nextButton.GetNextbutton());
		}	
		
		else
			title = GetPageTitle(nextButton.GetNextbutton());
		System.out.println("EmailIdValidation*********"+title);
	}
	
	@DataProvider
	public Object[][] NameDataProvider() throws IOException {

		return new Object[][] { { "Drashty", "Dadhaniya"}, { "Prisha", "Vachhani"} };
	}

	@DataProvider
	public Object[][] EmailDataProvider() throws IOException {

		return new Object[][] { { "drashty@gmail.com" }, { "prisha@gmail.com" } };
	}

}
