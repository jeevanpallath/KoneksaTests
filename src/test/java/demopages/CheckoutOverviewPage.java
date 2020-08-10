package demopages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage extends GUIBase {	
	private By paymentItems = By.cssSelector("div.summary_info > div");
	private By finishBtn = By.cssSelector("a.btn_action.cart_button");

	WebDriver driver;

	public CheckoutOverviewPage() {
		super();
	}

	public CheckoutOverviewPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	//Priinting payment informations
	public CheckoutOverviewPage printPaymentInfo() {		
		List<WebElement> paymaentTexts = driver.findElements(paymentItems);   

		for(int i = 0 ; i < paymaentTexts.size()-2; i++) {	//not printing cancel and finish button texts
			System.out.println(paymaentTexts.get(i).getText());
		}	

		return this;
	}

	//Clicking Finish button
	public CheckoutOverviewPage finish() {		
		driver.findElement(finishBtn).click();
		System.out.println("Pressed Finish button\n");
		getExtentReporter().pass("Checkout Overview Page", "Clicked Finish");
		return this;
	}
}
