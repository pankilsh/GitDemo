package PankilSharma.ExtentReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports reportConfig()
	{
		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setDocumentTitle("Reports Demo");
		reporter.config().setReportName("Sample Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pankil Sharma");
		
		return extent;
	}

}
