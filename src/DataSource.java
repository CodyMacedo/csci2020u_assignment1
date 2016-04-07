/*
 * DataSource
 *
 * This class uses an observable list as a collection to store the individual
 * TestFile objects. The resulting collection will be the input for the ui table
 * 
 * @author	CodyMacedo	Omar Khan
 * @id		100486136	100523629
 * @date	March 10, 2016
 *
 */

import java.io.*;
import java.util.*;
import javafx.collections.*;
import java.lang.Math;

public class DataSource {

	private int correctGuesses = 0;
	private int fileTotal = 0;
	
	private int spamGuesses = 0;
	private int correctSpam = 0;
	
	public ObservableList<TestFile> data = FXCollections.observableArrayList();	
	wordCount counter = new wordCount();
	
	public void calculateData(File file) throws IOException{
			
			File trainFolder = new File(file.getPath() + "/train");
			counter.processFolder(trainFolder);
		
			counter.calculateWordProb();
		
			double tempProb = 0.0;
			double reallyProb = 0.0;
		
			File testSpam = new File(file.getPath() + "/test/spam");
			File[] spamFiles = testSpam.listFiles();
		
			for (int x = 0; x < spamFiles.length; x++) {
				fileTotal++;
				tempProb = counter.testProb(spamFiles[x]);
				reallyProb = 1 / (1 + Math.pow(Math.E, tempProb));
				if (reallyProb >= 1) {
					correctSpam++;
					spamGuesses++;
					correctGuesses++;
				}
				addData(spamFiles[x].getName(), reallyProb, "Spam");
			}
		
		
		
			File testHam = new File(file.getPath() + "/test/ham");
			File[] hamFiles = testHam.listFiles();
		
			for (int y = 0; y < hamFiles.length; y++) {
				fileTotal++;
				tempProb = counter.testProb(hamFiles[y]);
				reallyProb = 1 / (1 + Math.pow(Math.E, tempProb));
				if (reallyProb < 1) {
					correctGuesses++;
				} else {
					spamGuesses++;
				}
				addData(hamFiles[y].getName(), reallyProb, "Ham");
			}
		
		
	}
	
	public String getPrecision(){
		return Double.toString((double)correctSpam/spamGuesses);
	}
	
	public String getAccuracy(){
		return Double.toString((double)correctGuesses/fileTotal);
	}
	
	private void addData(String filename, double spamProbability, String actualClass) {
		data.add(new TestFile(filename, spamProbability, actualClass));
	}
	
	public ObservableList<TestFile> getData() {
		return data;
	}
}
