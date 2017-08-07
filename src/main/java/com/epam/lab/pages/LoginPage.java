package com.epam.lab.pages;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.lab.controls.Button;
import com.epam.lab.controls.CustomFieldDecorator;
import com.epam.lab.controls.InputArea;
import com.epam.lab.utils.Driver;

public class LoginPage {
	private static final Logger logger = Logger.getLogger(LoginPage.class);
	
	@SuppressWarnings("unused")
	private LoginPage(){}
	
	public LoginPage(String driverName) throws IOException {
		PageFactory.initElements(new CustomFieldDecorator(getDriver(driverName)), this);
	}
	
	private WebDriver getDriver(String driverName) throws IOException{
		return Driver.getDriver(driverName);
	}

	@FindBy(name = "identifier")
	private InputArea loginInput;

	@FindBy(name = "password")
	private InputArea passwordInput;
	
	@FindBy(xpath = "//div[@gh='cm']")
	private Button createButton;

	public void typeLoginAndSubmit(String login) {
		logger.info("enter and submit user login");
		loginInput.sendKeys(login+Keys.RETURN);		
	}

	public void typePasswordAndSubmit(String pass) {
		logger.info("enter and submit user password");
		passwordInput.sendKeys(pass + Keys.RETURN);		
	}
	
	public boolean isLogined(){	
		return createButton.isEnabled();
	}

}
