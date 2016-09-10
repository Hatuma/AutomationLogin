package mscwork;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface WebPage {

	public List<WebElement> getAllInputFieldNotButton();
	public List<WebElement> getAllInputField();
	public List<WebElement> getAllButtons();
	public List<WebElement> getAllElementWithText(String text);
	public void close();
	public void init();
	public void prepareForLogin();
	public void waitForPageToLoad();
	public boolean isLoginSuccessed();
}
