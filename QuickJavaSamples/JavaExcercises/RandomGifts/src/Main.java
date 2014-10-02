import java.util.Random;


public class Main {

	public static void main(String[] args) {
		String[] gifts = new String[]{ "pesho", "gosho", "kiro" };
		
		Random random = new Random();
		System.out.println(gifts[random.nextInt(3)]);
	}
}
