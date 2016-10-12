package mscwork.attributeScore;

public class AttributeWordAndScoreMultiplier {

	private String attribute;
	private String word;
	private int multiplier;
	
	public AttributeWordAndScoreMultiplier(String attribute, String word, int multiplier) {
		super();
		this.attribute = attribute;
		this.word = word;
		this.multiplier = multiplier;
	}

	public AttributeWordAndScoreMultiplier() {
		super();
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
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
		return "AttributeWordAndScoreMultiplier [attribute=" + attribute + ", word=" + word + ", multiplier=" + multiplier
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + multiplier;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttributeWordAndScoreMultiplier other = (AttributeWordAndScoreMultiplier) obj;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		if (multiplier != other.multiplier)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

}
