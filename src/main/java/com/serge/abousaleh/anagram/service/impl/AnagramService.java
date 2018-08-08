package com.serge.abousaleh.anagram.service.impl;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.serge.abousaleh.anagram.domain.AnagramResult;
import com.serge.abousaleh.anagram.service.IAnagramService;
import com.serge.abousaleh.anagram.utils.AnagramUtility;

@Service
public class AnagramService implements IAnagramService {

	@Override
	public AnagramResult getAnagrams(String word, String fileName) {
		
		// Get Dictionary
		AnagramResult anagramResult = new AnagramResult();
		anagramResult.setAnagrams(AnagramUtility.getAnagramsFromDictionary(word, fileName));
		
		return anagramResult;
	}

	@Override
	public boolean insertWordInDictionary(String word, String fileName) {
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
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteWordFromDictionary(String word, String fileName) {
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
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean checkWordExistsInDictionary(String word, String fileName) {
		boolean wordExists = false;
		if (word.length() > 1 && word.length() < Integer.MAX_VALUE) {
			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
				
				wordExists = stream.anyMatch(line -> line.equals(word));
	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return wordExists;
	}

}
