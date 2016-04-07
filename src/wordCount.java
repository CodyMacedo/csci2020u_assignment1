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
    	File[] filesInDir = file.listFiles();
    	for (int i = 0; i < filesInDir.length; i++) {
			if (filesInDir[i].getName().contains("ham")) {
				File[] moreFiles = filesInDir[i].listFiles();
    			for (int q = 0; q < moreFiles.length; q++) {
					processFile(moreFiles[q], "ham");
				}
    		} else if (filesInDir[i].getName().contains("spam")) {
    			File[] moreFiles = filesInDir[i].listFiles();
    			for (int q = 0; q < moreFiles.length; q++) {
    				processFile(moreFiles[q], "spam");
    			}
    		}
    	}
    }
    
    public void processFile(File file, String type) throws IOException {
        if (file.exists()) {
            // load all of the data, and process it into words
            if (type.equals("ham")) {
		        hamNum++;
		    } else if (type.equals("spam")) {
		        spamNum++;
		    }
		    Map<String,Integer> thisFile = new TreeMap<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (isWord(word)) {
                	if (!(thisFile.containsKey(word))) {
                		thisFile.put(word, 1);

		            	if (trainHamFreq.containsKey(word) && type.equals("ham")) {
		            		trainHamFreq.put(word, trainHamFreq.get(word) + 1);
		            	} else if (trainSpamFreq.containsKey(word) && type.equals("spam")) {
		            		trainSpamFreq.put(word, trainSpamFreq.get(word) + 1);
		                } else {
		                	setWord(word, type);
		                }
		            }
                }
            }
        }
    }
    
    private void setWord(String word, String type) throws IOException{
        if (type.equals("ham")) {
        	trainHamFreq.put(word, 1);
		} else if (type.equals("spam")) {
        	trainSpamFreq.put(word, 1);
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
    		PrS = (double)trainSpamFreq.get(arr[i]) / spamNum;
    		if (trainHamFreq.containsKey(arr[i])){
    			PrH = (double)trainHamFreq.get(arr[i]) / hamNum;
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












