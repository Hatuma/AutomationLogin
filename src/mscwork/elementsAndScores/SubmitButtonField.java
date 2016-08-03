package mscwork.elementsAndScores;

import org.openqa.selenium.WebElement;

public class SubmitButtonField extends FieldAndScore{

	public SubmitButtonField(WebElement field, int score) {
		super(field, score);
	}
	
	public SubmitButtonField() {
		super();
	}
	
	@Override
	public String toString() {
		return "SubmitButtonField [field=" + getField() + ", score=" + getScore() + "]";
	}

}
