package org.openqa.mytest;

import java.util.List;
import java.io.File;
import java.lang.*;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.*;

import org.apache.commons.io.FileUtils;

public class Example {
    public static void main(String[] args) throws Exception {
        // The Firefox driver supports javascript 
    FirefoxProfile profile = new FirefoxProfile();
    profile.setEnableNativeEvents(true);
        WebDriver driver = new FirefoxDriver(profile);

        // Go to the Google Suggest home page
        driver.get("http://www.wired.com");

    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // now save the screenshto to a file some place
        FileUtils.copyFile(scrFile, new File("./screenshot.png"));


    Actions builder = new Actions(driver);
    Action moveM = builder.moveByOffset(40, 40).build();
    moveM.perform();

    Action click = builder.click().build();
    click.perform();
    //click.release();

    Action moveM2 = builder.moveByOffset(50, 50).build();
    moveM2.perform();

    Action click2 = builder.click().build();
    click2.perform();
    //click2.release();

    Action moveM3 = builder.moveByOffset(150, 540).build();
    moveM3.perform();

    for( int i=0; i < 1000; i++)
    {
        moveM = builder.moveByOffset(200, 200).build();
        moveM.perform();
        Thread.sleep(500);
        moveM = builder.moveByOffset(-200, -200).build();
        moveM.perform();
        Thread.sleep(500);
    }
    //Action click3 = builder.click().build();
    //click3.perform();
    //click3.release();

    scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // now save the screenshto to a file some place
        FileUtils.copyFile(scrFile, new File("./screenshot2.png"));

        driver.quit();
    }
}
