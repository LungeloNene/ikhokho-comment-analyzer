package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommentAnalyzer {
	
	private File file;
	
	public CommentAnalyzer(File file) {
		this.file = file;
	}
	
	public Map<String, Integer> analyze() {
		
		Map<String, Integer> resultsMap = new HashMap<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				Rule rules[] = {
						new Rule(line.length() < 15, "SHORTER_THAN_15", resultsMap),
						new Rule(line.contains("Mover"), "MOVER_MENTIONS", resultsMap),
						new Rule(line.contains("Shaker"), "SHAKER_MENTIONS", resultsMap),
						new Rule(line.contains("?"), "QUESTIONS", resultsMap),
						new Rule(line.contains("https://") || line.contains("http://"), "SPAM", resultsMap)
				};
				for(int index=0; index<rules.length; index++)
					rules[index].createRule();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}
		
		return resultsMap;
		
	}
	
	

}
