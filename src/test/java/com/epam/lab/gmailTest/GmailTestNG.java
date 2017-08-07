package com.epam.lab.gmailTest;

import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import com.epam.lab.projectBOs.LoginBO;
import com.epam.lab.projectBOs.MailBO;
import com.epam.lab.utils.Driver;
import com.epam.lab.utils.ExcelDataPrivider;
import com.epam.lab.utils.PropertiesLoader;
import com.epam.lab.listener.MyListener;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

@Listeners({MyListener.class, HTMLReporter.class})
public class GmailTestNG {

	@BeforeMethod
	public void beforeMethod(){
	}

	@Test(threadPoolSize = 5, dataProvider = "getUsersData", dataProviderClass = ExcelDataPrivider.class)
	public void testSend(String propName) throws Exception{
		Properties mailProp = new PropertiesLoader().getProperties(propName + ".properties");
		LoginBO login = new LoginBO(mailProp);
		Assert.assertTrue(login.loginInEmail());
		MailBO mailBO = new MailBO(mailProp);
		mailBO.createAndSendLetter();
		Assert.assertTrue(mailBO.deleteFromSent());		
	}
	
	@AfterMethod
	public void afterMethod() {
		Driver.quitDriver();
				
	}

}
