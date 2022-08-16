package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Map<String, Integer> totalResults = new HashMap<>();
				
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		
		Thread thread[] = new Thread[commentFiles.length];
		for(int index =0; index<commentFiles.length; index++) {
			thread[index] = new Thread(new MultiFileReader(commentFiles[index], totalResults));
			thread[index].start();
		}
		
		try {
			for(int index =0; index<commentFiles.length; index++) {
				thread[index].join();
			}
			System.out.println("RESULTS\n=======");
			totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
