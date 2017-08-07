package com.epam.lab.controls;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LocatingCustomElementListHandler implements InvocationHandler {

	private final ElementLocator locator;
    private final Class<CustomElement> clazz;

    public LocatingCustomElementListHandler(ElementLocator locator, Class<CustomElement> clazz) {
        this.locator = locator;
        this.clazz = clazz;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        //System.out.println(method.getName());
        List<WebElement> elements = locator.findElements();
        List<CustomElement> customs = new ArrayList<CustomElement>();

        for (WebElement element : elements) {
            customs.add(new WrapperFactory().createInstance(clazz, element));
        }
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
