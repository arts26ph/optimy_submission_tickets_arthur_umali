package stepDefinition;

import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.loginPage;
import pages.submitFormPage;

public class Test_Steps {
	public static WebDriver driver = null;
	public static String userName = "optimyautomationtester@gmail.com";
	public static String password = "yRMhojb7";	
	public static int randomNumber = new Random().nextInt(9000) + 10000;
	public static String firstName = "qa_first_name";
	public static String lastName = "qa_last_name";
	public static String extensionName = "qa_ex";
	public static String address = "qa_add";
	public static String postalCode = "1000";
	public static String country = "Finland";
	public static String gender = "Male";
	public static String role = "Manual tester";
	public static String [] skillsSet = {"Java", "Python","Cucumber"};
	public static String objective = "Agile tester";
	WebDriverWait customWait = new WebDriverWait(driver, 5); 
	@Given("^User is on Login page$")
	public void user_is_on_login_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://automationinterface1.front.staging.optimy.net/en/");   
        driver.manage().window().maximize();        
        try {
        	customWait = new WebDriverWait(driver, 5); 
            customWait.until(ExpectedConditions.elementToBeClickable(loginPage.btn_allow_all_cookies));
           if ( driver.findElement(loginPage.btn_allow_all_cookies).isDisplayed()) {
        	   driver.findElement(loginPage.btn_allow_all_cookies).click();
        	   
           }
        }catch (Exception e) {}
        driver.findElement(loginPage.btn_login1).click();
		throw new PendingException();
	}
	
	@When("^User logs in$")
	public void user_login() throws Throwable{
		 driver.findElement(loginPage.txt_username).clear();
	        driver.findElement(loginPage.txt_username).sendKeys(userName);
	        driver.findElement(loginPage.txt_password).clear();
	        driver.findElement(loginPage.txt_password).sendKeys(password);        
	        driver.findElement(loginPage.btn_login2).click();
		throw new PendingException();
	}
	
	@Then("^The Submit new application page will be displayed$")
	public void submit_new_application_page_successfully_displayed() throws Throwable {
		 try {
	        	customWait = new WebDriverWait(driver, 5); 
	            customWait.until(ExpectedConditions.elementToBeClickable(submitFormPage.btn_submit_new_application));
	        }catch (Exception e) {}
	        driver.findElement(submitFormPage.btn_submit_new_application).click();
		throw new PendingException();
	}
	
	@When("^The Continue with the submission of the application is present$")
	public void when_continue_with_the_submission_is_displayed() throws Throwable{
		try {
        	customWait = new WebDriverWait(driver, 5); 
            customWait.until(ExpectedConditions.numberOfElementsToBe(submitFormPage.btn_continue_with_submission, 1));
        }catch (Exception e) {}
        if (driver.findElements(submitFormPage.btn_continue_with_submission).size()>0) {
        	driver.findElement(submitFormPage.btn_submit_new_application).click();
        }else {
        	 driver.findElements(submitFormPage.btn_submit_new_application).get(0).click();
        }
		throw new PendingException();
	}
	
	@Then("^User clicks the Submit a new application button and fill-out the forms$")
	public void user_fill_out_the_forms() throws Throwable{
		 LocalDateTime now = LocalDateTime.now();
		 System.setProperty("webdriver.chrome.driver","C:\\test_img.PNG");
		firstName = firstName+randomNumber+now;
		randomNumber = new Random().nextInt(9000) + 10000;
		lastName = lastName+randomNumber+now;
		randomNumber = new Random().nextInt(9000) + 10000;
		extensionName = extensionName+randomNumber+now;
		randomNumber = new Random().nextInt(9000) + 10000;
		address = address+" "+ randomNumber+"-"+ now;
		objective = objective+"-"+ randomNumber;
		driver.findElement(submitFormPage.txt_first_name).clear();
        driver.findElement(submitFormPage.txt_first_name).sendKeys(firstName);
        driver.findElement(submitFormPage.txt_last_name).clear();
        driver.findElement(submitFormPage.txt_last_name).sendKeys(lastName);
        driver.findElement(submitFormPage.txt_extension_name).clear();
        driver.findElement(submitFormPage.txt_extension_name).sendKeys(extensionName);
        driver.findElement(submitFormPage.txt_address).sendKeys(address);
        driver.findElement(submitFormPage.txt_postal_code).clear();
        driver.findElement(submitFormPage.txt_postal_code).sendKeys(postalCode);
        WebElement elePostalCodeList = null;
        try {
        	elePostalCodeList = driver.findElement(By.xpath("//ul[contains(@class,'ui-autocomplete')]/li/a[contains(text(),'"+postalCode+"')]"));
        }catch(org.openqa.selenium.NoSuchElementException ex) {}
        for (int x=0; x<10;x++) {
        	try {
        		Thread.sleep(1000);
        		elePostalCodeList.click();
        		break;
        	}catch (org.openqa.selenium.ElementClickInterceptedException ex) {}
        }        
        Select objSelect =new Select(driver.findElement(submitFormPage.drp_country));
        objSelect.selectByVisibleText(country);        
        driver.findElement(submitFormPage.btn_file).sendKeys("C://testimg.PNG");
        driver.findElement(By.xpath("//div[contains(text(),'"+gender+"')]")).click();
         objSelect =new Select(driver.findElement(submitFormPage.drp_role));
        objSelect.selectByVisibleText(role);        
        List<WebElement> listTechKnowledges = driver.findElements(By.xpath("//div[contains(@class,'question-checkbox')]//ul/li"));
        for (String skills: skillsSet) {
        	for (WebElement ele: listTechKnowledges) {
        		System.out.println("testing: "+ele.getAttribute("textContent").trim());
        		if (skills.toLowerCase().contains(ele.getAttribute("textContent").trim().toLowerCase())) {
        			ele.click();
        		}
        	}
        }
        int size = driver.findElements(By.tagName("iframe")).size();
        driver.switchTo().frame(size-1);
        driver.findElement(By.cssSelector("body")).sendKeys(objective);
        driver.switchTo().defaultContent();
		throw new PendingException();
	}
	
	@When("^User clicks the Next screen button$")
	public void user_clicks_the_next_screen() throws Throwable{
		driver.findElement(submitFormPage.btn_nextScreen).click();
		throw new PendingException();
	}
	@Then("^User verifies all entered values are correct$")
	public void user_verifies_entered_values() throws Throwable{
		 Boolean hasIt = false;
	        assertEquals(driver.findElement(submitFormPage.label_first_name).getAttribute("textContent").trim().contains(firstName),true);
	        assertEquals(driver.findElement(submitFormPage.label_last_name).getAttribute("textContent").trim().contains(lastName),true);
	        assertEquals(driver.findElement(submitFormPage.label_extension_name).getAttribute("textContent").trim().contains(extensionName),true);
	        assertEquals(driver.findElement(submitFormPage.label_address_house_no).getAttribute("textContent").trim().contains(address),true);
	        assertEquals(driver.findElement(submitFormPage.label_postal_code).getAttribute("textContent").trim().contains(postalCode),true);
	        assertEquals(driver.findElement(submitFormPage.label_country).getAttribute("textContent").trim().contains(country),true);
	        assertEquals(driver.findElement(submitFormPage.label_gender).getAttribute("textContent").trim().contains(gender),true);
	        assertEquals(driver.findElement(submitFormPage.label_role).getAttribute("textContent").trim().contains(role),true);
	        assertEquals(driver.findElement(submitFormPage.label_role).getAttribute("textContent").trim().contains(role),true);
	        assertEquals(driver.findElement(submitFormPage.label_attachment).getAttribute("textContent").trim().contains("testimg.png"),true);
	        for(String skills: skillsSet) {
	        	hasIt = (driver.findElement(submitFormPage.label_skills).getAttribute("textContent").trim().contains(skills)) ? true : false;
	        	assertEquals(hasIt, true);
	        	if (hasIt) {        		
	        		System.out.println("This skill: "+skills+ " is found on the list.");
	        	}
	        }
	        assertEquals(driver.findElement(submitFormPage.label_objective).getAttribute("textContent").trim().contains(objective),true);
	       
		throw new PendingException();
	}
	
	@When("^User clicks the Validate and send button$")
	public void user_clicks_the_validate_and_send() throws Throwable{
		try {
        	customWait = new WebDriverWait(driver, 5); 
            customWait.until(ExpectedConditions.elementToBeClickable(submitFormPage.btn_validate_and_send));
        }catch (Exception e) {}
        driver.findElement(submitFormPage.btn_validate_and_send).click();
		throw new PendingException();
	}
	
	@Then("^User is redirected to Thank you for submitting your project page$")
	public void user_directs_to_thank_you_page() throws Throwable{
		try {
        	customWait = new WebDriverWait(driver, 5); 
            customWait.until(ExpectedConditions.textToBePresentInElementLocated(submitFormPage.lbl_success_message,"Thank you for submitting your project"));
            System.out.println(driver.findElement(By.cssSelector("h1[class*='h1 text-center']")).getAttribute("textContent"));
        }catch (Exception e) {}       
       assertEquals(driver.findElement(By.cssSelector("h1[class*='h1 text-center']")).getAttribute("textContent").toLowerCase().trim(),"thank you for submitting your project");
       driver.quit();
		throw new PendingException();
	}
	
	
}
