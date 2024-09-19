//Joshua Jacob
//115732401
//Recitation 3

package hw3;

import java.util.Stack;
import java.util.Scanner;

/*
 * this class is the main class which runs the code
 * this class also contains methods which run based off the user input
 */

public class MailroomManager {
	
	
	 static Stack<Package> stack1 = new Stack<Package>();
	 static Stack<Package> stack2 = new Stack<Package>();
	 static Stack<Package> stack3 = new Stack<Package>();
	 static Stack<Package> stack4 = new Stack<Package>();
	 static Stack<Package> stack5 = new Stack<Package>();
	 static Stack<Package> floor = new Stack<Package>();
	 
	 
	 static int arrivalDate = 0;

	 public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		

		
		String[] choices = {
				"D) Deliver a package", 
				"G) Get someone's package",
				"T) Make it tomorrow",
				"P) Print the stacks",
				"M) Move a package from one stack to another",
				"F) Find packages in the wrong stack and move to floor",
				"L) List all packages awaiting a user",
				"E) Empty the floor.",
				"Q) Quit.",
				};
		
		System.out.println("Welcome to the Irving Mailroom Manager. "
				+ "You can try to make it better, but the odds are stacked against you. It is day 0");
		do {
			System.out.println("\nMenu: ");		
			for (int i = 0; i < choices.length; i++) {
				System.out.println((i < choices.length-1 ? i+1 : 0) + ". " + choices[i]);
			}			
			
			System.out.println("Please select an option: ");
			char choice = input.next().charAt(0);
			
			
			choice = Character.toLowerCase(choice);
			
			switch (choice) {
			case 'd': 
					deliverPackage();
				break;
			case 'g':
				try {
					getPackage();
				} catch (EmptyStackException e) {
					System.out.println("Empty Stack therefore cannot get packages.");
				}
				break;
			case 't': 
				makeTomorrow();
				break;
			case 'p':
				printStacks();
				break;
			case 'm':
				try {
					movePackage();
				} catch (EmptyStackException e) {
					System.out.println("Source stack is empty. Cannot move a package.");
				}
				break;
			case 'f':
				findWrongPackage();
				break;
			case 'l':
				listPackages();
				break;
			case 'e':
				emptyFloor();
				break;
			case 'q': 
				System.out.println("Use Amazon Locker next time.");
				System.out.println("(A-G, H-J, K-M, N-R, S-Z)");
				System.exit(0);
			default:
				System.out.println("Invalid choice. Try again.");
			}
		} while (true);
}
	
	/**
	 * this method is used when the user wants to add a new package
	 * its does this by checking the first character of the persons name and then adding it to the stack that they belong too
	 */
	
	public static void deliverPackage() {
		
		
	System.out.println("Please enter the recipient name:");
	String name = input.next();
	
	System.out.println("Please enter the weight (lbs):");
	double weight = input.nextDouble();
	
	int dateArrived = arrivalDate;
	
	Package newPackage = new Package(name, dateArrived , weight);
	
	
	if (checkFirstLetter(name) == 1) {
		stack1.push(newPackage);
	} else if (checkFirstLetter(name) == 2) {
		stack2.push(newPackage);
	}  else if (checkFirstLetter(name) == 3) {
		stack3.push(newPackage);
	}  else if (checkFirstLetter(name) == 4) {
		stack4.push(newPackage);
	}  else if (checkFirstLetter(name) == 5) {
		stack5.push(newPackage);
	}
	
	 if (weight == (int) weight) {

         System.out.printf("A %.0f lb package is awaiting pickup by " + name, weight);
     } else {
         System.out.println("A " + weight + " lb package is awaiting pickup by" + name);
     }
 }
	
	/**
	 * this method is used to retrieve the most recent package from the person that the user inputed
	 * it does this by moving all elements above that to the floor and then popping the package and then returning all those elements back
	 * @throws EmptyStackException for when there are no packages in the stack
	 */
	
	public static void getPackage() throws EmptyStackException {
	
		System.out.println("Please enter the recipient name:");
		String name = input.next();
		
	    Package remove = null;
	    Stack<Package> stack = null;
	    
	    if (checkFirstLetter(name) == 1) {
	    	stack = stack1;
	    } else if (checkFirstLetter(name) == 2) {
	    	stack = stack2;
	    } else if (checkFirstLetter(name) == 3) {
	    	stack = stack3;
	    } else if (checkFirstLetter(name) == 4) {
	    	stack = stack4;
	    } else if (checkFirstLetter(name) == 5) {
	    	stack = stack5;
	    }
	    
	    if (stack.isEmpty() == true) {
			throw new EmptyStackException("");
		}
	    	
	    for (int i = 0; i < stack.size(); i++) {
	        Package pack = stack.get(i);
	        if (pack.getRecipient().equalsIgnoreCase(name)) {
	        	remove = pack;
	        }
	    }
	    
	    
	    int distance = stack.search(remove) - 1;
	    
	    System.out.println("Move " + distance + " packages from Stack 1 to floor.");
	    for (int i = 0; i < distance; i++) {
	        floor.push(stack.pop());
	    }
	    printStacks();
	    
	    
	    if (remove.getWeight() == (int) remove.getWeight()) {
	     System.out.printf("Give " + name + " %.0f lb package delivered on day " + remove.getArrivalDate() + ".",remove.getWeight());
	    }  else {
	    	System.out.println("Give " + name + " " + remove.getWeight() + "package delivered on day " + remove.getArrivalDate() + ".");
	    }

	     
	    System.out.println("Return " + distance + " packages to stack " + checkFirstLetter(name) +"from floor.");
	    
	    stack.pop();
	    
	    for (int i = 0; i < distance; i++) {
	    stack.push(floor.pop());
	    	
	    }
	    
	    printStacks();
	  }
	    
	
	/**
	 * this method is used to add 1 to the day
	 */
	public static void makeTomorrow() {
		
		arrivalDate++;
		
		System.out.println("It is now day " + arrivalDate + ".");
	}
	
	/**
	 * this method is used to print out each of the stacks
	 * it does this by running a loop in order to print out each element of the stack until it reaches the size of the stack
	 */
	public static void printStacks() {
		
		System.out.println("Current Packages:");
		System.out.println("--------------------------------");
		
		if (stack1.isEmpty()) {
	        System.out.println("Stack 1 (A-G):|empty.");
	    } else {
	        	System.out.print("Stack 1 (A-G):|");
	        	for (int i = 0; i < stack1.size(); i++) {
	        	    Package pack = stack1.get(i);
	        	    System.out.print("[" + pack.getRecipient() + " " + pack.getArrivalDate() + "]");
	        	}
	    }
		
		System.out.println();
		
		if (stack2.isEmpty()) {
	        System.out.println("Stack 2 (H-J):|empty.");
	    } else {
	    	System.out.print("Stack 2 (H-J):|");
        	for (int i = 0; i < stack2.size(); i++) {
        	    Package pack = stack2.get(i);
        	    System.out.print("[" + pack.getRecipient() + " " + pack.getArrivalDate() + "]");
        	}
    }
		
		if (stack3.isEmpty()) {
	        System.out.println("Stack 3 (K-M):|empty.");
	    } else {
	    	System.out.print("Stack 3 (K-M):|");
        	for (int i = 0; i < stack3.size(); i++) {
        	    Package pack = stack3.get(i);
        	    System.out.print("[" + pack.getRecipient() + " " + pack.getArrivalDate() + "]");
        	}
    }
		
		if (stack4.isEmpty()) {
	        System.out.println("Stack 4 (N-R):|empty.");
	    } else {
	    	System.out.print("Stack 4 (N-R):|");
        	for (int i = 0; i < stack4.size(); i++) {
        	    Package pack = stack4.get(i);
        	    System.out.print("[" + pack.getRecipient() + " " + pack.getArrivalDate() + "]");
        	}
    }
		
		if (stack5.isEmpty()) {
	        System.out.println("Stack 5 (S-Z):|empty.");
	    } else {
	    	System.out.print("Stack 5 (S-Z):|");
        	for (int i = 0; i < stack5.size(); i++) {
        	    Package pack = stack5.get(i);
        	    System.out.print("[" + pack.getRecipient() + " " + pack.getArrivalDate() + "]");
        	}
    }
		
		if (floor.isEmpty()) {
	        System.out.println("Floor: |empty.");
	    } else {
	    	for (int i = 0; i < floor.size(); i++) {
        	    Package pack = floor.get(i);
        	    System.out.print("[" + pack.getRecipient() + " " + pack.getArrivalDate() + "]");
        	}
    }	
		
	}
	
	
	/**
	 * this method is used to move one package from one stack to another
	 * it does this by checking which stack the user wants to be the source and destination and then pushing 
	 * the popped element from the source stack onto the destination stack
	 * @throws EmptyStackException for when there are no packages in the source stack
	 */
	public static void movePackage() throws EmptyStackException {
		
		
		System.out.println("Please enter the source stack (enter 0 for floor): ");
		int sourceStack = input.nextInt();
		
		System.out.println("Please enter the destination stack: ");
		int destinationStack = input.nextInt();
		
		Stack<Package> source = null;
		Stack<Package> destination = null;
		
		
		if (sourceStack == 0) {
			source = floor;
		} else if (sourceStack == 1) {
			source = stack1;
		} else if (sourceStack == 2) {
			source = stack2;
		} else if (sourceStack == 3) {
			source = stack3;
		} else if (sourceStack == 4) {
			source = stack4;
		} else if (sourceStack == 5) {
			source = stack5;
		}
		
		if (source.isEmpty() == true) {
			throw new EmptyStackException("");
		}
			
		
		if (destinationStack == 0) {
			destination = floor;
		} else if (destinationStack == 1) {
			destination = stack1;
		} else if (destinationStack == 2) {
			destination = stack2;
		} else if (destinationStack == 3) {
			destination = stack3;
		} else if (destinationStack == 4) {
			destination = stack4;
		} else if (destinationStack == 5) {
			destination = stack5;
		}
		
		destination.push(source.pop());
	
		
		System.out.println("Package moved successfully from stack " + sourceStack + " to stack " + destinationStack);
}
	
	/**
	 * this method finds any package that is not in the right stack and then pushes them onto the floor
	 * it does this by checking through each stack and finding each element that is not in the alphabetical range for that stack
	 */
	public static void findWrongPackage() {
		
		System.out.println("Misplaced packages moved to floor.");
		
		
		for (int i = 0; i < stack1.size(); i++) {
	        Package pack = stack1.get(i);
	        char recipient = pack.getRecipient().toLowerCase().charAt(0);
	  
	        if (recipient < 'a' || recipient > 'g' ) {
	        	
	        	floor.push(stack1.remove(i));
	            i--;  	
	        }
		}
		
		for (int i = 0; i < stack2.size(); i++) {
	        Package pack = stack2.get(i);
	        char recipient = pack.getRecipient().toLowerCase().charAt(0);
	        if (recipient < 'h' || recipient > 'j' ) {
	        	
	        	floor.push(stack2.remove(i));
	            i--;   	
	        }
		}
		
		for (int i = 0; i < stack3.size(); i++) {
	        Package pack = stack3.get(i);
	        char recipient = pack.getRecipient().toLowerCase().charAt(0);
	        if (recipient < 'k' || recipient > 'm' ) {
	        	
	        	floor.push(stack3.remove(i));
	            i--;	
	        }
		}
		
		for (int i = 0; i < stack4.size(); i++) {
	        Package pack = stack4.get(i);
	        char recipient = pack.getRecipient().toLowerCase().charAt(0);
	        if (recipient < 'n' || recipient > 'r' ) {
	        	
	        	floor.push(stack4.remove(i));
	            i--; 	
	        }
		}
		
		for (int i = 0; i < stack5.size(); i++) {
	        Package pack = stack5.get(i);
	        char recipient = pack.getRecipient().toLowerCase().charAt(0);
	        if (recipient < 's' || recipient > 'z' ) {
	        	
	        	floor.push(stack5.remove(i));
	            i--;
	        	
	        }
		}
		
		
		
	}
	
	/**
	 * this method is used to list each of the packages for the person inputed by the user
	 * it does this by checking the first letter so it knows which stack to run through
	 * it then loops through that stack to see when the person name is there
	 */
	public static void listPackages() {
		
		System.out.println("Please enter the recipient name: ");
		String name = input.next();
		
		int packageCount = 0;
		Stack<Package> stack = null;
		
		if (checkFirstLetter(name) == 1) {
	    	stack = stack1;
	    } else if (checkFirstLetter(name) == 2) {
	    	stack = stack2;
	    } else if (checkFirstLetter(name) == 3) {
	    	stack = stack3;
	    } else if (checkFirstLetter(name) == 4) {
	    	stack = stack4;
	    } else if (checkFirstLetter(name) == 5) {
	    	stack = stack5;
	    }
		
			for (int i = 0; i < stack.size(); i++) {
		        Package pack = stack.get(i);
		        if (pack.getRecipient().equalsIgnoreCase(name)) {
		        	
		        	packageCount++;
		        }
			}
			
		if (packageCount > 0) {	
		System.out.println(name + " has " + packageCount +  " package total.");
			
			int count = 1;
	        for (int i = 0; i < stack.size(); i++) {
	            Package pack = stack.get(i);
	            if (pack.getRecipient().equalsIgnoreCase(name)) {
	            	
	            	if (pack.getWeight() == (int) pack.getWeight()) {
	            		 System.out.printf("Package " + count + " is in Stack " + checkFirstLetter(name) + ", it was delivered on day "  + pack.getArrivalDate() 
	 	                + ", and weighs %.0f lbs",pack.getWeight());
	           	    }  else {
	           	     System.out.println("Package " + count + " is in Stack " + checkFirstLetter(name) + ", it was delivered on day "  + pack.getArrivalDate() 
		                + ", and weighs " + pack.getWeight() + " lbs");
	           	    }
	                count++;
	            }
	        }
		} else {
			System.out.println("No packages found for " + name + ".");
		}
}		
		
	/**
	 * this method is used to wipe the floor
	 * it does this by removing all the elements from the stack until the floor isEmpty is true
	 */
	public static void emptyFloor() {
		
		System.out.println("The floor has been emptied. Mr. Trash Can is no longer hungry.");
		
		while(floor.isEmpty() != true) {
		floor.pop();
		}	
	}

	/**
	 * this method is used to check the first character of the inputed name 
	 * @param word is the name that the method is checking
	 * @return a number from 1 - 5 which helps me when selecting which stack I have to add the elements into
	 */
	public static int checkFirstLetter(String word) {
		
		String letter = word.toLowerCase();	
		char character = letter.charAt(0);
	
	if (character >= 'a' &&  character <= 'g') {
	 return 1;	
	} else if (character >= 'h' &&  character <= 'j') {
		 return 2;	
	} else if (character >= 'k' &&  character <= 'm') {
		 return 3;	
	} else if (character >= 'n' &&  character <= 'r') {
		 return 4;	
	} else 
		return 5;
	}	
	
}	

