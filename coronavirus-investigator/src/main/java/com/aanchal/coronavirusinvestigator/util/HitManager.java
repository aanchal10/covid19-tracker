package com.aanchal.coronavirusinvestigator.util;

public class HitManager {
	
	private static int hits = 0;


	public static void initializeHitValues() {
	 		
	 		hits = 0;
	 }

	public static int getHits() {
		return hits;
	}

	public static void setHits(int hits) {
		HitManager.hits = hits;
	}
	 	
	

}
