package com.epam.lab.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener{
	private static final Logger logger = Logger.getLogger(MyListener.class);
	public void onFinish(ITestContext arg0) {
		 logger.info("Testing is finished");
		
	}

	public void onStart(ITestContext arg0) {
		 logger.info("Testing is started");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		 logger.info("Test is failed" );
		
	}

	public void onTestFailure(ITestResult arg0) {
		 logger.info("Test is failed" );
		
	}

	public void onTestSkipped(ITestResult arg0) {
		 logger.info("Test is skipped" );
		
	}

	public void onTestStart(ITestResult arg0) {
		 logger.info("Test is started");
		
	}

	public void onTestSuccess(ITestResult arg0) {
		 logger.info("Testing is successfull");
		
	}

}
