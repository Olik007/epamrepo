package com.epam.lab.pages;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.lab.controls.Button;
import com.epam.lab.controls.CustomFieldDecorator;
import com.epam.lab.controls.InputArea;
import com.epam.lab.utils.Driver;

public class HomePage {	
	private static final Logger logger = Logger.getLogger(HomePage.class);
	
	@SuppressWarnings("unused")
	private HomePage() {}
		
	public HomePage(String driverName) throws IOException {
		PageFactory.initElements(new CustomFieldDecorator(getDriver(driverName)), this);
	}
	
	private WebDriver getDriver(String driverName) throws IOException{
		return Driver.getDriver(driverName);
	}

	@FindBy(xpath = "//div[@gh='cm']")
	private Button createButton;

	@FindBy(xpath = "//textarea[@name='to']")
	private InputArea emailTo;

	@FindBy(name = "subjectbox")
	private InputArea subjectName;

	@FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
	private InputArea text;

	@FindBy(css = "div[aria-label*='Enter'][role='button']")
	private Button sendButton;

	@FindBy(id = "gbqfq")
	private InputArea searchArea;
	
	@FindBy(xpath = "//table/tbody/tr[@class='zA yO']")
	private List<WebElement> sendMessagesList;

	@FindBy(xpath = "//div[@role='alertdialog']/div/button[@name='ok']")
	private Button okButton;
	
	@FindBy(xpath = "//div[@act='10']")
	private List<Button> deleteButton;

	public void sendEmail(String sendTo, String subject, String text) {
		logger.info("creation and sending message");
		createButton.click();
		emailTo.sendKeys(sendTo);
		this.subjectName.sendKeys(subject);
		this.text.sendKeys(text);
		sendButton.click();
	}

	public void checkSentAndDelete(String email, String subject) {
		searchArea.sendKeys("in:sent" + Keys.RETURN);
		// check each message for EMAIL and SUBJECT equals
		for (WebElement message : sendMessagesList) {
			String messageSendTo = message.findElement(By.xpath(".//td[@class='yX xY ']/div/span[@email]")).getAttribute("email");
			String messageSubject = message.findElement(By.xpath(".//td[@class='xY a4W']/div/div/div/span[@class='bog']")).getText();
				
			if (message.isDisplayed() && message.isEnabled() 
					&& email.equals(messageSendTo) 
					&& subject.equals(messageSubject)) {
				message.findElement(By.xpath(".//td/div[@role='checkbox']")).click();
				logger.info("mark checkbox with an equals message");
			}
		}
		
		for (Button button : deleteButton) {
			// click on available delete button
			if (button.isDisplayed()) {
				logger.info("press delete button");
				button.click();
			}			
		}		
		
		logger.info("click \"ok\" to confirm message(s) deletion");
		okButton.click();
		
	}

}
