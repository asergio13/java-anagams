package com.serge.abousaleh.anagram.utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class AnagramUtilityTest {
	
	
	@Before
	public void setup() {
	}
	
//	@Test
//	public void shouldGetDictionaryContent_givenSmallDictionary() {
//		
//		String fileName = "/src/test/resources/dictionaryRandom.txt";
//		String dictionaryFilePath = new File("").getAbsolutePath();
//		dictionaryFilePath = dictionaryFilePath.concat(fileName);
//		
//		// action
//		List<String> listAllWords = AnagramUtility.getDictionaryContent(dictionaryFilePath);
//		
//		// assert
//		Assertions.assertThat(listAllWords).isNotNull();
//		Assertions.assertThat(listAllWords).isNotEmpty();
//	}
	
//	@Test
//	public void shouldGetDictionaryContent_givenBigDictionary() {
//		
//		String fileName = "/src/test/resources/Words_Tech_Test.txt";
//		String dictionaryFilePath = new File("").getAbsolutePath();
//		dictionaryFilePath = dictionaryFilePath.concat(fileName);
//		
//		// action
//		List<String> listAllWords = AnagramUtility.getDictionaryContent(dictionaryFilePath);
//		
//		// assert
//		Assertions.assertThat(listAllWords).isNotNull();
//		Assertions.assertThat(listAllWords).isNotEmpty();
//	}
	
	
	@Test
	public void shouldCheckAnagrams_givenTwoWords() {
		
		String word1 = "abcde";
		String word21 = "cbade";
		String word22 = "dacbe";
		String word3 = "cbadez";
		String word4 = "";
		
		// action
		boolean areAnagrams_test1 = AnagramUtility.checkAnagrams(word1, word21);
		boolean areAnagrams_test2 = AnagramUtility.checkAnagrams(word1, word22);
		boolean areAnagrams_test3 = AnagramUtility.checkAnagrams(word1, word3);
		boolean areAnagrams_test4 = AnagramUtility.checkAnagrams(word1, word4);
		
		// assert
		Assertions.assertThat(areAnagrams_test1).isTrue();
		Assertions.assertThat(areAnagrams_test2).isTrue();
		Assertions.assertThat(areAnagrams_test3).isFalse();
		Assertions.assertThat(areAnagrams_test4).isFalse();
	}
	
	@Test
	public void shouldGetAnagrams_givenValidWords() {
		
		String fileName = "/src/test/resources/dictionaryRandom.txt";
		String dictionaryFilePath = new File("").getAbsolutePath();
		dictionaryFilePath = dictionaryFilePath.concat(fileName);
		
		String word1 = "abcde";
		String word2 = "vaaj";
		String word3 = "lp!";
		
		// action
		List<String> list1 = AnagramUtility.getAnagramsFromDictionary(word1, dictionaryFilePath);
		List<String> list2 = AnagramUtility.getAnagramsFromDictionary(word2, dictionaryFilePath);
		List<String> list3 = AnagramUtility.getAnagramsFromDictionary(word3, dictionaryFilePath);
		
		// assert
		Assertions.assertThat(list1).contains("cbeda");
		Assertions.assertThat(list2).contains("java");
		Assertions.assertThat(list3).contains("!pl");
	}
	
	@Test
	public void shouldGetAnagrams_givenNotValidWords() {
		
		String fileName = "/src/test/resources/dictionaryRandom.txt";
		String dictionaryFilePath = new File("").getAbsolutePath();
		dictionaryFilePath = dictionaryFilePath.concat(fileName);
		
		String word1 = "";
		String word2 = "x";
		String word3 = "xax";
		String word4 = "abcd";
		String word5 = "1";
		
		// action
		List<String> list1 = AnagramUtility.getAnagramsFromDictionary(word1, dictionaryFilePath);
		List<String> list2 = AnagramUtility.getAnagramsFromDictionary(word2, dictionaryFilePath);
		List<String> list3 = AnagramUtility.getAnagramsFromDictionary(word3, dictionaryFilePath);
		List<String> list4 = AnagramUtility.getAnagramsFromDictionary(word4, dictionaryFilePath);
		List<String> list5 = AnagramUtility.getAnagramsFromDictionary(word5, dictionaryFilePath);
		
		// assert
		Assertions.assertThat(list1).isNull();
		Assertions.assertThat(list2).isNull();
		Assertions.assertThat(list3).isEmpty();
		Assertions.assertThat(list4).isEmpty();
		Assertions.assertThat(list5).isNull();
	}
	
	@Test
	public void shouldConvertStringToMap_givenValidWords() {
		
		
		String word1 = "";
		String word2 = "abc";
		String word3 = "aba";
		String word4 = "a*c*'";
		
		// action
		Map<Character, Integer> mapChar1 = AnagramUtility.convertStringToMap(word1);
		Map<Character, Integer> mapChar2 = AnagramUtility.convertStringToMap(word2);
		Map<Character, Integer> mapChar3 = AnagramUtility.convertStringToMap(word3);
		Map<Character, Integer> mapChar4 = AnagramUtility.convertStringToMap(word4);
		
		// assert
		Assertions.assertThat(mapChar1).isNull();
		Assertions.assertThat(mapChar2).isNotNull();
		Assertions.assertThat(mapChar2).isNotEmpty();
		Assertions.assertThat(mapChar3).isNotNull();
		Assertions.assertThat(mapChar3).isNotEmpty();
		Assertions.assertThat(mapChar4).isNotNull();
		Assertions.assertThat(mapChar4).isNotEmpty();
	}

}