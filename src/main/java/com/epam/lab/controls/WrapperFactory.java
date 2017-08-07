package com.epam.lab.controls;

import org.openqa.selenium.WebElement;

public class WrapperFactory {

	public CustomElement createInstance(Class<CustomElement> clazz, WebElement element) {
        //System.out.println(element.toString());
        try {
            return clazz.getConstructor(WebElement.class).newInstance(element);
        } catch (Exception e) {
            throw new AssertionError(
                    "Error while asserting element as " + clazz
            );
        }
    }
}
