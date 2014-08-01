package io.github.KoenMulder.Yolo;

import java.util.HashMap;
import java.util.Map;


public class SwearUtils {
	public static Map<String, String[]> swearwordMap;
	
	static {
		swearwordMap = new HashMap<String, String[]>();
		
		swearwordMap.put("fuck", new String[] {
				"fudge"
				});
		swearwordMap.put("shit", new String[] {
				"chocolate"
				});
		swearwordMap.put("fuc", new String[] {
				"pineapple"
				});
		swearwordMap.put("asshole", new String[] {
				"grapefruit"
				});
		swearwordMap.put("dipshit", new String[] {
				"smoothie"
				});
		swearwordMap.put("nigger", new String[] {
				"african american"
				});
		swearwordMap.put("nigga", new String[] {
				"african american"
				});
		swearwordMap.put("niggers", new String[] {
				"african americans"
				});
		swearwordMap.put("motherfucker", new String[] {
				"Cranberry"
		        });
		swearwordMap.put("douchbag", new String[] {
				"showerhead"
				});
		swearwordMap.put("bitch", new String[] {
				"female dog"
				});
		swearwordMap.put("bullshit", new String[] {
				"poop"
				});
		swearwordMap.put("crap", new String[] {
				"god"
				});
		swearwordMap.put("suck", new String[] {
				"swallow"
				});
		swearwordMap.put("sucker", new String[] {
				"swallower"
				});
		swearwordMap.put("cocksucker", new String[] {
				"i love you"
				});
		swearwordMap.put("cocksucking", new String[] {
				"Lil Wayne"
				});
		swearwordMap.put("pussylicker", new String[] {
				"cat"
				});
		swearwordMap.put("cunt", new String[] {
				"Justin Bieber"
				});
		swearwordMap.put("faggot", new String[] {
				""
				});
	}
}
