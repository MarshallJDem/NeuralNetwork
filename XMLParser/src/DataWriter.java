import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataWriter {

	String data = "";
	String name = "";
	
	public DataWriter(){
	}/*
	public static void main(String[] args) throws IOException {
		DataWriter writer = new DataWriter();
		writer.setData("1427414400:4951&#124", "TestItem");
		writer.toFile();
		
	}*/
	public void setData(String l, String name){
		data = l;
		this.name = name;
	}
	public void toFile(){
		processData();
		writeData();
	}
	private void writeData(){
		PrintWriter outputStream = null;
		 try {
			 outputStream = new PrintWriter(new FileWriter("ItemPriceHistory.txt", true));
			 outputStream.println(name + ":" + data);
			 outputStream.close();
		 } 
         catch(Exception e){
        	 System.out.println("Error with method writeData in class DataWriter");
         }
	}
	private enum stage{
		SEARCHNUMBER, PROCESSNUMBER
	}
	stage currentStage = stage.SEARCHNUMBER;
	private void processData(){
		//1427414400:4951&#124;
		currentStage = stage.SEARCHNUMBER;
		String newData = "";
		int count = 0;
		for(int i =0; i < data.length(); i++){
			if(Character.isDigit(data.charAt(i)) && currentStage == stage.PROCESSNUMBER){
				newData += data.charAt(i);
			}
			else if(!Character.isDigit(data.charAt(i)) && currentStage == stage.PROCESSNUMBER){
				currentStage = stage.SEARCHNUMBER;
				newData +=",";
			}
			else if(data.charAt(i) == ':')
				currentStage = stage.PROCESSNUMBER;
			
		}
		data = newData;
	}
	
}
