package mscwork;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.SubmitButtonField;
import mscwork.elementsAndScores.UserNameField;

public class MainProgram {	
	
	public void scoreUsernameFields(List<WebElement> list){
		for (WebElement element: list){
			if (element.getAttribute("name").equals("email"))
				System.out.println("found!");
			
		}
	}
	
	
	private void login(String url, String username, String password){
		WebPageObject page = new WebPageObject(url);
		
		List<PasswordField> passwords = AlgoritamWithoutMachineLearning.getPasswordFields(page);
		AlgoritamWithoutMachineLearning.sortPasswordList(passwords);
		
		List<UserNameField> usernames = AlgoritamWithoutMachineLearning.getUsernameFields(page);
		AlgoritamWithoutMachineLearning.sortUserNameList(usernames);
		
		List<SubmitButtonField> submitButtons = AlgoritamWithoutMachineLearning.getSubmitButtons(page);
		AlgoritamWithoutMachineLearning.sortSubmitButtonList(submitButtons);
		
		
		usernames.get(0).getField().sendKeys(username);
		passwords.get(0).getField().sendKeys(password);
		submitButtons.get(0).getField().click();
		
		page.close();
	}
}
