import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import javax.lang.model.element.VariableElement;

public class Main {

	public static void main(String[] args) {
		
		String textFile = readFile("text.txt");

		System.out.println(countAllWord(textFile));
	}
	
	public static int countAllWord(String text){
		int count = 0;
		
		char[] textAsCharArray = text.toCharArray();
		for (int i = 0; i < textAsCharArray.length; i++) {
			if (textAsCharArray[i] == ' ') {
				count++;
				while (textAsCharArray[i] == ' ' && i < textAsCharArray.length - 1) {
					i++;
				}
			}
		}
		
		return count;
	}
	
	public static String readFile(String filename)
	{
	   String content = null;
	   File file = new File(filename);
	   try {
	       FileReader reader = new FileReader(file);
	       char[] chars = new char[(int) file.length()];
	       reader.read(chars);
	       content = new String(chars);
	       reader.close();
	   } catch (IOException e) {
	       e.printStackTrace();
	   }
	   
	   return content;
	}
}
