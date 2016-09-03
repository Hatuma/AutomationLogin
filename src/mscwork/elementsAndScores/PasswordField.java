package mscwork.elementsAndScores;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import mscwork.attributeScore.AttributeWordAndScoreMultiplier;
import mscwork.db.DBControl;
import mscwork.db.FilePathEnum;

public class PasswordField extends FieldAndScore{
	
	public static FilePathEnum filepath = FilePathEnum.PASSWORD;
	
	public static Map<String, Map<String, AttributeWordAndScoreMultiplier>> multipliers;
	public static List<String> keyWords;
	
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
		keyWords = DBControl.getKeyWords(filepath);
		multipliers = DBControl.getMultipliers(filepath);
		/*keyWords = Arrays.asList("pass", "password", "Password");
		
		multipliers = new HashMap<String, Map<String, AttributeWordAndScoreMultiplier>>();
		for (String attribute: attributes){
			multipliers.put(attribute, new HashMap<String, AttributeWordAndScoreMultiplier>());
			for (String word: keyWords){
				AttributeWordAndScoreMultiplier attributeWordAndScoreMultiplier = new AttributeWordAndScoreMultiplier(attribute, word, 1);
				multipliers.get(attribute).put(word, attributeWordAndScoreMultiplier);
			}
		}
		*/
		//attribute.setAttribue();
	}

}
