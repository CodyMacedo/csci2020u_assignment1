import java.io.*;
import java.util.*;
import java.lang.Math;

public class wordCount {
    private Map<String,Integer> trainHamFreq;
    private Map<String,Integer> trainSpamFreq;
    private Map<String,Double> spamProb;
    private int spamNum;
    private int hamNum;
    
    public wordCount() {
        trainHamFreq = new TreeMap<>();
        trainSpamFreq = new TreeMap<>();
        spamProb = new TreeMap<>();
        spamNum = 0;
    	hamNum = 0;
    }
    
    public void processFolder(File file) throws IOException {
    	int countHam = 0;
    	int countSpam = 0;
    	File[] filesInDir = file.listFiles();
    	for (int i = 0; i < filesInDir.length; i++) {
			if (filesInDir[i].getName().contains("ham")) {
				countHam++;
				File[] moreFiles = filesInDir[i].listFiles();
    			for (int q = 0; q < moreFiles.length; q++) {
					processFile(moreFiles[q], "ham", countHam);
				}
    		} else if (filesInDir[i].getName().contains("spam")) {
    			countSpam++;
    			File[] moreFiles = filesInDir[i].listFiles();
    			for (int q = 0; q < moreFiles.length; q++) {
    				processFile(moreFiles[q], "spam", countSpam);
    			}
    		}
    	}
    }
    
    public void processFile(File file, String type, int count) throws IOException {
        if (file.exists()) {
            // load all of the data, and process it into words
            if (type.equals("ham")) {
		        hamNum++;
		    } else if (type.equals("spam")) {
		        spamNum++;
		    }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (isWord(word)) {
                    countWord(word, type, count);
                }
            }
        }
    }
    
    private void countWord(String word, String type, int count) throws IOException{
        if (type.equals("ham")) {
			if (trainHamFreq.containsKey(word)) {
            	int oldCount = trainHamFreq.get(word);
            	if (oldCount < count) {	
            		trainHamFreq.put(word, oldCount + 1);
            	}
        	} else {
        	    trainHamFreq.put(word, 1);
       		}
		} else if (type.equals("spam")) {
			if (trainSpamFreq.containsKey(word)) {
            	int oldCount = trainSpamFreq.get(word);
            	if (oldCount < count) {	
            		trainSpamFreq.put(word, oldCount + 1);
            	}
        	} else {
        	    trainSpamFreq.put(word, 1);
       		}
		}
    }
    
    private boolean isWord(String str){
        String pattern = "^[a-zA-Z]*$";
        if (str.matches(pattern)){
            return true;
        }
        return false;   
    }
    
    
    
    public void calculateWordProb() throws IOException{
    	String[] arr = trainSpamFreq.keySet().toArray(new String[trainSpamFreq.size()]);
    	double PrH = 0.0;
    	double PrS = 0.0;
    	double PrW = 0.0;
    	for (int i = 0; i < trainSpamFreq.size(); i++) {
    		PrS = trainSpamFreq.get(arr[i]) / spamNum;
    		if (trainHamFreq.containsKey(arr[i])){
    			PrH = trainHamFreq.get(arr[i]) / hamNum;
    		} else {
    			PrH = 0;
    		}
    		PrW = PrS / (PrS + PrH);
    		spamProb.put(arr[i], PrW);
    	}
    }
    		
    
    
    
    public double testProb(File file) throws IOException {
    	double n = 0.0;
    	if (file.exists()) {
            // load all of the data, and process it into words
            Scanner scanner = new Scanner(file);
            
            
            
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (isWord(word)) {
                    if (spamProb.containsKey(word)) {
                    	n = n + (Math.log(1 - spamProb.get(word)) - Math.log(spamProb.get(word)));
                	} 
                }
            }
            
        }
        return n;
   }
    	
}












