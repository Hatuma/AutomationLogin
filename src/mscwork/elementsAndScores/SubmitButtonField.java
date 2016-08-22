package mscwork.elementsAndScores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;

import attributeScore.AttributeWordAndScoreMultiplier;

public class SubmitButtonField extends FieldAndScore{

	public SubmitButtonField(WebElement field, int score) {
		super(field, score);
	}
	
	public SubmitButtonField() {
		super();
	}
	
	@Override
	public String toString() {
		return "SubmitButtonField [field=" + getField() + ", score=" + getScore() + ", location=" + getField().getLocation() + ", text=" + getField().getAttribute("value") + "]";
	}

	static{
		keyWords = Arrays.asList("login", "submit", "bejelentkezés", "bejelentkezes",
				"signin", "sign in", "Login", "Submit", "Bejelentkezés", "Bejelentkezes", "Signin", "Sign in", "belépés", "Belépés");
		
		multipliers = new HashMap<String, Map<String, AttributeWordAndScoreMultiplier>>();
		for (String attribute: attributes){
			multipliers.put(attribute, new HashMap<String, AttributeWordAndScoreMultiplier>());
			for (String word: keyWords){
				AttributeWordAndScoreMultiplier attributeWordAndScoreMultiplier = new AttributeWordAndScoreMultiplier(attribute, word, 1);
				multipliers.get(attribute).put(word, attributeWordAndScoreMultiplier);
			}
		}
		
		for(AttributeWordAndScoreMultiplier multiplier: multipliers.get("value").values()){
			multiplier.setMultiplier(5);
		}
		
		for(AttributeWordAndScoreMultiplier multiplier: multipliers.get("text").values()){
			multiplier.setMultiplier(5);
		}
	}
}
