package mscwork.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import mscwork.attributeScore.AttributeWordAndScoreMultiplier;

public class DBReset {

	public static void main(String[] args) {
		for (FilePathEnum filePathEnum: FilePathEnum.values()) {
			List<AttributeWordAndScoreMultiplier> list = new ArrayList<AttributeWordAndScoreMultiplier>();
			List<String> attributes = DBControl.getAttributes(filePathEnum);
			List<String> keywords = DBControl.getKeyWords(filePathEnum);
			for (String attribute: attributes){
				for (String keyword: keywords){
					AttributeWordAndScoreMultiplier attributeWordAndScoreMultiplier = new AttributeWordAndScoreMultiplier(attribute, keyword, 50/*RandomUtils.nextInt(1, 101)*/);
					/*if (attribute.equals("text")||attribute.equals("value")){
						attributeWordAndScoreMultiplier.setMultiplier(50);
					}*/
					list.add(attributeWordAndScoreMultiplier);
				}
			}
			DBControl.modifyJSONWithNewScores(filePathEnum, list);				
		}
	}

}
