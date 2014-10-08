package com.example.usingfragments;

public class Product {
	private int id;
	private String name;
	private String category;
	private int quantity;
	private double price;
	
	public Product(String name, String category, int quantity, double price, int id){
		this.setName(name);
		this.setCategory(category);
		this.setQuantity(quantity);
		this.setPrice(price);
		this.setId(id);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
