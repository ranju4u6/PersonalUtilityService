package com.itest.personal.utility;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTestStepDefinition {

	WebDriver driver = null;
	

	@Given("^I am in personal utility page$")
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://192.168.0.102:8080/PersonalUtility/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Given("^I enter user as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void i_enter_user_as_and_password_as(String arg1, String arg2) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameId")));
		element.sendKeys(arg1);
		
		driver.findElement(By.id("passwordId")).sendKeys(arg2);
	}

	@When("^I click the login button$")
	public void i_click_the_login_button() throws Throwable {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Then("^The \"([^\"]*)\" tab should be visible$")
	public void the_settings_tab_should_be_visible(String arg0) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String xpath="//a[contains(text(),'"+arg0+"')]";
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		boolean isSettingsVisible = element.isDisplayed();
		Assert.assertTrue(isSettingsVisible);
	}
	
	
	
	
	@Given("^I click on \"([^\"]*)\" tab$")
	public void i_click_on_tab(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String xpath="//a[contains(text(),'"+arg1+"')]";
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		element.click();
	}

	@Then("^The \"([^\"]*)\" button should be disabled$")
	public void the_button_should_be_disabled(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addButtonId")));
		Assert.assertTrue(!element.isEnabled());
	}

	@When("^I enter the user registration details$")
	public void i_enter_the_user_registration_details(DataTable arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameId")));
		WebElement passwordElement = driver.findElement(By.id("passwordId"));
		WebElement confPasswordElement = driver.findElement(By.id("confirmPasswordId"));
		WebElement userTypeElement = driver.findElement(By.id("userTypeIdentifier"));
		Select dropdown = new Select(userTypeElement);
		
		userNameElement.sendKeys(arg1.getGherkinRows().get(0).getCells().get(0).toString());
		passwordElement.sendKeys(arg1.getGherkinRows().get(0).getCells().get(1).toString());
		confPasswordElement.sendKeys(arg1.getGherkinRows().get(0).getCells().get(2).toString());
		dropdown.selectByVisibleText(arg1.getGherkinRows().get(0).getCells().get(3).toString());
		
	}

	@Then("^the \"([^\"]*)\" button should be enabled$")
	public void the_button_should_be_enabled(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("addButtonId")));
		Assert.assertTrue(element.isEnabled());
	}

	@When("^I click on \"([^\"]*)\" button$")
	public void i_click_on_button(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("addButtonId")));
		element.click();
	}

	@Then("^The user \"([^\"]*)\" should be present in the list\\.$")
	public void the_user_should_be_present_in_the_list(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String xpath="//td[contains(text(),'"+arg1+"')]";
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		Assert.assertTrue(element.isDisplayed());
	}
	
	
	
	@Then("^I select \"([^\"]*)\" option$")
	public void i_select_option(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String xpath="//a[contains(text(),'"+arg1+"')]";
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		element.click();
	}

	@Then("^The taskadd button should be disabled$")
	public void the_taskadd_button_should_be_disabled() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addTaskBtnId")));
		Assert.assertTrue(!element.isEnabled());
	}

	@When("^I enter the task details$")
	public void i_enter_the_task_details(DataTable arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement tgtDateElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("targetDateId")));
		WebElement taskElement = driver.findElement(By.id("taskId"));
		
		tgtDateElement.sendKeys(arg1.getGherkinRows().get(0).getCells().get(0).toString());
		taskElement.sendKeys(arg1.getGherkinRows().get(0).getCells().get(1).toString());
	}

	@Then("^the taskadd button should be enabled$")
	public void the_taskadd_button_should_be_enabled() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addTaskBtnId")));
		Assert.assertTrue(element.isEnabled());
	}

	@When("^I click on taskadd button$")
	public void i_click_on_taskadd_button() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addTaskBtnId")));
		element.click();
	}

	@Then("^The task \"([^\"]*)\" should be present in the list\\.$")
	public void the_task_should_be_present_in_the_list(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String xpath="//td[contains(text(),'"+arg1+"')]";
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		Assert.assertTrue(element.isDisplayed());
	}

	@After
	public void cleanUp() {
		driver.close();
	}

}
