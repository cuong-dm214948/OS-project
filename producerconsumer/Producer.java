package producerconsumer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Producer {   
	
    public Product produceProduct(int id, String name, int quantity){
    	
    	LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = currentTime.format(formatter); 
        
        Product product = new Product();

        product.setName(name);
        product.setId(id);
        product.setQuantity(quantity);
        product.setTime(time);
       
        return product;
    }
}