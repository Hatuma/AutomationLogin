package mscwork;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import mscwork.attributeScore.AttributeWordAndScoreMultiplier;
import mscwork.db.DBControl;
import mscwork.db.FilePathEnum;
import mscwork.elementsAndScores.NextButtonField;
import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.SubmitButtonField;
import mscwork.elementsAndScores.UserNameField;

public class MainProgram {	
	
	private WebPageObject page;
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
	}
	
	
	private void login(String url, String username, String password, By expectedElementLocator){
		page = new WebPageObject(url);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		try{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement expectedElement = page.driver.findElement(expectedElementLocator);
			if (!expectedElement.isDisplayed()){
				failed = true;
				Assert.fail("The given element is invisible: " + expectedElement);
			}
			
			//succeed login
		} catch (NoSuchElementException e){
			// login failed
			failed = true;
			Assert.fail("No element with the given locator: " + expectedElementLocator);
		}
		
	}
}
