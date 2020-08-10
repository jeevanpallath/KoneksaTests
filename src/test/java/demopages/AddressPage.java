package demopages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import koneksatests.TestUtils;

public class AddressPage extends GUIBase {	
	private By firstName = By.cssSelector("input#first-name");
	private By lastName = By.cssSelector("input#last-name");
	
	private By zipCode = By.cssSelector("input#postal-code");
	By checkOutContinue = By.xpath("/html/body/div/div[2]/div[3]/div/form/div[2]/input");

	private WebDriver driver;

	public AddressPage() {
		super();
	}

	public AddressPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	//reading address from Json file and enter to form
	//if no file found goes with default values
	public AddressPage enterAddress() {
		System.out.println("\nFilling address details");
		JSONObject jsonObject = TestUtils.readJsonFile("./address.json");
		
		if(jsonObject.isEmpty()) {
			firstName("Reagan");
			laststName("Raymond");
			zip("07047");
		}
		else {
			JSONObject address = (JSONObject) jsonObject.get("address");
	         String firstName = (String) address.get("firstName");
	         firstName( firstName);
	         
	         String lastName = (String)address.get("lastName");
	         laststName(lastName);
	         
	         String zipcode = (String)address.get("zip");
	         zip(zipcode);
		}

		return this;
	}
	
	//Filling first name field
	public AddressPage firstName(String fn) {
		clearAndSend(firstName , fn);
		System.out.println("First Name :"+fn);
		return this;
	}
	
	//Filling last name field
	public AddressPage laststName(String ln) {
		clearAndSend(lastName , ln);
		System.out.println("Last Name :"+ln);
		return this;
	}
	
	//Filling zip field
	public AddressPage zip(String pin) {
		clearAndSend(zipCode , pin);
		System.out.println("Pin :"+pin);
		return this;
	}
	
	//Clicking submit button
	public AddressPage submitAddress() {
		driver.findElement(checkOutContinue).click();
		System.out.println("Address details submitted\n");
		getExtentReporter().pass("AddressPage", " Address submitted");
		return this;
	}

}
