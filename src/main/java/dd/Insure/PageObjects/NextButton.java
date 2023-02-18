package dd.Insure.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NextButton extends CommonMethods {
	
	WebDriver driver;

	public NextButton(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css = "div[class*='truncate !text-xl']")
	private WebElement getTitleOfPage;
	
	@FindBy(css = "div[class='truncate text-sm ']")
	private List<WebElement> applicantRadioButtons;
	
	@FindBy(css = "div[class='flex-grow min-w-0 mx-2']")
	private List<WebElement> productList;
	
	@FindBy(id = "first_name")
	private WebElement firstName;
	
	@FindBy(id = "last_name")
	private WebElement lastName;
	
	@FindBy(name = "answer")
	private WebElement email;
		
	@FindBy(xpath = "//div[@class='mr-1']")
	private WebElement nextButton;
	
		
	public String GetTitleOfPage() {
		waitForWebElementToAppear(getTitleOfPage);
		return getTitleOfPage.getText();
	}
	
	
	public List<WebElement> GetapplicantRadioButtons() {
		return applicantRadioButtons;
	}
	
	
	public List<WebElement> GetProductCheckboxes() {
		return productList;
	}
	
	
	public WebElement GetNextbutton() {
		return nextButton;
	}
	
	
	public void AddNames(String first, String last) {
		firstName.sendKeys(first);
		lastName.sendKeys(last);
		nextButton.click();
	}
	
	
	public void AddEmailId(String emailid) {
		email.sendKeys(emailid);
		nextButton.click();
	}

}
