package mscwork.elementsAndScores;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mscwork.attributeScore.AttributeWordAndScoreMultiplier;
import mscwork.db.DBControl;
import mscwork.db.FilePathEnum;

public class FieldAndScore implements Comparable<FieldAndScore>{
	
	public static List<String> attributes;// = Arrays.asList("id", "class", "name", "text", "value", "type");	

	private WebElement field;
	private int score;
	private List<AttributeWordAndScoreMultiplier> gotScoreFrom = new ArrayList<AttributeWordAndScoreMultiplier>();
	
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
	public List<AttributeWordAndScoreMultiplier> getGotScoreFrom() {
		return gotScoreFrom;
	}
	public void setGotScoreFrom(List<AttributeWordAndScoreMultiplier> gotScoreFrom) {
		this.gotScoreFrom = gotScoreFrom;
	}
	
	public void addToGotScoreList(AttributeWordAndScoreMultiplier attributeWordAndScoreMultiplier){
		this.gotScoreFrom.add(attributeWordAndScoreMultiplier);
	}
	
	public FieldAndScore(WebElement field, int score) {
		super();
		this.field = field;
		this.score = score;
	}
	public FieldAndScore() {
		super();
	}
	
	public void incScore(int i) {
		score += i;
	}
	
	static {
		attributes = DBControl.getAttributes(FilePathEnum.USERNAME);
	}

	public int compareTo(FieldAndScore o) {
		return o.getScore() - score;
	}
	
}
