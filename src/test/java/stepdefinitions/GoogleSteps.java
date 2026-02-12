package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverFactory;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

public class GoogleSteps {

    WebDriver driver = DriverFactory.getDriver();

    @Given("User opens Google homepage")
    public void openGoogle() {
      //  driver.get("https://www.google.com");
    	
    	String browser = System.getProperty("browser", "chrome");

    	ChromeOptions options = new ChromeOptions();

    	if(browser.equalsIgnoreCase("headless")){
    	    options.addArguments("--headless=new");
    	    options.addArguments("--no-sandbox");
    	    options.addArguments("--disable-dev-shm-usage");
    	}

    	driver = new ChromeDriver(options);
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	driver.manage().window().maximize();
    }

    @When("User searches for {string}")
    public void searchFor(String searchText) {
//        driver.findElement(By.name("q"))
//                .sendKeys(searchText + Keys.ENTER);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	WebElement searchBox = wait.until(
    	        ExpectedConditions.visibilityOfElementLocated(By.name("q"))
    	);

    	searchBox.sendKeys(searchText + Keys.ENTER);
    }

    @Then("User should see page title containing {string}")
    public void verifyTitle(String expectedText) {
        assertTrue(!driver.getTitle().contains(expectedText));
    }
    
    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}