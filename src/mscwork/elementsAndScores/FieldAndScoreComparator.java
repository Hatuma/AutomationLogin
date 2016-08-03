package mscwork.elementsAndScores;

import java.util.Comparator;

public class FieldAndScoreComparator implements Comparator<FieldAndScore> {

	public int compare(FieldAndScore arg0, FieldAndScore arg1) {
		return arg1.getScore() - arg0.getScore();
	}

}
