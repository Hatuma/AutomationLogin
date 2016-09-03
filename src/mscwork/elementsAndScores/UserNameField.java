package mscwork.elementsAndScores;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import mscwork.attributeScore.AttributeWordAndScoreMultiplier;
import mscwork.db.DBControl;
import mscwork.db.FilePathEnum;

public class UserNameField extends FieldAndScore{
	
	public static FilePathEnum filepath = FilePathEnum.USERNAME;
	
	
	public static Map<String, Map<String, AttributeWordAndScoreMultiplier>> multipliers;
	public static List<String> keyWords;
	
	public UserNameField(WebElement field, int score) {
		super(field, score);
	}
	
	public UserNameField() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserNameField [field=" + getField() + ", score=" + getScore() + "]";
	}

	static{
		keyWords = DBControl.getKeyWords(filepath);
		multipliers = DBControl.getMultipliers(filepath);
		/*
		keyWords = Arrays.asList("username", "email", "user", "account", "Username",
				"Email", "User", "Account");
		
		multipliers = new HashMap<String, Map<String, AttributeWordAndScoreMultiplier>>();
		for (String attribute: attributes){
			multipliers.put(attribute, new HashMap<String, AttributeWordAndScoreMultiplier>());
			for (String word: keyWords){
				AttributeWordAndScoreMultiplier attributeWordAndScoreMultiplier = new AttributeWordAndScoreMultiplier(attribute, word, 1);
				multipliers.get(attribute).put(word, attributeWordAndScoreMultiplier);
			}
		}*/
	}
}
