package mscwork;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import mscwork.db.DBControl;
import mscwork.elementsAndScores.NextButtonField;
import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.SubmitButtonField;
import mscwork.elementsAndScores.UserNameField;

public class MainProgram {	
	
	private WebPage page;
	private boolean failed = false;
	
	private UserNameField userNameField;
	private PasswordField passwordField;
	private NextButtonField nextButtonField;
	private SubmitButtonField submitButtonField;
	
	
	@After
	public void closePage(){
		if (userNameField!=null)
			DBControl.modifyJSONWithNewScores(UserNameField.filepath, userNameField.getGotScoreFrom(), failed);
		if (passwordField!=null)
			DBControl.modifyJSONWithNewScores(PasswordField.filepath, passwordField.getGotScoreFrom(), failed);
		if (submitButtonField!=null)
			DBControl.modifyJSONWithNewScores(SubmitButtonField.filepath, submitButtonField.getGotScoreFrom(), failed);
		if (nextButtonField!=null)
			DBControl.modifyJSONWithNewScores(NextButtonField.filepath, nextButtonField.getGotScoreFrom(), failed);
		if (page != null)
			page.close();
	}
	@Test
	public void wiki(){
		String url = "https://en.wikipedia.org/w/index.php?title=Special:UserLogin&returnto=Main+Page";
		String locatorString = "//div/p[contains(text(),'Incorrect password entered.')]";
		String username = "username";
		String password = "password";
		WebPage page = new WebPageObject(url, By.xpath(locatorString));
		login(page, username, password);
	}
	
	@Test
	public void salesForce(){
		String url = "https://login.salesforce.com/";
		String locatorString = "//div[contains(text(),'Please check your username and password.')]";
		String username = "username";
		String password = "password";
		WebPage page = new WebPageObject(url, By.xpath(locatorString));
		login(page, username, password);
	}
	
	@Test
	public void marketo(){
		String url = "https://login.marketo.com/";
		String locatorString = "//div[contains(text(),'Incorrect email or password. Try again.')]";
		String username = "username";
		String password = "password";
		WebPage page = new WebPageObject(url, By.xpath(locatorString));
		login(page, username, password);
	}
	
	
	private void login(WebPage page, String username, String password){
		this.page = page;
		
		page.init();
		page.waitForPageToLoad();
		page.prepareForLogin();
		page.waitForPageToLoad();
		
		List<PasswordField> passwords = AlgoritamWithoutMachineLearning.getPasswordFields(page);
		AlgoritamWithoutMachineLearning.sortPasswordList(passwords);
		
		List<UserNameField> usernames = AlgoritamWithoutMachineLearning.getUsernameFields(page);
		AlgoritamWithoutMachineLearning.sortUserNameList(usernames);
		
		List<SubmitButtonField> submitButtons = AlgoritamWithoutMachineLearning.getSubmitButtons(page);
		AlgoritamWithoutMachineLearning.sortSubmitButtonList(submitButtons);
		
		List<NextButtonField> nextButtons = AlgoritamWithoutMachineLearning.getNextButtons(page);
		AlgoritamWithoutMachineLearning.sortNextButtonList(nextButtons);
		
		
		
		if(nextButtons.get(0).getScore() >= 20){
			userNameField = usernames.get(0);
			userNameField.getField().sendKeys(username);
			
			nextButtonField = nextButtons.get(0);
			nextButtonField.getField().click();
			
			page.waitForPageToLoad();
			
			passwords = AlgoritamWithoutMachineLearning.getPasswordFields(page);
			AlgoritamWithoutMachineLearning.sortPasswordList(passwords);
			submitButtons = AlgoritamWithoutMachineLearning.getSubmitButtons(page);
			AlgoritamWithoutMachineLearning.sortSubmitButtonList(submitButtons);
			
			passwordField = passwords.get(0);
			passwordField.getField().sendKeys(password);
			
			submitButtonField = submitButtons.get(0);
			submitButtonField.getField().click();
			
		} else {
			userNameField = usernames.get(0);
			userNameField.getField().sendKeys(username);
			
			passwordField = passwords.get(0);
			passwordField.getField().sendKeys(password);
			
			submitButtonField = submitButtons.get(0);
			submitButtonField.getField().click();
		}
		page.waitForPageToLoad();
		if (!page.isLoginSuccessed()){
			failed = true;
			Assert.fail("The login failed");
		}
	}
}
