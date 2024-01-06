package producerconsumer;

public class Product {
 
    private int id;
    private String name;
    private int quantity;
    private String time;
    
    public Product() {
    	
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
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Product [id:" + id + ", name:" + name + ", quantity:" + quantity + ", at "+ time + "]";
	}

	public Product(int id, String name, int quantity, String time) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.quantity = quantity;
	} 
	
	public Product(int id, int quantity, String time) {
		super();
		this.id = id;
		this.time = time;
		this.quantity = quantity;
	} 
}