package koneksatests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import Reports.ExtReporter;
import demopages.AddressPage;
import demopages.CheckoutOverviewPage;
import demopages.GUIBase;
import demopages.LoginPage;
import demopages.ProductsPage;
import demopages.ThankYouPage;
import demopages.Variables;
import demopages.YourCartPage;


public class SeleniumTestsRunner extends GUIBase {

	List<String> cartList = new ArrayList<String>();
	static ExtReporter extentReporter ;
	
	
	public SeleniumTestsRunner() {
		super();
	}
	public SeleniumTestsRunner(WebDriver driver) {
		super(driver);
	}
	
	public static void main(String[] args) {		
		SeleniumTestsRunner javaRunner = new SeleniumTestsRunner();	
		TestUtils.readBrowserAndDriverInfo();

		javaRunner.startBrowser(Variables.browser);
		javaRunner.testExecute();
		//extentReporter.closeReport();
	}
	
	public SeleniumTestsRunner testExecute() {
		LoginPage loginPage = new LoginPage(this.driver );
		loginPage.setExtentReporter(extentReporter);
		
		loginPage.validLogin();	
		
		ProductsPage productPage = new ProductsPage(this.driver );
		productPage.setExtentReporter(extentReporter);
		
		productPage.countNumberOfItems()
		.sortItems()
		.addToCart()
		.goToCart();
	
		YourCartPage yourCartPage = new YourCartPage(this.driver );
		yourCartPage.setExtentReporter(extentReporter);
		yourCartPage.checkCartList();
		
		Assert.assertEquals(yourCartPage.getCartCheckOutList().size(), 3 , "Number of itmes not matching in checkout Cart");
		
		Assert.assertEquals(productPage.getCartList(), yourCartPage.getCartCheckOutList(), "Cross checking Order items and items in Cart");
		yourCartPage.checkOut();
		
		AddressPage addressPage = new AddressPage(this.driver );
		addressPage.setExtentReporter(extentReporter);
		addressPage.enterAddress()
		.submitAddress();
		
		CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(this.driver );
		checkoutOverviewPage.setExtentReporter(extentReporter);
		
		checkoutOverviewPage.printPaymentInfo()
		.finish();
		
		ThankYouPage thankYou = new ThankYouPage(this.driver );
		thankYou.setExtentReporter(extentReporter);
		
		thankYou.orderConfirmation();
		
		Assert.assertTrue(thankYou.getThankYouNote().contains("THANK YOU FOR YOUR ORDER"));
		Assert.assertTrue(thankYou.getStatusNote().contains("Your order has been dispatched, and will arrive just as fast as the pony can get there!"));
		
		extentReporter.closeReport();
		driver.close();
		return this;
	}

	
	public  SeleniumTestsRunner startBrowser(String browser) {
		try {		
			System.out.println("Starting selenium tests....");
			extentReporter = new ExtReporter(Variables.SeniumReportFile);
			
			this.setExtentReporter(extentReporter);	
			
			if(browser == null) {	//Bypass case of user spoiled json file content
				browser = "chrome";
				System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", Variables.driverPath);
				driver = new ChromeDriver();
			}			
			else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", Variables.driverPath);
				driver = new FirefoxDriver();
			}

			driver.get("https://www.saucedemo.com/");
			driver.manage().window().maximize();
			this.getExtentReporter().pass("Koneksa Tests", "Browser launched");

		} catch (Exception e) {
			e.printStackTrace();
			extentReporter.fail("Browser Launching", e.getMessage());
		}
		return this;
	}
}
