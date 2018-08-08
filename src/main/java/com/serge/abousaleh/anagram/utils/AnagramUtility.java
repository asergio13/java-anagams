package com.serge.abousaleh.anagram.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AnagramUtility {
	
	public static List<String> getDictionaryContent(String fileName) {
		
		List<String> listFullWords = null;

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(System.out::println);
			// convert Stream to collection
			listFullWords = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return listFullWords;
	}
	
	
	public static List<String> getAnagramsFromDictionary(String word, String fileName) {
		
		List<String>  listAnagrams = null;
		if (word.length() > 1 && word.length() < Integer.MAX_VALUE) {
		
			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
	
				listAnagrams = stream
						.filter(lineDictionary -> AnagramUtility.checkAnagrams(lineDictionary, word))
						.collect(Collectors.toList());
	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listAnagrams;
	}

	
	public static boolean isAlpha(String name) {
		return name.matches("[a-zA-Z]+");
	}
	
//	static boolean checkAnagram(String word1, String word2) {
//
//		boolean areAnagram = true;
//		
////		if (isAlpha(word1) && isAlpha(word1)) {
//			// put everything to lower case
////			char a[] = word1.toLowerCase().toCharArray();
////			char b[] = word2.toLowerCase().toCharArray();
//			char a[] = word1.toCharArray();
//			char b[] = word2.toCharArray();
//			
//			//Declare 2 arrays of size 26 (26 letters)
//			int first[] = new int[100];
//			int second[] = new int[100];
//			int frequency = 0;
//			
//		
//			// Calculating frequency of characters of first string
//			for(int i = 0; i < a.length; i++) {
//				first[a[frequency]-'a']++;
//				frequency++;
//			}
//		 
//			//reinit frequency
//			frequency = 0;
//			
//			for(int i = 0; i < b.length; i++) {
//				second[b[frequency]-'a']++;
//				frequency++;
//			}
//		
//			// Comparing frequency of characters
//			for (frequency = 0; frequency < 100; frequency++) {
//				if (first[frequency] != second[frequency]) {
//					areAnagram = false;
//					break;
//				}
//			}
////		} else {
////			areAnagram = false;
////		}
//		return areAnagram;
//	}
	
	
	static boolean checkAnagrams(String word1, String word2) {
		boolean result = false;

		Map<Character, Integer> mapChars1 = AnagramUtility.convertStringToMap(word1);
		Map<Character, Integer> mapChars2 = AnagramUtility.convertStringToMap(word2);
		
		if(mapChars1 != null && mapChars2 != null 
				&& mapChars1.equals(mapChars2)) {
			result = true;
		}
		return result;
	}
	
	public static Map<Character, Integer> convertStringToMap(String word) {
		Map<Character, Integer> mapChars = null;
		if(word != null && word.length() > 1) {
			mapChars = new HashMap<>();
			
			// Convert to charArray
			char[] wordChar = word.toCharArray();
			
			for (int i = 0; i < word.length(); i++) {
				char a = wordChar[i];
				Stream<Character> sch = word.chars().mapToObj(c -> (char)c);
				long nbChar = sch.filter(item -> item == a).count();
				
				// if char not yet logged in the map, add it
				if(mapChars.get(wordChar[i]) == null) {
					mapChars.put(Character.valueOf(wordChar[i]), Integer.valueOf((int)nbChar));
				}
			}
		}
		return mapChars;
	}
}
