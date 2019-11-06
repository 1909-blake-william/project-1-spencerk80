package com.revature.util;
/**
 * A utility class to quickly get a safe value from strings
 * 
 * @author Kristoffer Spencer
 */
public final class SafeParser {
	
	public static int parseInt(String s) {
		
		try {
			
			return Integer.parseInt(s);
			
		} catch(Exception e) {
			
			return -1;
			
		}
		
	}
	
	public static double parseDouble(String s) {
		
		try {
			
			return Double.parseDouble(s);
			
		} catch(Exception e) {
			
			return -1f;
			
		}
		
	}
	
}
