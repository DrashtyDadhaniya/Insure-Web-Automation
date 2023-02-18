package dd.Insure.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dd.Insure.PageObjects.CommonMethods;
import dd.Insure.PageObjects.HomePage;
import dd.Insure.PageObjects.NextButton;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Properties properties;
	public HomePage homePage;
	public String title;
	public NextButton nextButton;
	
	
	@BeforeTest(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initializeDriver();										//Initialize Drivers
		homePage = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String Url = properties.getProperty("url");							//Set url from config.properties file
		driver.get(Url);
	}
	

	@AfterTest(alwaysRun = true, enabled = true)
	public void tearDown() {
		driver.quit();
	}
	

	//Read properties file
	public Properties readPropertiseFile() throws IOException {

		properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//config.properties");
		properties.load(fis);
		return properties;
	}
	

	//Set chrome, firefox or edge browser drivers
	public WebDriver initializeDriver() throws IOException {

		properties = readPropertiseFile();
		
		// Read browser properties from maven command or properties file
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if (browserName.contains("headless")) {
				options.addArguments("--headless");
			}
			driver = new ChromeDriver(options);
			 
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();

			if (browserName.contains("headless")) {
				options.addArguments("--headless");
			}
			driver = new FirefoxDriver(options);
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			WebDriverManager.edgedriver().setup();

			if (browserName.contains("headless")) {
				options.addArguments("--headless");
			}
			driver = new EdgeDriver(options);
		}

		driver.manage().window().maximize();
		return driver;
	}

	
	//Method to take screenshot & store the image under reports folder
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		System.out.println("Screen shot captured for the error and saved");
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	
	//Method to select radio button
	public static void SelectRadioButton(List<WebElement> RadioButtonList, String value) {

		for (WebElement radioButton : RadioButtonList) {
			Assert.assertTrue(radioButton.isEnabled(), radioButton + "Radio button is not enabled");
			if (radioButton.getText().equalsIgnoreCase(value) && !radioButton.isSelected()) {
				radioButton.click();
			}

		}
	}
	
	//Method to select checkbox
	public static void SelectCheckBox(List<WebElement> CheckboxList, String value) {

		for (WebElement Checkbox : CheckboxList) {
			Assert.assertTrue(Checkbox.isEnabled(), CheckboxList + "Checkbox is not enabled");
			if (Checkbox.getText().equalsIgnoreCase(value) && !Checkbox.isSelected()) {
				Checkbox.click();
			}

		}
	}
	

	//Method to get title of the page and wait until next button display
	public String GetPageTitle(WebElement nextButton) {

		CommonMethods commonMethods = new CommonMethods(driver);
		commonMethods.waitForWebElementToAppear(nextButton);
		title = driver.getTitle();
		return title;
	}

}
