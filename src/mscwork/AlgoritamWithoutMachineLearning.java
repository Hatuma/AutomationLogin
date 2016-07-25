package mscwork;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.UserNameField;

public class AlgoritamWithoutMachineLearning {

	public static List<PasswordField> getPasswordFields(WebPageObject page){
		List<PasswordField> list = new ArrayList<PasswordField>();
		for (WebElement field: page.getAllInputField()){
			list.add(new PasswordField(field, 0));
		}
		for (PasswordField passField: list){
			if (passField.getField().getAttribute("name").contains("pass"))
				passField.setScore(passField.getScore() + 1);
			if (passField.getField().getAttribute("class").contains("pass"))
				passField.setScore(passField.getScore() + 1);
			if (passField.getField().getAttribute("id").contains("pass"))
				passField.setScore(passField.getScore() + 1);
		}
		return list;
	}
	
	public static List<UserNameField> getUsernameFields(WebPageObject page){
		List<UserNameField> list = new ArrayList<UserNameField>();
		for (WebElement field: page.getAllInputField()){
			list.add(new UserNameField(field, 0));
		}
		for (UserNameField userNameField: list){
			if (userNameField.getField().getAttribute("name").contains("username"))
				userNameField.setScore(userNameField.getScore() + 1);
			if (userNameField.getField().getAttribute("class").contains("username"))
				userNameField.setScore(userNameField.getScore() + 1);
			if (userNameField.getField().getAttribute("id").contains("username"))
				userNameField.setScore(userNameField.getScore() + 1);
			if (userNameField.getField().getAttribute("name").contains("email"))
				userNameField.setScore(userNameField.getScore() + 1);
			if (userNameField.getField().getAttribute("class").contains("email"))
				userNameField.setScore(userNameField.getScore() + 1);
			if (userNameField.getField().getAttribute("id").contains("email"))
				userNameField.setScore(userNameField.getScore() + 1);
		}
		return list;
	}
}
