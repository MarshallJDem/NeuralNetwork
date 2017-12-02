
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class SimpleReader {

    ArrayList<String> file1 = new ArrayList<String>();
    ArrayList<String> itemNames = new ArrayList<String>();
    
    private stage currentStage = stage.SEARCHITEM;
    
    
    
    
    public enum stage{
    	SEARCHITEM, BEGAN, SEARCHPRICE, FOUNDPRICE, SEARCHDATE, FOUNDDATE
    }
    
	public static void main(String[] args) throws IOException {
		SimpleReader reader = new SimpleReader();
		reader.processFile();
	}
	public void processFile() throws IOException {
		
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileWriter("output.txt"));
           
			BufferedReader inputStream = null;
	        try {
	            inputStream = new BufferedReader(new FileReader("07current.txt"));
	            String l;
	        	int price = 0;
	        	String name = "ERROR";
	        	String date = "ERROR";
	            while ((l = inputStream.readLine()) != null) {
	            	currentStage = examineLine(l);
	            	switch(currentStage){
	            	case FOUNDPRICE:
	            		price = findPrice(l);
	            		break;
	            	case BEGAN:
	            		name = findName(l);
	            		break;
	            	case FOUNDDATE:
	            		date = findDate(l);
	            		outputStream.println(name + "," + price + "<" + date + ">");
	            		break;
	            	}
	            }
	            if (inputStream != null) {
	                inputStream.close();
	            }
	        } catch(Exception e) { //In case the file doesn't exist or other file reading issues occur
	        	System.out.println("File could not be read! (Or there was an error YOU made)");
	        	return;
	        } 
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        System.out.println("Done");
	}
	public stage examineLine(String l){
		if(l.contains(">Module:Exchange/") && !l.contains("Data") && currentStage == stage.SEARCHITEM)
			return stage.BEGAN;
		if(l.contains("price      =") && currentStage == stage.SEARCHPRICE)
			return stage.FOUNDPRICE;
		if(l.contains("date       =") && currentStage == stage.SEARCHDATE)
			return stage.FOUNDDATE;
		if(currentStage == stage.BEGAN)
			return stage.SEARCHPRICE;
		if(currentStage == stage.FOUNDPRICE)
			return stage.SEARCHDATE;
		if(currentStage == stage.FOUNDDATE)
			return stage.SEARCHITEM;
		return currentStage;
	}
	public int findPrice(String l){
		boolean isNumber = false;
		String number = "";
		for(int i = 0; i< l.length(); i++){
			if(l.charAt(i) == ','){
				number = number.trim();
				return Integer.parseInt(number);
			}
			if(l.charAt(i) == '=')
				isNumber = true;
			else if(isNumber)
				number += l.charAt(i);
		}
		return 0;
	}
	public String findName(String l){
		boolean isName = false;
		String name = "";
		for(int i = 0; i< l.length(); i++){
			if(l.charAt(i) == '<' && isName)
				return name;
			if(l.charAt(i) == '/')
				isName = true;
			else if(isName)
				name += l.charAt(i);
		}
		return "ERROR-NAME";
	}
	public String findDate(String l){
		boolean isDate = false;
		String date = "";
		for(int i = 0; i< l.length(); i++){
			if(l.charAt(i) == '\'' && isDate){
				date = date.trim();
				return date;
			}
			if(l.charAt(i) == '\'')
				isDate = true;
			else if(isDate)
				date += l.charAt(i);
		}
		return "ERROR-DATE";
	}

}
