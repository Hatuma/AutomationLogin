package mscwork.elementsAndScores;

import org.openqa.selenium.WebElement;

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

}
