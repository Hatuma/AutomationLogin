package mscwork.elementsAndScores;

import org.openqa.selenium.WebElement;

public class UserNameField {

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
	public UserNameField(WebElement field, int score) {
		super();
		this.field = field;
		this.score = score;
	}
	public UserNameField() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserNameField [field=" + field + ", score=" + score + "]";
	}

}
