package mscwork;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import mscwork.attributeScore.AttributeWordAndScoreMultiplier;
import mscwork.db.DBControl;
import mscwork.elementsAndScores.NextButtonField;
import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.SubmitButtonField;
import mscwork.elementsAndScores.UserNameField;

public class MainProgram {	
	
	private WebPage page;
	private boolean failed = true;
	
	private UserNameField userNameField;
	private PasswordField passwordField;
	private NextButtonField nextButtonField;
	private SubmitButtonField submitButtonField;
	
	
	@After
	public void after(){
		if (userNameField!=null){
			List<AttributeWordAndScoreMultiplier> list = userNameField.getGotScoreFrom();
			modifyScrore(list, failed);
			DBControl.modifyJSONWithNewScores(UserNameField.filepath, list);
		}
		if (passwordField!=null){
			List<AttributeWordAndScoreMultiplier> list = passwordField.getGotScoreFrom();
			modifyScrore(list, failed);
			DBControl.modifyJSONWithNewScores(PasswordField.filepath, list);
		}
		if (submitButtonField!=null){
			List<AttributeWordAndScoreMultiplier> list = submitButtonField.getGotScoreFrom();
			modifyScrore(list, failed);
			DBControl.modifyJSONWithNewScores(SubmitButtonField.filepath, list);
		}
		if (nextButtonField!=null){
			List<AttributeWordAndScoreMultiplier> list = nextButtonField.getGotScoreFrom();
			modifyScrore(list, failed);
			DBControl.modifyJSONWithNewScores(NextButtonField.filepath, list);
		}
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
		
		List<PasswordField> passwords = AlgorithmWithoutMachineLearning.getPasswordFields(page);
		Collections.sort(passwords);
		
		List<UserNameField> usernames = AlgorithmWithoutMachineLearning.getUsernameFields(page);
		Collections.sort(usernames);
		
		List<SubmitButtonField> submitButtons = AlgorithmWithoutMachineLearning.getSubmitButtons(page);
		Collections.sort(submitButtons);
		
		List<NextButtonField> nextButtons = AlgorithmWithoutMachineLearning.getNextButtons(page);
		Collections.sort(nextButtons);
		
		
		
		if(nextButtons.size()>0 && nextButtons.get(0).getScore() >= 20){
			userNameField = usernames.get(0);
			userNameField.getField().sendKeys(username);
			
			nextButtonField = nextButtons.get(0);
			nextButtonField.getField().click();
			
			page.waitForPageToLoad();
			
			passwords = AlgorithmWithoutMachineLearning.getPasswordFields(page);
			Collections.sort(passwords);
			submitButtons = AlgorithmWithoutMachineLearning.getSubmitButtons(page);
			Collections.sort(submitButtons);
			
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
		if (page.isLoginSuccessed()){
			failed = false;
		} else {
			failed = true;
			Assert.fail("The login failed");
		}
	}
}
