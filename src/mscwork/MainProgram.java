package mscwork;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import mscwork.elementsAndScores.NextButtonField;
import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.SubmitButtonField;
import mscwork.elementsAndScores.UserNameField;

public class MainProgram {	
	
	WebPageObject page;
	
	@After
	public void closePage(){
		page.close();
	}
	
	
	private void login(String url, String username, String password, By expectedElementLocator){
		page = new WebPageObject(url);
		
		List<PasswordField> passwords = AlgoritamWithoutMachineLearning.getPasswordFields(page);
		AlgoritamWithoutMachineLearning.sortPasswordList(passwords);
		
		List<UserNameField> usernames = AlgoritamWithoutMachineLearning.getUsernameFields(page);
		AlgoritamWithoutMachineLearning.sortUserNameList(usernames);
		
		List<SubmitButtonField> submitButtons = AlgoritamWithoutMachineLearning.getSubmitButtons(page);
		AlgoritamWithoutMachineLearning.sortSubmitButtonList(submitButtons);
		
		List<NextButtonField> nextButtons = AlgoritamWithoutMachineLearning.getNextButtons(page);
		AlgoritamWithoutMachineLearning.sortNextButtonList(nextButtons);
		
		if(nextButtons.get(0).getScore() > submitButtons.get(0).getScore()){
			usernames.get(0).getField().sendKeys(username);
			nextButtons.get(0).getField().click();
			
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
			
			passwords.get(0).getField().sendKeys(password);
			submitButtons.get(0).getField().click();
			
		} else {
			usernames.get(0).getField().sendKeys(username);
			passwords.get(0).getField().sendKeys(password);
			submitButtons.get(0).getField().click();
		}
		try{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement expectedElement = page.driver.findElement(expectedElementLocator);
			Assert.assertTrue("The given element is invisible", expectedElement.isDisplayed());
			//succed login
		} catch (NoSuchElementException e){
			// login faild
			Assert.fail("No element with the given locator: " + expectedElementLocator);
		}
		
	}
}
