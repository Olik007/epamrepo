package com.epam.lab.projectBOs;

import java.util.Properties;
import com.epam.lab.pages.HomePage;


public class MailBO {
	
	private static final String SEND_TO = "SEND_TO";
	private static final String SUBJECT = "SUBJECT";
	private static final String TEXT = "TEXT";
	private static final String USE_DRIVER = "USE_DRIVER";
	
	private Properties prop;
	HomePage mailHomePage;
	
	public MailBO(Properties prop) throws Exception {
		this.prop = prop;
	}
	
	public void createAndSendLetter() throws Exception{
		mailHomePage = new HomePage(prop.getProperty(USE_DRIVER));
		mailHomePage.sendEmail(prop.getProperty(SEND_TO), prop.getProperty(SUBJECT), prop.getProperty(TEXT));
	}
	
	public boolean deleteFromSent(){
		mailHomePage.checkSentAndDelete(prop.getProperty(SEND_TO), prop.getProperty(SUBJECT));
		return true;
	}

}
