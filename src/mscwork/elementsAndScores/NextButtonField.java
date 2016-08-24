package mscwork.elementsAndScores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import attributeScore.AttributeWordAndScoreMultiplier;

public class NextButtonField extends FieldAndScore{
	
	public static Map<String, Map<String, AttributeWordAndScoreMultiplier>> multipliers;
	public static List<String> keyWords;

	public NextButtonField(WebElement field, int score) {
		super(field, score);
	}
	
	public NextButtonField() {
		super();
	}
	
	@Override
	public String toString() {
		return "NextButtonField [field=" + getField() + ", score=" + getScore() + ", location=" + getField().getLocation() + ", value=" + getField().getAttribute("value") + ", text=" + getField().getText() + "]";
	}

	static{
		keyWords = Arrays.asList("next", "tovább");
		
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
