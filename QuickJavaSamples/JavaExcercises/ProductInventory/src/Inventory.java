import java.util.ArrayList;
import java.util.List;


public class Inventory {
	private List<Product> products;
	
	public Inventory(){
		products =  new ArrayList<Product>();
	}
	
	public void AddProduct(Product product){
		this.products.add(product);
	}
	
	public void RemoveProduct(Product product){
		int indexOfProduct = this.products.indexOf(product);
		if (indexOfProduct != -1){
			this.products.remove(indexOfProduct);
		}
	}
	
	public long CalculateInventoryValue(){
		long sum = 0;
		
		for (Product product : products) {
			sum += product.getPrice() * product.getQuantity();
		}
		
		return sum;
	}
}
