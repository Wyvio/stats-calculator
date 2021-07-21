import java.util.Scanner;

public class Main {

	public static void main(String[] args) {	
		// Initialize helpful objects
		Scanner console = new Scanner(System.in);
		
		// Initialize the window
		Program program = new Program();
		
		Distribution question1 = new Distribution();
		question1.getData(console);
		question1.solve();		
		System.out.println(question1);
	}
	
}
