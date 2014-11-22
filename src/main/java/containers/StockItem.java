package containers;

public class StockItem {
	private int product_id;
	private String displayed_name;
	private String description;
	private float price;
	private int available_number;
	private int number_sold;
	private int discount;
	private int image_id;
    
public StockItem(int product_id, String displayed_name, String description, float price, int available_number, int number_sold, int discount, int image_id){
	this.product_id = product_id;
	this.displayed_name = displayed_name;
	this.description = description;
	this.price = price;
	this.available_number = available_number;
	this.number_sold = number_sold;
	this.discount = discount;
	this.image_id = image_id;
}

public int getProduct_id() {
	return product_id;
}

public String getDisplayed_name() {
	return displayed_name;
}

public String getDescription() {
	return description;
}

public float getPrice() {
	return price;
}

public int getAvailable_number() {
	return available_number;
}

public int getNumber_sold() {
	return number_sold;
}

public int getDiscount() {
	return discount;
}

public int getImage_id() {
	return image_id;
}
    
   
}