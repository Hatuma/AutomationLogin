package mscwork.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import mscwork.attributeScore.AttributeWordAndScoreMultiplier;

public class DBControl {
	
	private static String getJSON(FilePathEnum filePathEnum){
		String filepath = filePathEnum.getFilepath();
		String everything = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		}  catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail("file not found:" + filepath);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("some io exception crushed the db read");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return everything;
	}
	
	private static void overwriteFile(FilePathEnum filePathEnum, JSONObject json){
		File fold=new File(filePathEnum.getFilepath());
		fold.delete();
		File fnew=new File(filePathEnum.getFilepath());

		try {
		    FileWriter f2 = new FileWriter(fnew, false);
		    f2.write(json.toString());
		    f2.close();
		} catch (IOException e) {
		    e.printStackTrace();
		} 
	}

	public static List<String> getAttributes(FilePathEnum filePathEnum) {
		String jsonString = getJSON(filePathEnum);
		try {
			List<String> attributeList = new ArrayList<String>();
			JSONObject json = new JSONObject(jsonString);
			JSONArray array = json.getJSONArray("attributes");
			for (int i = 0; i < array.length(); i++) {
				attributeList.add(array.getString(i));
			}
			return attributeList;
		} catch (JSONException e) {
			e.printStackTrace();
			Assert.fail("The json was not correct");
		}
		return null;
	}
	
	public static List<String> getKeyWords(FilePathEnum filePathEnum) {
		String jsonString = getJSON(filePathEnum);
		try {
			List<String> keywordList = new ArrayList<String>();
			JSONObject json = new JSONObject(jsonString);
			JSONArray array = json.getJSONArray("keywords");
			for (int i = 0; i < array.length(); i++) {
				keywordList.add(array.getString(i));
			}
			return keywordList;
		} catch (JSONException e) {
			e.printStackTrace();
			Assert.fail("The json was not correct");
		}
		return null;
	}
	
	public static JSONObject modifyJSONWithNewScores(FilePathEnum filePathEnum, List<AttributeWordAndScoreMultiplier> list, boolean failed) {
		String jsonString = getJSON(filePathEnum);
		try {
			JSONObject json = new JSONObject(jsonString);	
			for (AttributeWordAndScoreMultiplier attributeWordAndScoreMultiplier: list){
				JSONArray array = json.getJSONArray("multipliers");
				JSONObject multipliersForAttribute = null;
				for (int i = 0; i < array.length(); i++) {
					multipliersForAttribute = array.getJSONObject(i);
					String attribute = multipliersForAttribute.getString("attribute");
					if (attribute.equals(attributeWordAndScoreMultiplier.getAttribue()))
						break;
				}
				JSONArray values = multipliersForAttribute.getJSONArray("values");
				for (int i = 0; i < values.length(); i++){
					JSONObject wordAndMultilier = values.getJSONObject(i);
					String keyword = wordAndMultilier.getString("keyword");
					if (keyword.equals(attributeWordAndScoreMultiplier.getWord())){
						wordAndMultilier.remove("multiplier");
						//we found the place where we need to modify multiplier
						int oldMultiplier = attributeWordAndScoreMultiplier.getMultiplier();
						int modifiedOldMultiplier = (oldMultiplier*8)/10;
						
						if (failed){
							wordAndMultilier.put("multiplier", modifiedOldMultiplier);
						} else {
							wordAndMultilier.put("multiplier", 20 + modifiedOldMultiplier);
						}
						break;
					}
				}
			}
			overwriteFile(filePathEnum, json);
		} catch (JSONException e) {
			e.printStackTrace();
			Assert.fail("The json was not correct");
		}
		return null;
	}
	
	public static Map<String, Map<String, AttributeWordAndScoreMultiplier>> getMultipliers(FilePathEnum filePathEnum) {
		String jsonString = getJSON(filePathEnum);
		try {
			Map<String, Map<String, AttributeWordAndScoreMultiplier>> multipliers = new HashMap<String, Map<String,AttributeWordAndScoreMultiplier>>();
			JSONObject json = new JSONObject(jsonString);
			JSONArray array = json.getJSONArray("multipliers");
			for (int i = 0; i < array.length(); i++) {
				JSONObject multipliersForAttribute = array.getJSONObject(i);
				
				String attribute = multipliersForAttribute.getString("attribute");
				JSONArray values = multipliersForAttribute.getJSONArray("values");
				
				Map<String, AttributeWordAndScoreMultiplier> submap = new HashMap<String, AttributeWordAndScoreMultiplier>();
				
				for (int j = 0; j < values.length(); j++) {
					JSONObject wordAndMultilier = values.getJSONObject(j);
					
					String keyword = wordAndMultilier.getString("keyword");
					int multiplier = wordAndMultilier.getInt("multiplier");
					
					AttributeWordAndScoreMultiplier attributeWordAndScoreMultiplier = new AttributeWordAndScoreMultiplier(attribute, keyword, multiplier);
					
					submap.put(keyword, attributeWordAndScoreMultiplier);
				}
				
				multipliers.put(attribute, submap);
			}
			return multipliers;
		} catch (JSONException e) {
			e.printStackTrace();
			Assert.fail("The json was not correct");
		}
		return null;
	}

}
