//Joshua Jacob
//115732401
//Recitation 3

package hw3;

/*
 * this is a custom exception for when the stack is empty
 */
public class EmptyStackException extends Throwable {

	public EmptyStackException(String string) {
		super(string);
	}
}
