package com.revature.util;

import com.revature.Status;
import com.revature.Type;

/**
 * A utility class to quickly get a safe value from strings
 * 
 * @author Kristoffer Spencer
 */
public final class SafeParser {
	
	public static Type parseType(String s) {
		
		try {
			
			return Type.valueOf(s.toUpperCase());
			
		} catch(Exception e) {
			
			return Type.INVALID;
			
		}
		
	}
	
	public static Status parseStatus(String s) {

		try {
			
			return Status.valueOf(s.toUpperCase());
			
		} catch(Exception e) {
			
			return Status.INVALID;
			
		}
		
	}
	
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
