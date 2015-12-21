package edu.ucsc.extension;

import java.io.File;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.apache.commons.io.FileUtils;

public class Paint {
	@Test
	public void testPaint() {
		WebDriver driver = new FirefoxDriver();
		driver.get(
				"file://" + System.getProperty("user.dir") + File.separator + "html" + File.separator + "paint.html");

		// Your drawing code here
		Actions builder = new Actions(driver);
		//set the starting point of the triangle
		Action moveM = builder.moveByOffset(400, 400).click().build();
		moveM.perform();

		int i = 0;
		while (i < 60) {
			Action moveNClick = builder.moveByOffset(5, 0).click().build();
			moveNClick.perform();
			i++;
		}

		i = 0;
		while (i < 30) {
			Action moveNClick = builder.moveByOffset(-5, -5).click().build();
			moveNClick.perform();
			i++;
		}

		i = 0;
		while (i < 30) {
			Action moveNClick = builder.moveByOffset(-5, 5).click().build();
			moveNClick.perform();
			i++;
		}

		Util.wait(10);
		//driver.quit();
	}
}
