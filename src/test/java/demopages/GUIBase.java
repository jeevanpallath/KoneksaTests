package demopages;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import Reports.ExtReporter;

public class GUIBase {
	public WebDriver driver;
	public Wait<WebDriver> wait;
	public ExtReporter extentReporter ;

	public GUIBase() {		
	}

	public GUIBase(WebDriver driver) {
		this.driver = driver;

		wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(200))
				.ignoring(NoSuchElementException.class);
	}
	
	public ExtReporter getExtentReporter() {
		return extentReporter;
	}

	public GUIBase setExtentReporter(ExtReporter extentReporter) {
		this.extentReporter = extentReporter;
		return this;
	}
	//Clear the field and write new data
	void clearAndSend( final By element, String data) {
		try {
			WebElement clickseleniumlink = getElementAfterFluentWait(element);

			clickseleniumlink.clear();
			clickseleniumlink.sendKeys(data);
		} catch (Exception e) {			
			e.printStackTrace();
			getExtentReporter().fail("Control Locating", "By :" + element.toString());
		}		
	}

	String printText(By element) {		
		String textData = "";
		try {
			textData = getElementAfterFluentWait(element).getText();
			System.out.println(textData);
		} catch (Exception e) {
			e.printStackTrace();
			getExtentReporter().fail("Control Locating", "By :" + element.toString());
		}
		
		return textData;
	}
	
	//below function waits for a webelement using fluent wait
	public WebElement getElementAfterFluentWait(final By element) {
		WebElement webControl = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver ) {
				return driver.findElement(element);
			}
		});
		return webControl;
	}
}
