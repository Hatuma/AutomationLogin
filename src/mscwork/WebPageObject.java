package mscwork;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebPageObject {

	public WebDriver driver;
	
	public WebPageObject(String url){
		driver = new FirefoxDriver();
		driver.get(url);
	}
	
	public List<WebElement> getAllInputFieldNotButton(){
		List <WebElement> list =  driver.findElements(By.cssSelector("input:not(.button)"));
		
		List <WebElement> list2 = new ArrayList<WebElement>();
		
		for (WebElement element: list){
			if (element.isDisplayed())
				list2.add(element);
		}
		
		return list2;
	}
	
	public List<WebElement> getAllInputField(){
		List <WebElement> list =  driver.findElements(By.cssSelector("input"));
		
		List <WebElement> list2 = new ArrayList<WebElement>();
		
		for (WebElement element: list){
			if (element.isDisplayed())
				list2.add(element);
		}
		
		return list2;
	}
	
	public List<WebElement> getAllButtons(){
		List <WebElement> list = new ArrayList<WebElement>();
		list.addAll(getAllInputField());
		list.addAll(driver.findElements(By.xpath("//button")));
		list.addAll(driver.findElements(By.xpath("//*[@type='submit']")));
		
		List <WebElement> list2 = new ArrayList<WebElement>();
		
		for (WebElement element: list){
			if (element.isDisplayed())
				list2.add(element);
		}
		
		return list2;
	}
	
	public List<WebElement> getAllElementWithText(String text){
		List <WebElement> list =  driver.findElements(By.xpath("//*[contains(text(), '" + text + "')]"));
		list.addAll(driver.findElements(By.xpath("//*[contains(@value, '" + text + "')]")));

		List <WebElement> list2 = new ArrayList<WebElement>();
		
		for (WebElement element: list){
			if (element.isDisplayed() && !element.getTagName().equals("input"))
				list2.add(element);
		}
		
		return list2;
	}
	
	public void close(){
		driver.close();
	}
	
}
