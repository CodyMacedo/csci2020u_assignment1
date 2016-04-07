/*
 * TestFile
 *
 * This class is used to organize the probability data that the calculation 
 * class spits out as well as the items used in the collection that goes into 
 * the ui
 * 
 * @author	CodyMacedo	Omar Khan
 * @id		100486136	100523629
 * @date	March 10, 2016
 *
 */


import java.text.DecimalFormat;

public class TestFile {
	private String filename;
	private double spamProbability;
	private String actualClass;
	
	public TestFile(String filename, double spamProbability, String actualClass) {
		this.filename = filename;
		this.spamProbability = spamProbability;
		this.actualClass = actualClass;
	}
	
	public String getFilename() {
		return this.filename;
	}
	
	public String getSpamProbability() {
		return getSpamProbRounded();
	}
	
	public String getSpamProbRounded() {
		DecimalFormat df = new DecimalFormat("0.00000");
		return df.format(this.spamProbability);
	}
	
	public String getActualClass() {
		return this.actualClass;
	}
	
	public void setFilename(String value) {
		this.filename = value;
	}
	
	public void setSpamProbability(double val) {
		this.spamProbability = val;
	}
	
	public void setActualClass(String value) {
		this.actualClass = value;
	}
}

	
