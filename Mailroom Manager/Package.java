//Joshua Jacob
//115732401
//Recitation 3

package hw3;

/**
 * This class contains the constructor, the setters, and getters for recipient, arrivalDate and weight
 */
public class Package {
	
	private String recipient;
	private int arrivalDate;
	public double weight;
	
	/**
	 * This is the constructor
	 * @param recipient: for the name of the person as a String
	 * @param arrivalDate: takes in the day that they arrived as an int
	 * @param weight: takes in the weight of the package as a double
	 */
	public Package(String recipient, int arrivalDate, double weight) {
		this.recipient = recipient;
		this.arrivalDate = arrivalDate;
		this.weight= weight;
	}

	/*
	 * getters and setters for Recipient, arrivalDate, weight
	 */
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public int getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(int arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
