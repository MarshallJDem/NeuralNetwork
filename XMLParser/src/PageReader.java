
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class PageReader {

    ArrayList<String> file1 = new ArrayList<String>();
    ArrayList<String> itemNames = new ArrayList<String>();
    
    private static stage currentStage = stage.SEARCHDATA;
    
    
    
    
    public enum stage{
    	SEARCHDATA, PROCESSDATA
    }
    
	public static void main(String[] args) throws IOException {
		getAllHistory();
		//getItemNames();
		/*
		PrintWriter outputStream = null;
		 try {
			 outputStream = new PrintWriter(new FileWriter("ItemPriceHistory.txt", true));
			 outputStream.println("test");
			 outputStream.close();
		 } 
        catch(Exception e){
       	 System.out.println("Error with method writeData in class DataWriter");
        }*/

			System.out.println("Done");
		
	}
	public static void getAllHistory() throws IOException{
		PageReader reader = new PageReader();
		DataWriter writer = new DataWriter();
		String rawData = "";
		String url = "http://2007.runescape.wikia.com/wiki/Exchange:";
			 BufferedReader inputStream = null;
		            inputStream = new BufferedReader(new FileReader("ItemNamesURL.txt"));
		            String l;
		            
		            while ((l = inputStream.readLine()) != null) {
		            	String name = "";
		            	for(int i = 0; i < l.length(); i++){
		            		if(l.charAt(i) == ',')
		            			break;
		            		else if(l.charAt(i) == ' ')
		            			name += "_";
		            		else if(l.charAt(i) == '\'')
		            			name += "%27";
		            		else if(l.charAt(i) == '&'){
		            			name += "%26";
		            			i+=4;
		            		}
		            		else
		            			name += l.charAt(i);
		            	}
		            	rawData = reader.parseURL(url + name);
		            	if(!rawData.equals("DNE")){
		        		writer.setData(rawData, name);
		        		writer.toFile();
		        		System.out.println(name);}
		            	else{
		            		System.out.println("DNE: " + name);
		            	}
		            }
	}
	
	
	private stage examineLine(String l){
		if(l.contains("data-data=\"") && currentStage == stage.SEARCHDATA)
			return stage.PROCESSDATA;
		return currentStage;
	}
    public String parseURL(String url) throws IOException {
    	 currentStage = stage.SEARCHDATA;
	     URL link = new URL(url);
	     URLConnection connection = link.openConnection();
	     BufferedReader in;
	     try{
	     in = new BufferedReader(new InputStreamReader(
	    		 connection.getInputStream(), "UTF-8"));}
	     catch(Exception FileNotFoundException){
	    	 return "DNE";
	     }
	     String l;
	     while ((l = in.readLine()) != null){
	    	 currentStage = examineLine(l);
	    	 if(currentStage == stage.PROCESSDATA){
		         in.close();
	    		 return cleanupData(l);
	    	 }
	     }
	     in.close();
	     return "ERROR - COULD NOT FIND DATA FROM PARSING";
     }
	 private String cleanupData(String l){
		 l = l.trim();
		 String holder = "";
		 boolean foundData = false;
		 for(int i = 0; i<l.length(); i++){
			 if(holder.contains("data-data=\"")){
				 holder = "" + l.charAt(i);
				 foundData = true;
			 }
			 else if(foundData && l.charAt(i) == '\"'){
				 return holder;
			 }
			 else{
				 holder += l.charAt(i);
			 }
		 }
		 return "ERROR TRIMMING DATA";
	 }
	 public static void getItemNames(){
		 PrintWriter outputStream = null;
		 try {
			 outputStream = new PrintWriter(new FileWriter("ItemNamesURL.txt", true));
			 BufferedReader inputStream = null;
			 try {
		            inputStream = new BufferedReader(new FileReader("output.txt"));
		            String l;
		            while ((l = inputStream.readLine()) != null) {
		            	String name = "";
		            	for(int i = 0; i < l.length(); i++){
		            		if(l.charAt(i) == ',')
		            			break;
		            		else if(l.charAt(i) == ' ')
		            			name += "_";
		            		else
		            			name += l.charAt(i);
		            	}
		            	System.out.println(name);
		            	outputStream.println(name);
		            }
			 }catch(Exception e){
				 System.out.println("Error in read block");
			 }
		 }
		 catch(Exception e){
				 System.out.println("Error in write block");
		 }
		 
	 }
}