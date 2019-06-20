package topica3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Reader {	
	
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        LinkedList<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> word1, Map.Entry<String, Integer> word2) 
            { 
                return (word2.getValue()).compareTo(word1.getValue()); 
            } 
        }); 
          
        // put data from sorted list into hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> word : list) { 
            temp.put(word.getKey(), word.getValue()); 
        } 
        return temp; 
    } 
	
	public static void main(String[] args) {
		double start = System.currentTimeMillis();
		Map<String,Integer> word = new HashMap<String,Integer>();
		try (FileReader reader = new FileReader("src/topica3/text.txt");
				 BufferedReader buffer = new BufferedReader(reader)){
			String line;
			while( (line = buffer.readLine())!= null) {				
				String [] splitString = line.trim().split("\\W+");
				for(String temp : splitString) {
					if(temp.length()>0) {
						if(word.containsKey(temp))
							word.put(temp, word.get(temp)+1);
						else
							word.put(temp, 1);
					}
				}
			}	
			for (Map.Entry<String, Integer> temp2 : sortByValue((HashMap<String, Integer>) word).entrySet()) 
	           System.out.println("Number: "+temp2.getValue()+ "\t|| Word: "+temp2.getKey());
		}
		
		catch(IOException e){
				System.out.println("Can't read the file");
		}
		double end =System.currentTimeMillis();
		System.out.println(end-start + " ms");
	}
}
