package mscwork.elementsAndScores;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import mscwork.attributeScore.AttributeWordAndScoreMultiplier;
import mscwork.db.DBControl;
import mscwork.db.FilePathEnum;

public class SubmitButtonField extends FieldAndScore{
	
	public static FilePathEnum filepath = FilePathEnum.SUBMIT;
	
	public static Map<String, Map<String, AttributeWordAndScoreMultiplier>> multipliers;
	public static List<String> keyWords;

	public SubmitButtonField(WebElement field, int score) {
		super(field, score);
	}
	
	public SubmitButtonField() {
		super();
	}
	
	@Override
	public String toString() {
		return "SubmitButtonField [field=" + getField() + ", score=" + getScore() + ", location=" + getField().getLocation() + ", value=" + getField().getAttribute("value") + ", text=" + getField().getText() + "]";
	}

	static{
		keyWords = DBControl.getKeyWords(filepath);
		multipliers = DBControl.getMultipliers(filepath);
		/*keyWords = Arrays.asList("login", "submit", "bejelentkezés", "bejelentkezes",
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
		}*/
	}
}
