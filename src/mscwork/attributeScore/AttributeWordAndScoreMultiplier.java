package mscwork.attributeScore;

public class AttributeWordAndScoreMultiplier {

	private String attribue;
	private String word;
	private int multiplier;
	
	
	public AttributeWordAndScoreMultiplier() {
		super();
	}

	public AttributeWordAndScoreMultiplier(String attribue, String word, int multiplier) {
		super();
		this.attribue = attribue;
		this.word = word;
		this.multiplier = multiplier;
	}

	public String getAttribue() {
		return attribue;
	}

	public void setAttribue(String attribue) {
		this.attribue = attribue;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public String toString() {
		return "AttributeAndScoreMultiplier [attribue=" + attribue + ", word=" + word + ", multiplier=" + multiplier
				+ "]";
	}

}
