package mscwork.elementsAndScores;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import attributeScore.AttributeWordAndScoreMultiplier;

public class FieldAndScore {
	
	public static List<String> attributes = Arrays.asList("id", "class", "name", "text", "value", "type");
	public static List<String> keyWords;
	
	public static Map<String, Map<String, AttributeWordAndScoreMultiplier>> multipliers;
	

	private WebElement field;
	private int score;
	
	public WebElement getField() {
		return field;
	}
	public void setField(WebElement field) {
		this.field = field;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public FieldAndScore(WebElement field, int score) {
		super();
		this.field = field;
		this.score = score;
	}
	public FieldAndScore() {
		super();
	}
	
	public void incScore(){
		score++;
	}
	public void incScore(int i) {
		score += i;
	}
	
}
