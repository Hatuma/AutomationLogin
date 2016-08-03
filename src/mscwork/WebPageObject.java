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
	
	public List<WebElement> getAllInputField(){
		return driver.findElements(By.cssSelector("input"));
	}
	
	public List<WebElement> getAllButtons(){
		List <WebElement> list = new ArrayList<WebElement>();
		list.addAll(driver.findElements(By.xpath("//*")));
		return list;
	}
	
	public void close(){
		driver.close();
	}
	
}
