package pages;

import org.testng.asserts.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class submitFormPage {

	public submitFormPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}
	WebDriver driver;	
	public static By btn_submit_new_application = By.cssSelector("a[href*='project/new'][class*='btn']");
	public static By btn_continue_with_submission = By.cssSelector("a[href*='project/form'][class*='btn']");
	public static By txt_first_name = By.cssSelector("input[aria-label='First name']");
	public static By txt_last_name = By.cssSelector("input[aria-label='Last name']");
	
	public static By txt_extension_name = By.cssSelector("input[aria-label='Extension']");
	public static By txt_address = By.cssSelector("textarea[aria-label*='House']");
	public static By txt_postal_code = By.cssSelector("input[aria-label*='postal code']");
	public static By drp_country = By.cssSelector("select[aria-label*='Country']");
	public static By btn_file = By.cssSelector("input[name='Filedata']");	
	
	public static By drp_role = By.cssSelector("select[aria-label*='Select a role']");
	public static By txt_objective = By.xpath("//div[@role='presentation']/iframe");	
	public static By btn_nextScreen = By.cssSelector("button#navButtonNext");
	public static By label_full_name = By.xpath("//legend[contains(text(),'Full name')]/parent::fieldset");
	public static By label_first_name = By.xpath("//p/strong[contains(text(),'First name')]/ancestor::div[contains(@class,'question-text view')]");
	
	public static By label_last_name = By.xpath("//p/strong[contains(text(),'Last name')]/ancestor::div[contains(@class,'question-text view')]");
	public static By label_extension_name = By.xpath("//p/strong[contains(text(),'Extension')]/ancestor::div[contains(@class,'question-text view')]");
	
	public static By label_address_house_no = By.cssSelector("div[class*='textarea view']");
	public static By label_postal_code = By.cssSelector("div[class*='location view']");
	public static By label_country= By.cssSelector("div[class*='locationCountry view']");
	public static By label_gender = By.cssSelector("div[class*='question-radio view']");
	public static By label_role = By.cssSelector("div[class*='question-dropdown view']");
	public static By label_attachment = By.cssSelector("a[href*='projectAnswer']");	
	public static By label_skills = By.cssSelector("div[class*='question-checkbox view']");
	public static By iframe = By.cssSelector("iframe[class*='siwyg_frame']");
	
	public static By label_objective = By.cssSelector("div[class*='question-richtext view']");
	
	public static By btn_validate_and_send = By.cssSelector("button[id='submitButton'][class*='auto']");
	public static By lbl_success_message = By.cssSelector("h1[class*='h1 text-center']");
	
}
