package com.serge.abousaleh.anagram.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.serge.abousaleh.anagram.domain.AnagramResult;
import com.serge.abousaleh.anagram.service.IAnagramService;
import com.serge.abousaleh.anagram.utils.AnagramUtility;

@Service
public class AnagramService implements IAnagramService {

	Logger logger = LoggerFactory.getLogger(AnagramService.class);
	
	@Override
	public AnagramResult getAnagrams(String word, String fileName) {
		
		// Get Dictionary
		AnagramResult anagramResult = new AnagramResult();
		Set<String> dictionary = AnagramUtility.getDictionaryContent(fileName);
		anagramResult.setAnagrams(AnagramUtility.getListAnagrams(word, dictionary));
		
		return anagramResult;
	}

	@Override
	public boolean insertWordInDictionary(String word, String fileName) {
		logger.debug("insertWordInDictionary - Start");
		boolean result = false;
		Set<String> dictionary = AnagramUtility.getDictionaryContent(fileName);
		boolean wordExists = checkWordExistsInDictionary(word, dictionary);
		
		if(!wordExists) {
			dictionary.add(word);
			// Append to dictionary File
			AnagramUtility.writeSetToFile(dictionary, fileName);
		}

		logger.debug("insertWordInDictionary - End");
		return result;
	}

	@Override
	public boolean deleteWordFromDictionary(String word, String fileName) {
		logger.debug("deleteWordFromDictionary - Start");
		boolean result = false;
		Set<String> dictionary = AnagramUtility.getDictionaryContent(fileName);
		boolean wordExists = checkWordExistsInDictionary(word, dictionary);
		// Delete from File
		if(wordExists) {
			result = dictionary.remove(word);
			if (result) {
				AnagramUtility.writeSetToFile(dictionary, fileName);
			}
		}
		logger.debug("deleteWordFromDictionary - End");
		return result;
	}
	
	@Override
	public boolean checkWordExistsInDictionary(String word, Set<String> dictionary) {
		logger.debug("checkWordExistsInDictionary - Start");
		boolean wordExists = false;
		if (word.length() > 1 && word.length() < Integer.MAX_VALUE) {
			if (dictionary.contains(word)) {
				wordExists = true;
			}
		}
		logger.debug("checkWordExistsInDictionary - End");
		return wordExists;
	}

}
