package com.ikhokha.techcheck;

import java.io.File;
import java.util.Map;

public class MultiFileReader implements Runnable {
	private File file;
	private Map<String, Integer> totalResults;
	
	public MultiFileReader(File file, Map<String, Integer> totalResults) {
		this.file = file;
		this.totalResults = totalResults;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		CommentAnalyzer commentAnalyzer = new CommentAnalyzer(file);
		Map<String, Integer> fileResults = commentAnalyzer.analyze();
		addReportResults(fileResults, totalResults);
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {

		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			//Entry data
			String key = entry.getKey();
			int value = entry.getValue();
			
			//Check if key already exist and add entry value to the existing value or put new set
			if(target.containsKey(key)) {
				target.put(key, target.get(key) + value);
			}
			else {
				target.put(key,  value);
			}
			
		}
		
	}
}
