package example.testutil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;
    public void onStart(ITestContext testContext){
    String timeStamp = new SimpleDateFormat( "yyyy.mm.dd.HH.mm.ss").format(new Date());//time stamp
    String repName= "Test-Report-"+timeStamp+" .html";
    htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify loc
   // htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
    extent=new ExtentReports();
    extent.attachReporter(htmlReporter);
    extent. setSystemInfo( "Host name", "localhost");
    extent. setSystemInfo( "Environemnt", "QA");
    extent. setSystemInfo( "user", "Vinay");
    htmlReporter.config().setDocumentTitle("Workfall Partner"); // Tile of report
    htmlReporter.config().setReportName("Candidate application Regression test"); // name of the report
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
        htmlReporter.config().setTheme(Theme.DARK);
    }
    public void onTestSuccess (ITestResult tr){
    logger=extent.createTest(tr.getName()); // create new entry in th report
    logger.log(Status.PASS, MarkupHelper.createLabel( tr.getName(), ExtentColor.GREEN)); // send the passed informati
    }
    public void onTestFailure (ITestResult tr){
    logger=extent.createTest(tr.getName()); // create new entry in th report
     logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information
    String screenshotPath = System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
    File f = new File(screenshotPath);
    if(f.exists()) {
        try {
            try {
                logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
                } catch (IOException e) {
                e.printStackTrace();
                    }
                } catch (Exception e) {
            throw new RuntimeException(e);
            }
        }
    }
    public void onTestSkipped(ITestResult tr){
    logger=extent.createTest(tr.getName()); // create new entry in th report
    logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext){
            extent. flush();
    }
}

