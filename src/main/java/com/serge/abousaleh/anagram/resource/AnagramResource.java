package com.serge.abousaleh.anagram.resource;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.serge.abousaleh.anagram.domain.AnagramResult;
import com.serge.abousaleh.anagram.service.IAnagramService;
import com.serge.abousaleh.anagram.utils.AnagramUtility;

@RestController
public class AnagramResource {

	
	private static final String DICTIONARY_FILE_PATH = "/src/main/resources/Words_Tech_Test.txt";
	
	@Autowired
	private IAnagramService anagramService;
	
	@RequestMapping (value="/anagrams/{word}", method = RequestMethod.GET)
	public AnagramResult getAnagrams(@PathVariable(name="word") String word) {
	
		String dictionaryFilePath = new File("").getAbsolutePath();
		dictionaryFilePath = dictionaryFilePath.concat(DICTIONARY_FILE_PATH);
		
		return anagramService.getAnagrams(word, dictionaryFilePath);
	}
	
	
	@RequestMapping (value="/anagrams/", method = RequestMethod.POST)
	public String insertWordInDictionary(@RequestBody String newWord) {
	
		String dictionaryFilePath = new File("").getAbsolutePath();
		dictionaryFilePath = dictionaryFilePath.concat(DICTIONARY_FILE_PATH);
		String resultMessage = "";
		
		boolean inserted = anagramService.insertWordInDictionary(newWord, dictionaryFilePath);
		
		if(inserted) {
			resultMessage =  "Word correctly inserted into dictionary.";
		} else {
			resultMessage =  "Word already exit in dictionary.";
		}
		return resultMessage;
	}
	
	@RequestMapping (value="/anagrams/{word}", method = RequestMethod.DELETE)
	public String deleteWordFromDictionary(@PathVariable(name="word") String word) {
	
		String dictionaryFilePath = new File("").getAbsolutePath();
		dictionaryFilePath = dictionaryFilePath.concat(DICTIONARY_FILE_PATH);
		String resultMessage = "";
		
		boolean deleted = anagramService.deleteWordFromDictionary(word, dictionaryFilePath);
		
		if(deleted) {
			resultMessage =  "Word correctly deleted from dictionary.";
		} else {
			resultMessage =  "Word does not exist in dictionary";
		}
		return resultMessage;
	}
	
	
}
