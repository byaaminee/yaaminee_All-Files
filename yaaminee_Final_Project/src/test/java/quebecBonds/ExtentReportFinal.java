package quebecBonds;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportFinal {
	
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
  
  public void intializer() {
	  //sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/extentSparkReportFinalProject.html");
	  sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/extentSparkReport.html");;
	  sparkReporter.config().setDocumentTitle("Final Exam Automation Report");
	  sparkReporter.config().setReportName("Final Exam Execution Report");
	  sparkReporter.config().setTheme(Theme.STANDARD);
	  sparkReporter.config().setTimelineEnabled(true);
	  sparkReporter.config().setTimeStampFormat("yyyy-mm-dd HH:mm:ss");
	  
	  extent = new ExtentReports();
	  extent.attachReporter(sparkReporter);
  }
  
  public static String captureScreenshot(WebDriver driver, String ssName) throws IOException {
	  
	  String FileSeparator = System.getProperty("file.separator"); // "/" or "\"
	  String Extent_report_path = "."+FileSeparator+"Reports"; // . means parent directory
	  File Src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  
	  //added below code since some city/municipality name has single quote (') which restricts to open the screenshot in the extent report
	  if(ssName.contains("'")){
		ssName = ssName.replaceAll("'", "");  
	  }
	  
	  String Screenshotname = "screenshot"+ssName+".png";
	  File Dst = new File(Extent_report_path+FileSeparator+"Screenshots"+FileSeparator+Screenshotname);
	  FileUtils.copyFile(Src, Dst);
	  String absPath = Dst.getAbsolutePath();
	  //System.out.println("Absolute path is:"+absPath);
	  return absPath;
  }
}
