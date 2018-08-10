package com.serge.abousaleh.anagram.service;


import java.util.Set;

import com.serge.abousaleh.anagram.domain.AnagramResult;

public interface IAnagramService {

	AnagramResult getAnagrams(String word, String fileName);
	
	boolean insertWordInDictionary(String word, String fileName);
	
	boolean deleteWordFromDictionary(String word, String fileName);

	boolean checkWordExistsInDictionary(String word, Set<String> dictionary);
}
