package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
	
	public InputReader() {
		
	}
	
	public List<String> read(Class<?> clazz, String inputFile) throws IOException {
		List<String> inputs = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clazz.getResourceAsStream(inputFile)))) {
			String line;
		    while ((line = reader.readLine()) != null) {
		    	inputs.add(line);
		    }
			
		}
		
		return inputs;		
	}
	
	public String[] readLine(Class<?> clazz, String inputFile) throws IOException {		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clazz.getResourceAsStream(inputFile)))) {
			String line = reader.readLine();
		    String[] codes = line.split(",");
		    
		    return codes;
		}		
	}
	
	public List<String> readPassport(Class<?> clazz, String inputFile) throws IOException {
		List<String> inputs = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clazz.getResourceAsStream(inputFile)))) {
			String line;
	    	String fullLine = "";
	    	
		    while ((line = reader.readLine()) != null) {
		    	if (line.equals("")) {
			    	inputs.add(fullLine + " ");
			    	fullLine = "";
		    	} else {
		    		fullLine = fullLine + " " + line;
		    	}		    	
		    }
			
		}
		
		return inputs;		
	}
}
