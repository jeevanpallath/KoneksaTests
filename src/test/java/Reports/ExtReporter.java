package Reports;

import org.testng.IReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtReporter implements IReporter{
	private ExtentTest test;
	public static ExtentReports extent;
	private String reportFile = "./ExtentReport.html";
	ExtentTest node;
	
	public static void main(String[] args) {
		ExtReporter er = new ExtReporter();
		er.pass("Test1","Des1");
		er.passStep("Step1");
		
		er.fail("Test2","Des2");
		er.failStep("Step1");
		
		er.info("Information");
		er.skip("Skipping");
		
		er.closeReport();
	}
	
	public ExtReporter(String fileName) {
		this.reportFile = fileName;
		init();
	}
	public ExtReporter() {
		init();		
	}
	
	private void init() {
		extent = new ExtentReports();
		ExtentHtmlReporter exHtmlReport = new ExtentHtmlReporter(reportFile);
		extent.attachReporter(exHtmlReport);
	}
	
	public void pass(String testName, String description) {
		test = extent.createTest(testName);
		test.log(Status.PASS, description);
	}
	
	public void fail(String testName,String description) {
		test = extent.createTest(testName);
		test.log(Status.FAIL, description);
	}
	
	public void passStep(String description) {
		node = test.createNode("Node");  // level = 1
		node.log(Status.PASS, description);
	}
	
	public void failStep(String description) {
		node = test.createNode("Node");  // level = 1
		node.log(Status.FAIL, description);
	}
	
	void info(String description) {
		
		test.log(Status.INFO,description);
	}
	
	public void skip(String description) {

		test.log(Status.SKIP,description);
	}

	public void closeReport() {
		extent.flush();
		extent.close();
	}

}
