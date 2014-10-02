import java.util.Scanner;

import javax.lang.model.element.VariableElement;


public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String word = in.nextLine();
		//String word = "pesho";
		
		String reversedWord = Reverse(word);
		System.out.println(reversedWord);
	}

	
	public static String Reverse(String word){
		String reversed = "";
		char[] wordAsArray = word.toCharArray();
		for (int i = word.length() - 1; i >= 0; i--) {
			reversed += wordAsArray[i];
		}
		
		return reversed;
	}
}
