package edu.ucsc.extension;

import java.io.File;
import org.apache.commons.io.FileUtils;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Point;
//import com.sun.javafx.scene.paint.GradientUtils.Point;

public class RectanglesTest {

	@Test
	public void testIntersection() {
		int intersectCnt = 0;
		int k=0;
	
		String[] intersectMsg = new String[40];

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file://" + System.getProperty("user.dir") + File.separator + "html" + File.separator + "rect.html");
		Util.wait(1);

		// Please write your code here
		List<WebElement> rects = driver.findElements(By.className("rect"));
		
		for (int i=0; i<rects.size(); i++){
			
			WebElement curRect = rects.get(i);
			int curX = curRect.getLocation().getX();
			int curY = curRect.getLocation().getY();
			int curW = curRect.getSize().getWidth();
			int curH = curRect.getSize().getHeight();
			
			System.out.printf("%-20s	X   \tY   \tW   \tH   \tX+W   \tY+H\n", "X    Y");
			System.out.print(i + " " + rects.get(i).getLocation() );
			System.out.print("\t\t" + curX);
			System.out.print("\t" + curY);
			System.out.print("\t" + curW);			
			System.out.print("\t" + curH);			
			System.out.print("\t" + (curX+curW));			
			System.out.print("\t" + (curY+curH) + "<=== CurRect\n");
		
			//compare curRect with rest of the rects
			for (int j=0; j<rects.size(); j++){
				if (i==j){
					System.out.println("Comparing the same rectangle. Skip to next");
					continue;
				}
				
				if (j < i){
					System.out.printf("Comparision of Rect[%d] and Rect[%d] had been done before. Skip to next\n", i, j);
					continue;
				}
				WebElement otherRect = rects.get(j);
				int otherX = otherRect.getLocation().getX();
				int otherY = otherRect.getLocation().getY();
				int otherW = otherRect.getSize().getWidth();
				int otherH = otherRect.getSize().getHeight();

				//System.out.println("(X, Y \t X   \tY   \tWidth   \tHeight   \tX_W   \tY+H");
				System.out.print(j + " " + otherRect.getLocation() );
				System.out.print("\t" + otherX);
				System.out.print("\t" + otherY);
				System.out.print("\t" + otherW);			
				System.out.print("\t" + otherH);			
				System.out.print("\t" + (otherX+otherW));			
				System.out.print("\t" + (otherY+otherH) + "\n");
									
				if (((otherX >= curX) && (otherX <= (curX+curW))) ||
					((otherX+otherW) >= curX) && ((otherX+otherW) <= (curX+curW))){
						
					if(((otherY >= curY) && (otherY <= (curY+curH)) ) ||
					 ((otherY+otherH) >= curY) && ((otherY+otherH) <= (curY+curH))){
						intersectCnt++ ;
						System.out.printf("Rect[%d] and Rect[%d] Intersect\n", i,j);
						intersectMsg[k] = "Rect["+ i +"] and Rect["+j+"] Intersect";
						k++;
					}
				}
			}
			System.out.println("------------------------------------------------");
		}
	
		if (intersectCnt > 0){
			System.out.printf("*** %d Intercept Found *** \n", intersectCnt);
			for (int m=0; m<intersectCnt; m++)
				System.out.println(intersectMsg[m]);
		}
		else
			System.out.println("NO Intercept");
		
		//Util.wait(1);
		//driver.quit();
	}
}
