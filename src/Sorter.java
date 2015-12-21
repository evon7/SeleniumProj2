package edu.ucsc.extension;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


public class Sorter {

	public void SortThisWay(Boolean cntFwd) {
		WebDriver driver = new FirefoxDriver();
		driver.get(
				"file://" + System.getProperty("user.dir") + File.separator + "html" + File.separator + "sorter.html");
		List<WebElement> slotList = new ArrayList<WebElement>();

		if (cntFwd) {
			slotList.add(driver.findElement(By.id("slot_1")));
			slotList.add(driver.findElement(By.id("slot_2")));
			slotList.add(driver.findElement(By.id("slot_3")));
			slotList.add(driver.findElement(By.id("slot_4")));
			slotList.add(driver.findElement(By.id("slot_5")));
			slotList.add(driver.findElement(By.id("slot_6")));
			slotList.add(driver.findElement(By.id("slot_7")));
			slotList.add(driver.findElement(By.id("slot_8")));
			slotList.add(driver.findElement(By.id("slot_9")));
			slotList.add(driver.findElement(By.id("slot_10")));
		} else {
			slotList.add(driver.findElement(By.id("slot_10")));
			slotList.add(driver.findElement(By.id("slot_9")));
			slotList.add(driver.findElement(By.id("slot_8")));
			slotList.add(driver.findElement(By.id("slot_7")));
			slotList.add(driver.findElement(By.id("slot_6")));
			slotList.add(driver.findElement(By.id("slot_5")));
			slotList.add(driver.findElement(By.id("slot_4")));
			slotList.add(driver.findElement(By.id("slot_3")));
			slotList.add(driver.findElement(By.id("slot_2")));
			slotList.add(driver.findElement(By.id("slot_1")));
		}
		int[] destX;
		int[] destY;
		destX = new int[100];
		destY = new int[100];

		//record location of each slot in ascending order
		int i = 1;
		for (WebElement slot : slotList) {
			destX[i] = slot.getLocation().x;
			destY[i] = slot.getLocation().y;
			//System.out.println(destX[i] + "   " + destY[i]);
			i++;
		}

		//cycle through each box in the list. find the box with 1 first, and then move it
		//to slot1. then find box with 2, move it to slot2. so on and so forth till all 10 is done
		int curY, j;
		if (cntFwd){
			System.out.println("*** Sorting in Ascending Order ***");
			for (j = 1; j <= 10; j++) {
				for (WebElement slot : slotList) {
					if (j == Integer.parseInt(slot.getText())) {
						System.out.println("match!!");
						curY = slot.getLocation().y;
						System.out.print(
								"block " + j + " goes to " + "slot " + j + ":(" + +destX[j] + ", " + destY[j] + ")\n");
						(new Actions(driver)).dragAndDropBy(slot, 0, (destY[j]) - curY).perform();
												//offset Y = the delta between the current object and the destination
												//i.e. specify moving by how many pixels
						break;
					}
				}
			}
		}else{
			//same methodology, but starting with box 10 first, move it to slot 1, then box9, move to slot2.
			System.out.println("*** Sorting in Reverse Order ***");
			for (j=10; j >=1; j--){
				for (WebElement slot : slotList) {
					if (j == Integer.parseInt(slot.getText())) {
						System.out.println("match!!");
						curY = slot.getLocation().y;
						System.out.print(
								"block " + j + " goes to " + "slot " + j + ":(" + +destX[j] + ", " + destY[j] + ")\n");
						(new Actions(driver)).dragAndDropBy(slot, 0, (destY[j]) - curY).perform();

						break;
					}
				}
			}
	
		}
		//Util.wait(5);
		// driver.quit();
	}

	@Test
	public void sortNumbers() {
		SortThisWay(true);
		SortThisWay(false);

	}
}
