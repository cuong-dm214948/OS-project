package producerconsumer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ProducerConsumerMonitor {
    private static final String FILE_NAME = "Product.json";
    private List<Product> productList;
    private int counter;
    int n;
    
    public ProducerConsumerMonitor(int n) {
    	this.n = n;  
    	this.productList = new ArrayList<>();
    	this.counter = readCounterFromFile();
    }
    private File file = new File(FILE_NAME);
    
    public synchronized void put(Product product) {
        try {
            while (counter + product.getQuantity() > n) {
                wait();  // If buffer is full, block
            }

            storeDataToFile(product);

            counter += product.getQuantity();

            // Notify all waiting threads (consumers and potentially other producers)
            notifyAll();
            if (counter == 1) notify();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void remove(int id, int quantity) {
        try {
            Product existingProduct = getProductById(id);
            while (existingProduct == null || existingProduct.getQuantity() < quantity) {
                wait();  // If the product is not found or quantity is insufficient, block
            }
            // Find and update quantity in the buffer
            int newQuantity = existingProduct.getQuantity() - quantity;
            existingProduct.setQuantity(newQuantity);

            counter -= quantity;
            notifyAll();
            // Notify all waiting threads (producers and potentially other consumers)
            if (counter == n - 1) notify();
            

        } catch (InterruptedException e) {
    	    e.printStackTrace();        	
        }
    }

    private Product getProductById(int productId) {
        // Implement the logic to get a product by its id
        // You may want to iterate through the productList and find the matching id
        return productList.stream().filter(p -> p.getId() == productId).findFirst().orElse(null);
    }
    
    private void storeDataToFile(Product newProduct) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            // Read existing products from the file
            if (file.exists()) {
                productList = objectMapper.readValue(file, new TypeReference<List<Product>>() {});
            }

            // Check if a product with the same ID already exists
            boolean productExists = false;
            for (Product existingProduct : productList) {
                if (existingProduct.getId() == newProduct.getId()) {
                    // Update quantity if the product already exists
                    existingProduct.setQuantity(existingProduct.getQuantity() + newProduct.getQuantity());
                    productExists = true;
                    break;
                }
            }

            // If the product doesn't exist, add it to the list
            if (!productExists) {
                productList.add(newProduct);
            }

            // Write the updated product list to the file
            objectMapper.writeValue(file, productList);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    public String readAccountsFromFile() {
        StringBuilder result = new StringBuilder();
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            if (file.exists()) {
                // Read the existing product list from the file
                productList = objectMapper.readValue(file, new TypeReference<List<Product>>() {});

                if (!productList.isEmpty()) {
                    for (Product product : productList) {
                        result.append(product.toString()).append("\n");
                    }
                }
            }

        } catch (IOException e) {
            result.append("Error reading: ").append(e.getMessage());
        }
        return result.toString();
    }
    
    private int readCounterFromFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            if (file.exists()) {
                // Read the existing product list from the file
                List<Product> existingProducts = objectMapper.readValue(file, new TypeReference<List<Product>>() {});

                // Calculate the total quantity from the existing products
                int totalQuantity = existingProducts.stream().mapToInt(Product::getQuantity).sum();

                return totalQuantity;
            }

        } catch (IOException e) {
            System.out.println("Error reading counter from file: " + e.getMessage());
        }

        // Return 0 if there is an error or the file doesn't exist
        return 0;
    }
}