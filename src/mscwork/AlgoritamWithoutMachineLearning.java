package mscwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import mscwork.elementsAndScores.FieldAndScore;
import mscwork.elementsAndScores.FieldAndScoreComparator;
import mscwork.elementsAndScores.NextButtonField;
import mscwork.elementsAndScores.PasswordField;
import mscwork.elementsAndScores.SubmitButtonField;
import mscwork.elementsAndScores.UserNameField;

public class AlgoritamWithoutMachineLearning {	

	public static List<PasswordField> getPasswordFields(WebPageObject page) {
		List<PasswordField> list = new ArrayList<PasswordField>();
		for (WebElement field : page.getAllInputFieldNotButton()) {
			list.add(new PasswordField(field, 0));
		}
		for (PasswordField passField : list) {
			for (String attribute : FieldAndScore.attributes) {
				for (String word : PasswordField.keyWords) {
					giveScore(passField, attribute, word, PasswordField.multipliers.get(attribute).get(word).getMultiplier());
					;
				}
			}
		}

		for (PasswordField passField : list) {
			for (String word : PasswordField.keyWords) {
				giveScoreFromDistance(page, passField, word);
			}
		}

		return list;
	}

	public static List<UserNameField> getUsernameFields(WebPageObject page) {
		List<UserNameField> list = new ArrayList<UserNameField>();
		for (WebElement field : page.getAllInputFieldNotButton()) {
			list.add(new UserNameField(field, 0));
		}
		for (UserNameField userNameField : list) {
			for (String attribute : FieldAndScore.attributes) {
				for (String word : UserNameField.keyWords) {
					giveScore(userNameField, attribute, word, UserNameField.multipliers.get(attribute).get(word).getMultiplier());
					;
				}
			}
		}

		for (UserNameField userNameField : list) {
			for (String word : UserNameField.keyWords) {
				giveScoreFromDistance(page, userNameField, word);
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
			for (String attribute : FieldAndScore.attributes) {
				for (String word : SubmitButtonField.keyWords) {
					giveScore(submitButtonField, attribute, word, SubmitButtonField.multipliers.get(attribute).get(word).getMultiplier());
				}
			}
			if (submitButtonField.getField().getTagName().equals("button"))
				submitButtonField.incScore();
			for (String word : SubmitButtonField.keyWords) {
				if(submitButtonField.getField().getText().toLowerCase().contains(word))
					submitButtonField.incScore(20);
			}
		}

		return list;
	}
	
	public static List<NextButtonField> getNextButtons(WebPageObject page) {
		List<NextButtonField> list = new ArrayList<NextButtonField>();
		for (WebElement field : page.getAllButtons()) {
			list.add(new NextButtonField(field, 0));
		}
		for (NextButtonField nextButtonField : list) {
			for (String attribute : FieldAndScore.attributes) {
				for (String word : NextButtonField.keyWords) {
					giveScore(nextButtonField, attribute, word, NextButtonField.multipliers.get(attribute).get(word).getMultiplier());
				}
			}
			if (nextButtonField.getField().getTagName().equals("button"))
				nextButtonField.incScore();
			for (String word : NextButtonField.keyWords) {
				if(nextButtonField.getField().getText().toLowerCase().contains(word))
					nextButtonField.incScore(20);
			}
		}

		return list;
	}

	private static void giveScore(FieldAndScore element, String attribute, String name, int multipier) {
		if (element.getField().getAttribute(attribute) != null
				&& element.getField().getAttribute(attribute).toLowerCase().contains(name))
			element.incScore(multipier);
	}

	private static void giveScoreFromDistance(WebPageObject page, FieldAndScore fieldAndScore, String word) {
		for (WebElement label : page.getAllElementWithText(word)) {
			// top left with top right
			int labelRightX = label.getLocation().getX() + label.getSize().getWidth();
			int labelTopY = label.getLocation().getY();
			int labelLeftX = label.getLocation().getX();
			int labelBotY = label.getLocation().getY() + label.getSize().getHeight();

			WebElement element = fieldAndScore.getField();
			int elementLeftX = element.getLocation().getX();
			int elementTopY = element.getLocation().getY();

			double distance = Math.min(
					Math.sqrt(Math.pow(elementLeftX - labelRightX, 2) + Math.pow(elementTopY - labelTopY, 2)),
					Math.sqrt(Math.pow(elementLeftX - labelLeftX, 2) + Math.pow(elementTopY - labelBotY, 2)));

			fieldAndScore.incScore((int) (100 / (distance + 1)));
		}
	}

	public static void sortPasswordList(List<PasswordField> list) {
		Collections.sort(list, new FieldAndScoreComparator());
	}

	public static void sortUserNameList(List<UserNameField> list) {
		Collections.sort(list, new FieldAndScoreComparator());
	}

	public static void sortSubmitButtonList(List<SubmitButtonField> list) {
		Collections.sort(list, new FieldAndScoreComparator());
	}
	
	public static void sortNextButtonList(List<NextButtonField> list) {
		Collections.sort(list, new FieldAndScoreComparator());
	}
}
