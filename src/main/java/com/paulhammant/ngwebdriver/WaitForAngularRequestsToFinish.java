package com.paulhammant.ngwebdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForAngularRequestsToFinish extends AngularJavaScriptFunctions{

    public static void waitForAngularRequestsToFinish(JavascriptExecutor driver) {
        driver.executeAsyncScript("var callback = arguments[arguments.length - 1];\n" +
                "var rootSelector = 'body';\n" +
                "\n" +
                ByAngular.functions.get("waitForAngular"));
    }
    
	public static void waitForAngularRequestsToFinish(WebDriver driver) {
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish((JavascriptExecutor) driver);
	}
}
