package koneksatests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import demopages.Variables;

public class TestNGGUITests {

	static WebDriver driver;
	static SeleniumTestsRunner javaRunner;
	
	@BeforeClass
	public static void createAndStartService() {
		setup();
	}

	public static void setup() {
		javaRunner = new SeleniumTestsRunner();
		TestUtils.readBrowserAndDriverInfo();
		javaRunner.startBrowser(Variables.browser);
		driver = javaRunner.driver;
	}

	@Test
	public void runGuiTests() {
		javaRunner.testExecute();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		System.out.println("\nSelenium Tests Finished\n");
	}

}
