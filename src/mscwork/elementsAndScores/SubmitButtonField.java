package mscwork.elementsAndScores;

import org.openqa.selenium.WebElement;

public class SubmitButtonField {

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
	public SubmitButtonField(WebElement field, int score) {
		super();
		this.field = field;
		this.score = score;
	}
	public SubmitButtonField() {
		super();
	}
	
	@Override
	public String toString() {
		return "SubmitButtonField [field=" + field + ", score=" + score + "]";
	}

}
