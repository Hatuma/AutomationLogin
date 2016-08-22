package mscwork.elementsAndScores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;

import attributeScore.AttributeWordAndScoreMultiplier;

public class PasswordField extends FieldAndScore{
	
	public PasswordField(WebElement field, int score) {
		super(field, score);
	}
	
	public PasswordField() {
		super();
	}
	
	@Override
	public String toString() {
		return "PasswordField [field=" + getField() + ", score=" + getScore() + "]";
	}
	
	
	static {
		keyWords = Arrays.asList("pass", "password", "Password");
		
		multipliers = new HashMap<String, Map<String, AttributeWordAndScoreMultiplier>>();
		for (String attribute: attributes){
			multipliers.put(attribute, new HashMap<String, AttributeWordAndScoreMultiplier>());
			for (String word: keyWords){
				AttributeWordAndScoreMultiplier attributeWordAndScoreMultiplier = new AttributeWordAndScoreMultiplier(attribute, word, 1);
				multipliers.get(attribute).put(word, attributeWordAndScoreMultiplier);
			}
		}
		
		//attribute.setAttribue();
	}

}
