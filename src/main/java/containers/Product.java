package containers;

public class Product
{
	private String name;
	private int id;
	
	public Product(String name, int id)
	{
		this.name = name;
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getID()
	{
		return id;
	}
}
