package mscwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import mscwork.elementsAndScores.FieldAndScore;
import mscwork.elementsAndScores.FieldAndScoreComparator;
import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.SubmitButtonField;
import mscwork.elementsAndScores.UserNameField;

public class AlgoritamWithoutMachineLearning {

	private static List<String> attributes = Arrays.asList("id", "class", "name", "text", "value", "type");

	private static List<String> userNameKeyWords = Arrays.asList("username", "email", "user", "account");
	private static List<String> passwordKeyWords = Arrays.asList("pass", "password");
	private static List<String> submitKeyWords = Arrays.asList("login", "submit", "bejelentkezés", "bejelentkezes",
			"signin", "sign in");

	public static List<PasswordField> getPasswordFields(WebPageObject page){
		List<PasswordField> list = new ArrayList<PasswordField>();
		for (WebElement field: page.getAllInputField()){
			list.add(new PasswordField(field, 0));
		}
		for (PasswordField passField: list){
			for (String attribute: attributes){
				for (String word: passwordKeyWords){
					giveScore(passField, attribute, word);;
				}
			}
		}
		return list;
	}

	public static List<UserNameField> getUsernameFields(WebPageObject page) {
		List<UserNameField> list = new ArrayList<UserNameField>();
		for (WebElement field : page.getAllInputField()) {
			list.add(new UserNameField(field, 0));
		}
		for (UserNameField userNameField : list) {
			for (String attribute: attributes){
				for (String word: userNameKeyWords){
					giveScore(userNameField, attribute, word);;
				}
			}
		}
		return list;
	}

	public static List<SubmitButtonField> getSubmitButtons(WebPageObject page) {
		List<SubmitButtonField> list = new ArrayList<SubmitButtonField>();
		for (WebElement field : page.getAllButtons()) {
			list.add(new SubmitButtonField(field, 0));
		}
		for (SubmitButtonField submitButtonField : list) {
			for (String attribute: attributes){
				for (String word: submitKeyWords){
					giveScore(submitButtonField, attribute, word);;
				}
			}

		}
		return list;
	}

	private static void giveScore(FieldAndScore element, String attribute, String name) {
		if (element.getField().getAttribute(attribute) != null
				&& element.getField().getAttribute(attribute).toLowerCase().contains(name))
			element.incScore();
	}

	public static void sortPasswordList(List<PasswordField> list) {
		Collections.sort(list, new FieldAndScoreComparator());
	}

	public static void sortUserNameList(List<UserNameField> list) {
		Collections.sort(list, new FieldAndScoreComparator());
	}
	
	public static void sortSubmitButtonList(List<SubmitButtonField> list){
		Collections.sort(list, new FieldAndScoreComparator());
	}
}
