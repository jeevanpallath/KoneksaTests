package koneksatests;
import org.testng.TestNG;


//This class used for running GUI tests + REST API tests from executable Jar file via console/terminal

public class SingleJarRunner {
	static TestNG testng;
	public static void main(String[] args) {

		System.out.println("Starting Automation tests...\n");
		testng = new TestNG();
		
		testng.setTestClasses(new Class[] {TestNGGUITests.class , TestnGApiTests.class});
		testng.run();
		
		System.out.println("\nEnding Automation tests...");
		
		return;
	}

}
