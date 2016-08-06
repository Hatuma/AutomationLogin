package mscwork.elementsAndScores;

import org.openqa.selenium.WebElement;

public class FieldAndScore {

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
	
}
