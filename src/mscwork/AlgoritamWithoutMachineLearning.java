package mscwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import mscwork.elementsAndScores.FieldAndScoreComparator;
import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.SubmitButtonField;
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
	
	public static List<SubmitButtonField> getSubmitButtons(WebPageObject page){
		List<SubmitButtonField> list = new ArrayList<SubmitButtonField>();
		for (WebElement field: page.getAllButtons()){
			list.add(new SubmitButtonField(field, 0));
		}
		for (SubmitButtonField SubmitButtonField: list){
			WebElement field = SubmitButtonField.getField();
			if (field.getAttribute("name") != null && field.getAttribute("name").contains("submit"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			if (field.getAttribute("class") != null && field.getAttribute("class").contains("submit"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			if (field.getAttribute("id") != null && field.getAttribute("id").contains("submit"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			if (field.getText().contains("submit"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			if (field.getAttribute("type") != null && field.getAttribute("type").contains("submit"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			if (field.getAttribute("name") != null && field.getAttribute("name").contains("login"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			if (field.getAttribute("class") != null && field.getAttribute("class").contains("login"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			if (field.getAttribute("id") != null && field.getAttribute("id").contains("login"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			if (field.getText().contains("login"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			if (field.getAttribute("type") != null && field.getAttribute("type").contains("login"))
				SubmitButtonField.setScore(SubmitButtonField.getScore() + 1);
			
		}
		return list;
	}
	
	public static void sortPasswordList(List<PasswordField> list){
		Collections.sort(list, new FieldAndScoreComparator());
	}
	
	public static void sortUserNameList(List<UserNameField> list){
		Collections.sort(list, new FieldAndScoreComparator());
	}
	
	public static void sortSubmitButtonList(List<SubmitButtonField> list){
		Collections.sort(list, new FieldAndScoreComparator());
	}
}
