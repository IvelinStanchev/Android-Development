import java.util.Date;


public class MovieProduct {
	private double price;
	private String name;
	private Date expirationDate;
	private boolean isFree;
	
	public MovieProduct(String name, double price){
		this.name = name;
		this.price = price;
		this.expirationDate = new Date();
		isFree = true;
	}
	
	public void setAvailability(boolean isFree){
		this.isFree = isFree;
	}
	
	public boolean isFree(){
		return this.isFree;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public Date getExpirationDate(){
		return this.expirationDate;
	}
	
	public void setExpirationDate(Date newDate){
		this.expirationDate = newDate;
	}
}
