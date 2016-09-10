package mscwork;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebPageObject implements WebPage{

	protected WebDriver driver;
	protected String url;
	protected By locator;
	
	public WebPageObject(String url, By locator){
		this.url = url;
		this.locator = locator;
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

	public void init() {
		driver = new FirefoxDriver();
		driver.get(url);		
	}

	public void prepareForLogin() {
		
	}

	public void waitForPageToLoad() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isLoginSuccessed() {
		try{
			return driver.findElement(locator).isDisplayed(); 
		} catch (NoSuchElementException e){
			return false;
		}
	}
	
}
