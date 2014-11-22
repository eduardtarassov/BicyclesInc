package containers;


public class Material {
	private int material_id;
	private String material;
	private String description;
	private float unit_price;
	private String units;
	private int amount;
	
    
public Material(int material_id, String material, String description, float unit_price, String units, int amount){
	this.material_id = material_id;
	this.material = material;
	this.description = description;
	this.unit_price = unit_price;
	this.units = units;
	this.amount = amount;
}

	public int getMaterial_id() {
		return material_id;
	}

	public String getMaterial() {
		return material;
	}

	public String getDescription() {
		return description;
	}

	public float getUnit_price() {
		return unit_price;
	}

	public String getUnits() {
		return units;
	}

	public int getAmount() {
		return amount;
	}



   
}