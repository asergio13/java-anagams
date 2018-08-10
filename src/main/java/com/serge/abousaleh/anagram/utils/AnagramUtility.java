package com.serge.abousaleh.anagram.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;


public final class AnagramUtility {
	
	private AnagramUtility() {}
	
	static final Logger logger = LoggerFactory.getLogger(AnagramUtility.class);
	
	public static Set<String> getDictionaryContent(String fileName) {
		logger.debug("getDictionaryContent - Start");
		Set<String> dictionaryContent = null;

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			// convert Stream to collection
			dictionaryContent = stream.collect(Collectors.toSet());
			if (!CollectionUtils.isEmpty(dictionaryContent) && dictionaryContent.size() < 200) {
				logger.debug(dictionaryContent.toString());
			}
			
		} catch (IOException e) {
			logger.error("Error getting the dictionary content", e);
		}
		logger.debug("getDictionaryContent - End");
		return dictionaryContent;
	}
	
	
	public static Set<String> getListAnagrams(String word, Set<String> dictionary) {
		logger.debug("getListAnagrams - Start");
		Set<String> listAnagrams  = null;
		if (word.length() > 1 && word.length() < Integer.MAX_VALUE) {
			listAnagrams = dictionary
			.stream()
			.filter(lineDictionary -> AnagramUtility.checkAnagrams(lineDictionary, word))
			.collect(Collectors.toSet());
		}
		logger.debug("getListAnagrams - End");
		return listAnagrams;
	}
	
	public static boolean isAlpha(String name) {
		return name.matches("[a-zA-Z]+");
	}
	
	static boolean checkAnagrams(String word1, String word2) {
		logger.debug("checkAnagrams - Start");
		boolean result = false;

		Map<Character, Integer> mapChars1 = AnagramUtility.convertStringToMap(word1);
		Map<Character, Integer> mapChars2 = AnagramUtility.convertStringToMap(word2);
		
		if(mapChars1 != null && mapChars2 != null 
				&& mapChars1.equals(mapChars2)) {
			result = true;
		}
		logger.debug("checkAnagrams - End");
		return result;
	}
	
	public static Map<Character, Integer> convertStringToMap(String word) {
		logger.debug("convertStringToMap - Start");
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
		logger.debug("convertStringToMap - End");
		return mapChars;
	}
	
	public static void writeSetToFile (Set<String> dictionary, String fileName) {
		logger.debug("writeSetToFile - Start");
		String fileContent = dictionary.stream()
				.collect(Collectors.joining("\n"));
		
		try( FileWriter fw = new FileWriter(new File(fileName)) ) {
			fw.write(fileContent);
		} catch (IOException e) {
			logger.error("Error writing into file " + fileName, e);
		}
		logger.debug("writeSetToFile - End");
	}
}
