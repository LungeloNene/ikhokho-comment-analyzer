package com.ikhokha.techcheck;

import java.util.Map;

public class Rule {
	boolean expression; String key; Map<String, Integer> resultsMap;
	
 public Rule(boolean expression, String key, Map<String, Integer> resultsMap ) {
	 this.expression = expression;
	 this.key = key;
	 this.resultsMap = resultsMap;
 }
 
 public void createRule() {
	 if(expression)
		 incOccurrence(resultsMap, key);
 }
 
 private void incOccurrence(Map<String, Integer> countMap, String key) {
		
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
	}
}
