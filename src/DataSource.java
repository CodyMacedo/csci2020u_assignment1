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
	//initialize list for data collection

	public calculateData(File file) {
		
	}
	
	
	private void addData(String filename, double spamProbability, String actualClass) {
		data.add(new TestFile(filename, spamProbability, actualClass));
		//initalized condition to add Data like a new file  along with its details
	}
	
	public ObservableList<TestFile> getData() {
		return data;
	}
}
