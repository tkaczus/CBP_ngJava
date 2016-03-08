package com.orasi.utils;

import com.orasi.utils.date.SimpleDate;
import org.testng.Assert;
import org.testng.Reporter;

public class TestReporter {
    private static boolean printToConsole = false;
    
    private static String getTimestamp(){
   	return SimpleDate.getTimestamp().toString() + " :: ";
    }
    
    private static String trimHtml(String log){
	return log.replaceAll("<[^>]*>", "");
    }
    
    public static void setPrintToConsole(boolean printToConsole){
	TestReporter.printToConsole = printToConsole;
    }
    
    public static boolean getPrintToConsole(){
	return printToConsole;
    }
    
    public static void logStep(String step) {
	Reporter.log("<br/><b><font size = 4>Step: " + step
		+ "</font></b><br/>");
	if(getPrintToConsole()) System.out.println(step);
    }

    /*
     * public static void logScenario(){
     * Reporter.log("<br/><b><font size = 4>Data Scenario: " +
     * Datatable.getCurrentScenario()+ "</font></b><br/>"); }
     */

    public static void logScenario(String scenario) {
	Reporter.log("<br/><b><font size = 4>Data Scenario: " + scenario
		+ "</font></b><br/>");
	if(getPrintToConsole()) System.out.println(getTimestamp() + trimHtml(scenario));
    }

    public static void interfaceLog(String message) {
	Reporter.log(getTimestamp() + message + "<br />");
	if(getPrintToConsole()) System.out.println(getTimestamp() + trimHtml(message.trim()));
    }
    
    public static void interfaceLog(String message, boolean failed) {
	Reporter.log(getTimestamp() + "<font size = 2 color=\"red\">" + message + "</font><br />");
	if(getPrintToConsole()) System.out.println(getTimestamp() + trimHtml(message.trim()));
    }

    public static void log(String message) {
	Reporter.log(getTimestamp() + " <i><b>" + message + "</b></i><br />");
	if(getPrintToConsole()) System.out.println(getTimestamp() + trimHtml(message));
    }

    public static void assertTrue(boolean condition, String description) {	
	try{
	    Assert.assertTrue(condition, description);
	}catch (AssertionError failure){
	    Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u>Assert True - " + description + "</font></u></b><br />");
	    if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert True Failed- " + trimHtml(description));
	    Assert.fail(description);
	}
	Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert True - " + description + "</font></u></b><br />");
	if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert True - " + trimHtml(description));
    }
    
    public static void assertFalse(boolean condition, String description) {	
   	try{
   	    Assert.assertFalse(condition, description);
   	}catch (AssertionError failure){
   	    Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u>Assert False - " + description + "</font></u></b><br />");
   	 if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert False Failed- " + trimHtml(description));
   	    Assert.fail(description);
   	}
   	Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert False - " + description + "</font></u></b><br />");
   	if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert False - " + trimHtml(description));
       }
    
    public static void assertEquals(boolean condition, String description) {
   	try{
   	    Assert.assertEquals(condition, description);
   	}catch (AssertionError failure){
   	    Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u>Assert Equals - " + description + "</font></u></b><br />");
   	 if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Equals Failed- " + trimHtml(description));
   	    Assert.fail(description);
   	}
   	Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Equals - " + description + "</font></u></b><br />");
   	if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Equals - " + trimHtml(description));
       }
    
    public static void assertNotEquals(boolean condition, String description) {
   	try{
   	    Assert.assertNotEquals(condition, description);
   	}catch (AssertionError failure){
   	    Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u>Assert Not Equals - " + description + "</font></u></b><br />");
   	 if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Not Equals Failed- " + trimHtml(description));
   	    Assert.fail(description);
   	}
   	Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Not Equals - " + description + "</font></u></b><br />");
   	if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Not Equals - " + trimHtml(description));
       }
    
    public static void assertGreaterThanZero(int value) {
   	try{
   	    Assert.assertTrue(value > 0);
   	}catch (AssertionError failure){
   	    Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u>Assert Greater Than Zero - Assert " + value + " is greater than zero</font></u></b><br />");
   	 if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Greater Than Zero Failed- Assert " + value + " is greater than zero");
   	    Assert.fail("Assert " + value + " is greater than zero");
   	}
   	Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Greater Than Zero - Assert " + value + " is greater than zero</font></u></b><br />");
   	if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Greater Than Zero - Assert " + value + " is greater than zero");
       }
    
    public static void assertGreaterThanZero(float value) {
   	try{
   	    Assert.assertTrue(value > 0);
   	}catch (AssertionError failure){
   	    Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u>Assert Greater Than Zero - Assert " + value + " is greater than zero</font></u></b><br />");
   	 if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Greater Than Zero Failed- Assert " + value + " is greater than zero");
   	    Assert.fail("Assert " + value + " is greater than zero");
   	}
   	Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Greater Than Zero - Assert " + value + " is greater than zero</font></u></b><br />");
   	if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Greater Than Zero - Assert " + value + " is greater than zero");
       }
    
    public static void assertGreaterThanZero(double value) {
   	try{
   	    Assert.assertTrue(value > 0);
   	}catch (AssertionError failure){
   	    Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u>Assert Greater Than Zero - Assert " + value + " is greater than zero</font></u></b><br />");
   	 if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Greater Than Zero Failed- Assert " + value + " is greater than zero");
   	    Assert.fail("Assert " + value + " is greater than zero");
   	}
   	Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Greater Than Zero - Assert " + value + " is greater than zero</font></u></b><br />");
   	if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Greater Than Zero - Assert " + value + " is greater than zero");
       }
    
    public static void assertNull(boolean condition, String description) {
   	try{
   	    Assert.assertNull(condition, description);
   	}catch (AssertionError failure){
   	    Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u>Assert Null - " + description + "</font></u></b><br />");
   	 if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Null Failed- " + trimHtml(description));
   	    Assert.fail(description);
   	}
   	Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Null - " + description + "</font></u></b><br />");
   	if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Null - " + trimHtml(description));
       }
    
    public static void assertNotNull(boolean condition, String description) {
   	try{
   	    Assert.assertNotNull(condition, description);
   	}catch (AssertionError failure){
   	    Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u>Assert Not Null - " + description + "</font></u></b><br />");
   	 if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Not Null Failed- " + trimHtml(description));
   	    Assert.fail(description);
   	}
   	Reporter.log(getTimestamp() + "<font size = 2 color=\"green\"><b><u>Assert Not Null - " + description + "</font></u></b><br />");
   	if(getPrintToConsole()) System.out.println(getTimestamp() + "Assert Not Null - " + trimHtml(description));
       }
}
