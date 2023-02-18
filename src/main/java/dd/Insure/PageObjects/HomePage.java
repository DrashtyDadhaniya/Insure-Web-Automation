package dd.Insure.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonMethods{
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//div[contains(text(),'Continue application')]")
	private WebElement continueApplication;
	
	@FindBy(xpath = "//div[contains(text(),'Start a new application')]")
	private WebElement startANewApp;
	
	@FindBy(css = "div[class*='text-2xl text-neutral-100']")
	private WebElement welcomeNote;
	
	@FindBy(css = "button[type='button']  div[class='flex items-center justify-center ']")
	private WebElement needHelpLink;
	
	@FindBy(css = "div[class*='bg-white rounded-lg']")
	private WebElement needHelpDialog;
	
	@FindBy(css = "a[href*='@insure.com']")
	private WebElement needHelpEmailid;
	
	@FindBy(css = "img[alt='Header Logo']")
	private WebElement logo;
	
	@FindBy(xpath = "//div[@class='mr-1']")
	private WebElement getStartedButton;
	
	@FindBy(xpath = "//div[@class='mr-1']")
	private WebElement nextButton;
	
	
	public WebElement GetStartedButton() {
		waitForWebElementToAppear(getStartedButton);
		return getStartedButton;
	}
	
	
	public String getMessage() {
		waitForWebElementToAppear(welcomeNote);
		return welcomeNote.getText();
	}
	
	
	public WebElement GetContinueAppButton() {
		return continueApplication;
	}
	
	
	public WebElement GetStartNewAppButton() {
		return startANewApp;
	}
	
		
	public WebElement NeedHelpLinkCheck() {
		return needHelpLink;
	}
	
	
	public WebElement NeedHelpDialogCheck() {
		return needHelpDialog;
	}
	
	
	public WebElement NeedHelpEmailId() {
		return needHelpEmailid;
	}
	
	
	public WebElement LogoCheck() {
		return logo;
	}

	
	public NextButton GetStartedButtonClick() {
		getStartedButton.click();
		waitForWebElementToAppear(nextButton);
		return new NextButton(driver);		
	}

}
