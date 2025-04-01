package GenericListenerUtility;

 


import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClassUtility.BaseClass;

public class ListenerImplementation implements ITestListener,ISuiteListener
{
	public static ExtentReports report=null;
	public ExtentSparkReporter spark = null;
	public static ExtentTest test  = null;
	@Override
	public void onStart(ISuite suite) 
	{
		System.out.println("Report Confiquration");
		
	    Date date  = new Date();
	    SimpleDateFormat sim= new SimpleDateFormat("HH-mm-ss");
	    String time= sim.format(date);
	    System.out.println(time);
	    spark = new ExtentSparkReporter("./AdvancedReports/report"+time+".html");
	    spark.config().setDocumentTitle("Ninza_CRM_Project_Results");
	    spark.config().setReportName("CRM_Report");
	    spark.config().setTheme(Theme.DARK);
	    
	    report=new ExtentReports();
	    report.attachReporter(spark);
	    report.setSystemInfo("OS", "Windows-11");
	    report.setSystemInfo("Env", "Production");
	 }

	@Override
	public void onFinish(ISuite suite)
	{
		System.out.println("Report Backeup");
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println(result.getMethod().getMethodName()+"Starts");
		
		test=report.createTest(result.getMethod().getMethodName() );
		test.log(Status.INFO, result.getMethod().getMethodName()+" Started");
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println(result.getMethod().getMethodName()+"ends");
		
	
		test.log(Status.PASS, result.getMethod().getMethodName()+" Ended");

		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		String TestCaseName =result.getMethod().getMethodName();
		Date date=new Date();
		SimpleDateFormat sim =new SimpleDateFormat("HH-mm-ss");
		String time =sim.format(date);
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String Temp=ts.getScreenshotAs(OutputType.BASE64);
		File temp1=ts.getScreenshotAs(OutputType.FILE);
		
		File dest = new File(".//Errshots//"+TestCaseName+"_"+time+".jpeg");
		//C:\Users\Administrator\eclipse-workspace\ProjectNinzaCRM\Screenshotss
		try
		{
	    System.out.println("Screenshot is taken");
		FileHandler.copy(temp1,dest );
		Thread.sleep(3000);
		
		
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
		}
		// TODO Auto-generated method stub
        catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromBase64String(Temp,"Failed"+TestCaseName);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context)
	{
		report.flush();//without this line report can't generate in advadareports given folder
		// TODO Auto-generated method stub
	}

}


//from source -->override and implement methods--> select
