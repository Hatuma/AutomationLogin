package mscwork.elementsAndScores;

import org.openqa.selenium.WebElement;

public class UserNameField extends FieldAndScore{

	public UserNameField(WebElement field, int score) {
		super(field, score);
	}
	
	public UserNameField() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserNameField [field=" + getField() + ", score=" + getScore() + "]";
	}

}
