package demopages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ThankYouPage extends GUIBase {	
	private By thankYou = By.xpath("//*[@id='checkout_complete_container']/h2");
	private By status = By.xpath("//*[@id='checkout_complete_container']/div");
	
	private String thankYouNote;
	private WebDriver driver;
	private String statusNote;

	public ThankYouPage() {
		super();
	}

	public String getThankYouNote() {
		return thankYouNote;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public ThankYouPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public ThankYouPage orderConfirmation() {		
		thankYouNote = printText(thankYou) ;
		statusNote = printText(status) ;
		
		getExtentReporter().pass("ThankYou Page Confirmation", "Recieved");
		return this;
	}
}
