package qqtest;
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

import pages.loginPage;
import pages.submitFormPage;
public class optimy_submit_form_test {
	
	private static String userName = "optimyautomationtester@gmail.com";
	private static String password = "yRMhojb7";	
	static int randomNumber = new Random().nextInt(9000) + 10000;
	private static String firstName = "qa_first_name";
	private static String lastName = "qa_last_name";
	private static String extensionName = "qa_ex";
	private static String address = "qa_add";
	private static String postalCode = "1000";
	private static String country = "Finland";
	private static String gender = "Male";
	private static String role = "Manual tester";
	private static String [] skillsSet = {"Java", "Python","Cucumber"};
	private static String objective = "Agile tester";
	
	public static void main(String[] args) throws InterruptedException {
	/*	Teststeps: 
		1. Go to the URL provided 
		2. Login using the credentials provided 
		3. Click Submit a new application button 
			a. If “Continue with the submission of the application” is present, click “Submit a new application button at the bottom 
		4. Fill-out the forms 
		5. Click Next screen button 
		6. Verify all the inputted values are displayed in the Summary screen 
		7. Click Validate and send button 
		8. Verify you are redirected to "Thank you for submitting your project" page

	*/	
		  
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
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://automationinterface1.front.staging.optimy.net/en/");   
        driver.manage().window().maximize();
        WebDriverWait customWait = new WebDriverWait(driver, 5); 
        try {
        	customWait = new WebDriverWait(driver, 5); 
            customWait.until(ExpectedConditions.elementToBeClickable(loginPage.btn_allow_all_cookies));
           if ( driver.findElement(loginPage.btn_allow_all_cookies).isDisplayed()) {
        	   driver.findElement(loginPage.btn_allow_all_cookies).click();
        	   
           }
        }catch (Exception e) {}
      
        driver.findElement(loginPage.btn_login1).click();
        driver.findElement(loginPage.txt_username).clear();
        driver.findElement(loginPage.txt_username).sendKeys(userName);
        driver.findElement(loginPage.txt_password).clear();
        driver.findElement(loginPage.txt_password).sendKeys(password);        
        driver.findElement(loginPage.btn_login2).click();

        //  Click Submit a new application button 
        try {
        	customWait = new WebDriverWait(driver, 5); 
            customWait.until(ExpectedConditions.elementToBeClickable(submitFormPage.btn_submit_new_application));
        }catch (Exception e) {}
        driver.findElement(submitFormPage.btn_submit_new_application).click();
        try {
        	customWait = new WebDriverWait(driver, 5); 
            customWait.until(ExpectedConditions.numberOfElementsToBe(submitFormPage.btn_continue_with_submission, 1));
        }catch (Exception e) {}
        if (driver.findElements(submitFormPage.btn_continue_with_submission).size()>0) {
        	driver.findElement(submitFormPage.btn_submit_new_application).click();
        }else {
        	 driver.findElements(submitFormPage.btn_submit_new_application).get(0).click();
        }
        //Fill-out the forms 
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
        		if (skills.toLowerCase().contains(ele.getAttribute("textContent").trim().toLowerCase())) {
        			ele.click();
        		}
        	}
        }
        int size = driver.findElements(By.tagName("iframe")).size();
        driver.switchTo().frame(size-1);
        driver.findElement(By.cssSelector("body")).sendKeys(objective);
        driver.switchTo().defaultContent();
        //Go to next screen
        driver.findElement(submitFormPage.btn_nextScreen).click();
        //Verify all the inputted values are displayed in the Summary screen 
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
        try {
        	customWait = new WebDriverWait(driver, 5); 
            customWait.until(ExpectedConditions.elementToBeClickable(submitFormPage.btn_validate_and_send));
        }catch (Exception e) {}
        driver.findElement(submitFormPage.btn_validate_and_send).click();    
       //  Verify you are redirected to "Thank you for submitting your project" page
        try {
        	customWait = new WebDriverWait(driver, 5); 
            customWait.until(ExpectedConditions.textToBePresentInElementLocated(submitFormPage.lbl_success_message,"Thank you for submitting your project"));
            System.out.println(driver.findElement(By.cssSelector("h1[class*='h1 text-center']")).getAttribute("textContent")+ " - End of Test...");
        }catch (Exception e) {}       
       assertEquals(driver.findElement(By.cssSelector("h1[class*='h1 text-center']")).getAttribute("textContent").toLowerCase().trim(),"thank you for submitting your project");
       driver.quit();
	}

}
