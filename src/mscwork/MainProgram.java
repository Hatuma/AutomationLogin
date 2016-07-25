package mscwork;

import java.util.List;

import org.openqa.selenium.WebElement;

import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.UserNameField;

public class MainProgram {	
	
	public void scoreUsernameFields(List<WebElement> list){
		for (WebElement element: list){
			if (element.getAttribute("name").equals("email"))
				System.out.println("found!");
			
		}
	}
	
	public static void main(String[] args){
		WebPageObject page = new WebPageObject("https:\\www.facebook.com");
		for (PasswordField pass:AlgoritamWithoutMachineLearning.getPasswordFields(page))
			System.out.println(pass.toString());
		for (UserNameField username: AlgoritamWithoutMachineLearning.getUsernameFields(page))
			System.out.println(username.toString());
		page.close();
	}
}
