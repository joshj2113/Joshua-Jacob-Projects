//Joshua Jacob
//115732401
//Recitation 3


package hw3;

/**
 * this is the custom exception for when the stack reaches the capacity(7)
 */
public class FullStackException extends Throwable{

	public FullStackException(String string) {
		super(string);
	}
	
}
