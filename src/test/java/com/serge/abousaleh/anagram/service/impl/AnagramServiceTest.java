package com.serge.abousaleh.anagram.service.impl;


import java.io.File;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.serge.abousaleh.anagram.service.IAnagramService;
import com.serge.abousaleh.anagram.utils.AnagramUtility;

public class AnagramServiceTest {

	private IAnagramService anagramService;
	
	@Before
	public void setup() {
		anagramService = new AnagramService();
	}
	
	@Test
	public void shouldCheckWordExistsInDictionary_givenExistingWord() {
		
		String fileName = "/src/test/resources/dictionaryRandom.txt";
		String dictionaryFilePath = new File("").getAbsolutePath();
		dictionaryFilePath = dictionaryFilePath.concat(fileName);
		
		String word1 = "RSEttc";
		String word2 = "'ww1z";
		Set<String> dictionary = AnagramUtility.getDictionaryContent(dictionaryFilePath);
		
		// Action
		boolean b1 = anagramService.checkWordExistsInDictionary(word1, dictionary);
		boolean b2 = anagramService.checkWordExistsInDictionary(word2, dictionary);
		
		// Assert
		Assertions.assertThat(b1).isTrue();
		Assertions.assertThat(b2).isTrue();
	}
	
	@Test
	public void shouldCheckWordExistsInDictionary_givenNonExistingWord() {
		String fileName = "/src/test/resources/dictionaryRandom.txt";
		String dictionaryFilePath = new File("").getAbsolutePath();
		dictionaryFilePath = dictionaryFilePath.concat(fileName);
		
		String word1 = "00";
		String word2 = "'";
		String word3 = "'w";
		Set<String> dictionary = AnagramUtility.getDictionaryContent(dictionaryFilePath);
		
		// Action
		boolean b1 = anagramService.checkWordExistsInDictionary(word1, dictionary);
		boolean b2 = anagramService.checkWordExistsInDictionary(word2, dictionary);
		boolean b3 = anagramService.checkWordExistsInDictionary(word3, dictionary);
		
		// Assert
		Assertions.assertThat(b1).isFalse();
		Assertions.assertThat(b2).isFalse();
		Assertions.assertThat(b3).isFalse();
	}
	
	@Test
	public void shouldInsertWordInDictionary_givenNonExistingWord() {
		String fileName = "/src/test/resources/dictionaryRandom.txt";
		String dictionaryFilePath = new File("").getAbsolutePath();
		dictionaryFilePath = dictionaryFilePath.concat(fileName);
		
		String word1 = "000";
		String word2 = "111";
		String word3 = "-t$*";
		
		// Action
		anagramService.insertWordInDictionary(word1, dictionaryFilePath);
		anagramService.insertWordInDictionary(word2, dictionaryFilePath);
		anagramService.insertWordInDictionary(word3, dictionaryFilePath);
		Set<String> dictionary = AnagramUtility.getDictionaryContent(dictionaryFilePath);
		
		boolean b1 = anagramService.checkWordExistsInDictionary(word1, dictionary);
		boolean b2 = anagramService.checkWordExistsInDictionary(word2, dictionary);
		boolean b3 = anagramService.checkWordExistsInDictionary(word3, dictionary);
		
		//Assert
		Assertions.assertThat(b1).isTrue();
		Assertions.assertThat(b2).isTrue();
		Assertions.assertThat(b3).isTrue();
	}
	
	@Test
	public void shouldDeleteWordFromDictionary_givenExistingWord() {
		String fileName = "/src/test/resources/dictionaryRandom.txt";
		String dictionaryFilePath = new File("").getAbsolutePath();
		dictionaryFilePath = dictionaryFilePath.concat(fileName);
		
		String word1 = "000";
		String word2 = "111";
		String word3 = "-t$*";
		anagramService.insertWordInDictionary(word1, dictionaryFilePath);
		anagramService.insertWordInDictionary(word2, dictionaryFilePath);
		anagramService.insertWordInDictionary(word3, dictionaryFilePath);
		
		// Action
		anagramService.deleteWordFromDictionary(word1, dictionaryFilePath);
		anagramService.deleteWordFromDictionary(word2, dictionaryFilePath);
		anagramService.deleteWordFromDictionary(word3, dictionaryFilePath);
		Set<String> dictionary = AnagramUtility.getDictionaryContent(dictionaryFilePath);
		
		boolean b1 = anagramService.checkWordExistsInDictionary(word1, dictionary);
		boolean b2 = anagramService.checkWordExistsInDictionary(word2, dictionary);
		boolean b3 = anagramService.checkWordExistsInDictionary(word3, dictionary);
		
		// Assert
		Assertions.assertThat(b1).isFalse();
		Assertions.assertThat(b2).isFalse();
		Assertions.assertThat(b3).isFalse();
	}

}
