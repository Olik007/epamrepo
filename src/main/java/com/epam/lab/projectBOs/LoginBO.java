package com.epam.lab.projectBOs;

import java.util.Properties;
import com.epam.lab.pages.LoginPage;

public class LoginBO {
	
	private static final String LOGIN = "EMAIL";
	private static final String PASSWORD = "PASS";
	private static final String USE_DRIVER = "USE_DRIVER";
	
	private Properties prop;	

	public LoginBO(Properties prop) {
		this.prop = prop;
	}

	public boolean loginInEmail() throws Exception{
		LoginPage mailLoginPage = new LoginPage(prop.getProperty(USE_DRIVER));
		mailLoginPage.typeLoginAndSubmit(prop.getProperty(LOGIN));
		mailLoginPage.typePasswordAndSubmit(prop.getProperty(PASSWORD));
		return mailLoginPage.isLogined();
    }
	
}
