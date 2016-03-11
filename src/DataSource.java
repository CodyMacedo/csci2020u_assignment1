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

public class DataSource {
	
	public ObservableList<TestFile> data = FXCollections.observableArrayList();	
	wordCount counter = new wordCount();
	
	public void calculateData(File file) throws IOException{
		
		
		
			File tempFile = new File(file.toString() + "train");
			counter.processFolder(tempFile);
		
			counter.calculateWordProb();
		
			double tempProb = 0.0;
			double reallyProb = 0.0;
		
			File testSpam = new File(file.toString() + "test/spam");
			File[] spamFiles = testSpam.listFiles();
		
			for (int x = 0; x < spamFiles.length; x++) {
				tempProb = counter.testProb(spamFiles[x]);
				reallyProb = 1 / (1 + Math.pow(Math.E, tempProb));
				addData(spamFiles[x].getName(), reallyProb, "Spam");
			}
		
		
		
			File testHam = new File(file.toString() + "test/ham");
			File[] hamFiles = testHam.listFiles();
		
			for (int y = 0; y < hamFiles.length; y++) {
				tempProb = counter.testProb(hamFiles[y]);
				reallyProb = 1 / (1 + Math.pow(Math.E, tempProb));
				addData(hamFiles[y].getName(), reallyProb, "Ham");
			}
		
		
	}
	
	
	private void addData(String filename, double spamProbability, String actualClass) {
		data.add(new TestFile(filename, spamProbability, actualClass));
	}
	
	public ObservableList<TestFile> getData() {
		return data;
	}
}
