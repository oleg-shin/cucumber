package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;

public class LoginSteps extends CommonMethods {
    //public static WebDriver driver;
    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() throws InterruptedException {
        openBrowserAndLaunchApplication();
    }
    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
        //WebElement username = driver.findElement(By.id("txtUsername"));
        //username.sendKeys(ConfigReader.getPropertyValue("username"));
        //LoginPage login = new LoginPage();
        sendText(login.usernameTextField,ConfigReader.getPropertyValue("username"));
        //WebElement password = driver.findElement(By.id("txtPassword"));
        //password.sendKeys(ConfigReader.getPropertyValue("password"));
        sendText(login.passwordTextField, ConfigReader.getPropertyValue("password"));
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() throws InterruptedException {
        //WebElement loginBtn = driver.findElement(By.id("btnLogin"));
        //loginBtn.click();
        //Thread.sleep(2000);
        //LoginPage login = new LoginPage();
        click(login.loginButton);
    }
    @Then("admin user is successfully logged in")
    public void admin_user_is_successfully_logged_in() {
        System.out.println("Test passed");
        //closeBrowser();
    }
    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        WebElement username = driver.findElement(By.id("txtUsername"));
        sendText(username, "Adm");
        WebElement password = driver.findElement(By.id("txtPassword"));
        sendText(password,"Hum@");
    }
    @Then("user is able to see error message")
    public void user_is_able_to_see_error_message() {
        System.out.println("Test Failed");
        closeBrowser();
    }
    @When("user enters ess username and password")
    public void user_enters_ess_username_and_password() {
        //LoginPage login = new LoginPage();
        sendText(login.usernameTextField,"Admin");
        sendText(login.passwordTextField, "Hum@nhrm123");
    }
    @Then("ess user is successfully logged in")
    public void ess_user_is_successfully_logged_in() {
        System.out.println("Test passed");
    }

    @When("user eneters different {string} and {string} and verify the {string}")
    public void user_eneters_different_and_and_verify_the(String un, String ps, String error) {
        sendText(login.usernameTextField, un);
        sendText(login.passwordTextField, ps);
        click(login.loginButton);
        String errorActual = login.errorMessage.getText();
        Assert.assertEquals(error, errorActual);
    }
}
