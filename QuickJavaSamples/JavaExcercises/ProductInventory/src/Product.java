import java.util.Random;


public class Product {
	private static Random random = new Random();
	
	private double price;
	private int id;
	private int quantity;
	
	public Product(double price, int quantity){
		this.price = price;
		this.quantity = quantity;
		this.id = random.nextInt(2000000000) + 1;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public double getId(){
		return this.id;
	}
	
	public double getQuantity(){
		return this.quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
}
