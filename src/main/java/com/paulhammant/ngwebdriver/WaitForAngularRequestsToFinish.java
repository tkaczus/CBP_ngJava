package com.paulhammant.ngwebdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WaitForAngularRequestsToFinish extends AngularJavaScriptFunctions{

    public static void waitForAngularRequestsToFinish(JavascriptExecutor driver) {
        driver.executeAsyncScript("var callback = arguments[arguments.length - 1];\n" +
                "var rootSelector = 'body';\n" +
                "\n" +
                ByAngular.functions.get("waitForAngular"));
    }
    
	public static void waitForAngularRequestsToFinish(WebDriver driver) {
		driver.manage().timeouts().setScriptTimeout(600, TimeUnit.SECONDS);
		WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish((JavascriptExecutor) driver);
	}
}
