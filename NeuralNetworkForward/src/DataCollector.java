import java.io.BufferedReader;
import java.io.FileReader;

public class DataCollector {

	public String getItemData(String name){
		
		BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader("ItemPriceHistory.txt"));
            String l;
            while ((l = inputStream.readLine()) != null) {
            	if(l.contains(name + ':')){
            		for(int i=0; i<l.length();i++){
            			if(l.charAt(i) == ':'){
            				return l.substring(i+1);
            			}
            		}
            		
            	}
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch(Exception e) { //In case the file doesn't exist or other file reading issues occur
        	System.out.println("ERROR: File could not be read! (Or there was an error YOU made)");
        	return "DATA ERROR";
        } 
    	System.out.println("ERROR: Never found item or it is corrupted!");
    	return "DATA ERROR";
	}
	public double[] dataToArray(String data){
		double[] arrayData;
		int numOfTerms = 1;
		for(int i=0; i<data.length(); i++){
			if(data.charAt(i) == ',')
				numOfTerms++;}
		arrayData = new double[numOfTerms];
		
		numOfTerms = 0;
		String sub = "";
		data += ','; //Just makes the looping easier
		double low =1000000000, high=-1; //Hehe this is a bit ghetto
		double sum = 0;
		for(int i=0; i<data.length(); i++){
			if(data.charAt(i) == ','){
				double num = Double.parseDouble(sub);
				sum += num;
				arrayData[numOfTerms] = num;
				if(num < low)
					low = num;
				if(num > high)
					high = num;
				numOfTerms++;
				sub = "";
			}
			else
				sub += data.charAt(i);
		}
		for(int i=0; i<numOfTerms; i++)
			arrayData[i] = (arrayData[i]-low)/(high-low);
			//arrayData[i] = (arrayData[i]-((high-low)/2))/((high-low)/2);
		System.out.println((high-low));
		System.out.println(low);
		return arrayData;
	}
	public double[] dataToUpDownArray(String data){
		double[] arrayData;
		int numOfTerms = 1;
		for(int i=0; i<data.length(); i++){
			if(data.charAt(i) == ',')
				numOfTerms++;}
		arrayData = new double[numOfTerms];
		
		numOfTerms = 0;
		String sub = "";
		double lastNum = 0;
		double low =1000000000, high=-1, negLow = 1000000000; //Hehe this is a bit ghetto
		data += ','; //Just makes the looping easier
		for(int i=0; i<data.length(); i++){
			if(data.charAt(i) == ','){
				double num = Double.parseDouble(sub);
				if(numOfTerms != 0){
					arrayData[numOfTerms-1] = num - lastNum;
				}
				if((num- lastNum) > high)
					high = (num- lastNum);
				if((num- lastNum) < low)
					low = (num- lastNum);
				lastNum = num;
				numOfTerms++;
				sub = "";
			}
			else
				sub += data.charAt(i);
		}
		for(int i=0; i<arrayData.length; i++)
			arrayData[i] = (arrayData[i]-low)/(high-low);
		System.out.println((high-low));
		System.out.println(low);
		return arrayData;
	}
	public double[][] dataToMatrix(double[] data, int setSize, int startIndex, int endIndex){
		double[][] newData = new double[endIndex + 2 - startIndex - (setSize)][setSize];
		for(int i =0; i<newData.length;i++){
			for(int j=0; j<setSize;j++){
				newData[i][j] = data[i+j+startIndex];
			}
		}
		return newData;
	}
	public double[][] toUpDownMatrix(double[]data, int setSize, int startIndex, int endIndex){
		double[][] newData = new double[endIndex-startIndex-setSize-1][setSize];
		for(int i =0; i<newData.length;i++){
			for(int j=0; j<setSize;j++){
				newData[i][j] = data[i+j+1+startIndex] - data[i+j+startIndex];
			}
		}
		return newData;
	}
}
