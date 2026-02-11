package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

import static org.junit.Assert.assertTrue;

public class GoogleSteps {

    WebDriver driver = DriverFactory.getDriver();

    @Given("User opens Google homepage")
    public void openGoogle() {
        driver.get("https://www.google.com");
    }

    @When("User searches for {string}")
    public void searchFor(String searchText) {
        driver.findElement(By.name("q"))
                .sendKeys(searchText + Keys.ENTER);
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