package mscwork;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
		list.addAll(driver.findElements(By.xpath("//*[@href]")));
		list.addAll(driver.findElements(By.xpath("//*[@onclick]")));
		list.addAll(driver.findElements(By.xpath("//img")));
		
		List <WebElement> list2 = new ArrayList<WebElement>();
		
		for (WebElement element: list){
			try{
			if (element.isDisplayed())
				list2.add(element);
			} catch (StaleElementReferenceException e){
				//do not add
			}
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
		driver.quit();
	}

	public void init() {
		//System.setProperty("webdriver.chrome.driver", "I:\\ChromeDriver\\chromedriver.exe");
		//driver = new ChromeDriver();
		DesiredCapabilities capability = DesiredCapabilities.chrome();
        try {
			driver = new RemoteWebDriver(new URL("http://localhost:4756/wd/hub"),  
			capability);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.manage().window().maximize();
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

	public WebDriver getDriver() {
		return driver;
	}
	
}
