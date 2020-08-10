package demopages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends GUIBase {
	private By userName = By.cssSelector("input#user-name");
	private By password = By.cssSelector("input#password");
	private By loginBtn = By.xpath("//*[@id='login-button']");

	private String userNameValue = "standard_user";
	private String passwordValue = "secret_sauce";
	private WebDriver driver;
	
	public LoginPage() {
		super();
	}

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	//uses valid user name and password for login
	public LoginPage validLogin() {
		validLoginWithCredentials( this.userNameValue,  this.passwordValue);	
		return this;
	}

	public LoginPage validLoginWithCredentials(String userNameValue, String passwordValue) {
		loginUserId( userNameValue );
		loginPassword(passwordValue );
		
		loginSubmit();	
		getExtentReporter().pass("Login", "User name :" + userNameValue);
		return this;
	}	

	//Fills user name field
	public LoginPage loginUserId(String userNameValue) {		
		clearAndSend(userName, userNameValue );
		return this;
	}

	//Fills user password field
	public LoginPage loginPassword(String passwordValue) {
		clearAndSend(password, passwordValue );
		return this;
	}

	//Click on login button
	public LoginPage loginSubmit() {
		driver.findElement(loginBtn).click();
		System.out.println("\nLogin in as "+userNameValue );
		return this;
	}

}
