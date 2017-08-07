package com.epam.lab.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

	private Properties prop;

	private FileInputStream getStream(String fileName) {
		File file = new File(System.getProperty("user.dir") + "/src/main/resources/" + fileName);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileInput;
	}
	
	public Properties getProperties(String fileName) throws IOException{
		prop = new Properties();
		prop.load(getStream(fileName));
		return prop;
	}
	
}
