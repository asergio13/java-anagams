package com.serge.abousaleh.anagram.service.impl;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
		try {
			boolean wordExists = checkWordExistsInDictionary(word, fileName);
			
			// Append to File
			if(!wordExists) {
				word = new StringBuilder(word).append("\r\n").toString();
				Files.write(
						Paths.get(fileName), 
						word.getBytes(), 
						StandardOpenOption.APPEND);
				result = true;
			}
		} catch (IOException e) {
			logger.error("Error when inserting a word in dictionary", e);
		}
		logger.debug("insertWordInDictionary - End");
		return result;
	}

	@Override
	public boolean deleteWordFromDictionary(String word, String fileName) {
		logger.debug("deleteWordFromDictionary - Start");
		boolean result = false;
		try {
			boolean wordExists = checkWordExistsInDictionary(word, fileName);
			// Delete to File
			if(wordExists) {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				 
				//String buffer to store contents of the file
				StringBuilder sb = new StringBuilder("");
	 
				//Keep track of the line number
				String line;
	 
				while((line=br.readLine()) != null)
				{
					//Store each valid line in the string buffer
					if(!line.equals(word))
						sb.append(line+"\n");
				}
				br.close();
	 
				FileWriter fw = new FileWriter(new File(fileName));
				//Write entire string buffer into the file
				fw.write(sb.toString());
				fw.close();
				
				result = true;
			}
		} catch (IOException e) {
			logger.error("Error when deleting a word in dictionary", e);
		}
		logger.debug("deleteWordFromDictionary - End");
		return result;
	}
	
	@Override
	public boolean checkWordExistsInDictionary(String word, String fileName) {
		logger.debug("checkWordExistsInDictionary - Start");
		boolean wordExists = false;
		if (word.length() > 1 && word.length() < Integer.MAX_VALUE) {
			Set<String> dictionary = AnagramUtility.getDictionaryContent(fileName);
			if (dictionary.contains(word)) {
				wordExists = true;
			}
		}
		logger.debug("checkWordExistsInDictionary - End");
		return wordExists;
	}

}
