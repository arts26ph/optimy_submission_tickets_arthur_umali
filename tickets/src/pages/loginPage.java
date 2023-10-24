package pages;

import org.testng.asserts.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {

	WebDriver driver;
	public static By btn_allow_all_cookies = By.cssSelector("button[id*='allow-all']");
	public static By btn_login1 = By.cssSelector("a[href*='login']");
	public static By txt_username = By.cssSelector("input[id='login-email']");
	public static By txt_password = By.cssSelector("input[id='login-password");
	public static By btn_submit = By.linkText("Submit a new application");
	public static By btn_login2 = By.xpath("//button[@type='submit' and contains(text(),'Login')]");
	
	
	public loginPage(WebDriver driver) {
		this.driver = driver;
		}
}

