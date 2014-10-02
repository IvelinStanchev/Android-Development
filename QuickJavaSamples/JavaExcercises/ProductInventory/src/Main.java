import javax.lang.model.element.VariableElement;


public class Main {

	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		
		Product firstProduct = new Product(10, 5); //50
		Product secondProduct = new Product(5, 8); //40
		Product thirdProduct = new Product(2, 1); //2
		Product fourthProduct = new Product(15, 2); //30
		Product fifthProduct = new Product(10, 8); //80
		
		inventory.AddProduct(firstProduct);
		inventory.AddProduct(secondProduct);
		inventory.AddProduct(thirdProduct);
		inventory.AddProduct(fourthProduct);
		inventory.AddProduct(fifthProduct);
		
		System.out.println(inventory.CalculateInventoryValue());
		
		inventory.RemoveProduct(secondProduct);
		
		System.out.println(inventory.CalculateInventoryValue());
	}

}
