 package application;
 
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import producerconsumer.Product;
import producerconsumer.Consumer;
import producerconsumer.Producer;
import producerconsumer.ProducerConsumerMonitor;

public class Main extends Application{
	
    private TextField textField1;
    private TextField textField2;
    private TextField textField3;
    private TextField textField6;
    private TextField textField5;  
    private TextField textField9;
    private TextArea textField7;
    
	static final int N = 50;//size of buffer
    static final int LIMIT = 500;//limit of number of items produced
    //static Thread producer = new Producer(N);
    //static Thread consumer = new Consumer(N);   
    
    public void start(Stage stage) {
        // Creating label and Text Field
    	Text text0  = new Text("Producer");
    	
        Text text1 = new Text("ID:");
        textField1 = new TextField();

        Text text2 = new Text("Name:");
        textField2 = new TextField();

        Text text3 = new Text("Quantity:");
        textField3 = new TextField();

    	Text text4  = new Text("Consumer");
    	
        Text text5 = new Text("ID:");
        textField5 = new TextField();

        Text text6 = new Text("Quantity:");
        textField6 = new TextField();
        
		Text text7 = new Text("Product:");
		textField9 = new TextField();
		textField9.setPrefWidth(40);		
		textField7 = new TextArea();
		textField7.setPrefWidth(370);
		textField7.setPrefHeight(260);
		
        Text text8 = new Text("Message:");
        TextField textField8 = new TextField();
        textField8.setPrefWidth(500);
        textField8.setPrefHeight(20);

        Button button1 = new Button("    produce   ");       
        Button button2 = new Button("      take      ");
        
        // Creating a Grid Pane
        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(5, 5, 5, 5));
        gridPane1.setVgap(5);
        gridPane1.setHgap(5);
        gridPane1.setAlignment(Pos.CENTER);
        gridPane1.add(text0, 0, 0);
        gridPane1.add(text1, 0, 1);
        gridPane1.add(textField1, 1, 1);
        gridPane1.add(text2, 0, 2);
        gridPane1.add(textField2, 1, 2);
        gridPane1.add(text3, 0, 3);
        gridPane1.add(textField3, 1, 3);
        gridPane1.add(button1, 1, 4);
        
        GridPane gridPane2 = new GridPane();
        gridPane2.setPadding(new Insets(5, 5, 5, 5));
        gridPane2.setVgap(5);
        gridPane2.setHgap(5);
        gridPane2.setAlignment(Pos.CENTER);
        gridPane2.add(text4, 0, 0);
        gridPane2.add(text5, 0, 1);
        gridPane2.add(textField5, 1, 1);
        gridPane2.add(text6, 0, 2);
        gridPane2.add(textField6, 1, 2);
        gridPane2.add(button2, 1, 3);       

        // Styling nodes
        text0.setStyle("-fx-font: normal bold 15px 'serif' ");
        text4.setStyle("-fx-font: normal bold 15px 'serif' ");
        text7.setStyle("-fx-font: normal bold 15px 'serif' ");
        text8.setStyle("-fx-font: normal bold 10px 'serif' ");

        GridPane gridPane10 = new GridPane();
        gridPane10.setPadding(new Insets(5, 5, 5, 5));
        gridPane10.setVgap(5);
        gridPane10.setHgap(5);
        gridPane10.add(text7, 0, 0);
        gridPane10.add(textField9, 1, 0); 
        
        GridPane gridPane3 = new GridPane();
        gridPane3.setPadding(new Insets(5, 5, 5, 5));
        gridPane3.setVgap(5);
        gridPane3.setHgap(5);
        gridPane3.add(gridPane10, 0, 0);    
        gridPane3.add(textField7, 0, 1);      
        
        GridPane gridPane4 = new GridPane();
        gridPane4.setPadding(new Insets(5, 5, 5, 5));
        gridPane4.setVgap(5);
        gridPane4.setHgap(5);
        gridPane4.add(text8, 0, 0);
        gridPane4.add(textField8, 0, 1);
        
        GridPane gridPane5 = new GridPane();
        gridPane5.add(gridPane1, 0, 0);
        gridPane5.add(gridPane2, 0, 1);           
        
        GridPane gridPane6 = new GridPane();
        gridPane6.add(gridPane3, 1, 0);
        gridPane6.add(gridPane5, 0, 0);
        
        GridPane gridPane7 = new GridPane();
        gridPane7.setVgap(30);
        gridPane7.add(gridPane6, 0, 0);
        gridPane7.add(gridPane4, 0, 1);
        
        BorderPane root = new BorderPane();
        root.setTop(gridPane7);

 

        root.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
            	
            	Producer producer = new Producer();
            	Consumer consumer =  new Consumer();
            	
            	try {
            		
            	String num = (textField9.getText());
            	if (!num.isEmpty()) {
		            	int n = Integer.parseInt(num);
		            	ProducerConsumerMonitor monitor = new ProducerConsumerMonitor(n);
		                button1.setOnAction(event -> {
		                    try{
		                	
		                    String id1 = textField1.getText();
		                    String name = textField2.getText();
		                    String quantity1 = (textField3.getText());
		                    if (!name.isEmpty() && !id1.isEmpty() && !quantity1.isEmpty() ) {
		                    	
		                    int id = Integer.parseInt(id1);
		                    int quantity = Integer.parseInt(quantity1);
		                    
		                    Product product = producer.produceProduct(id, name, quantity);
		                    
		                    monitor.put(product);
		                    
		                    //list of created product
		                    String result = monitor.readAccountsFromFile();
		                    textField7.setText(result);
		                    textField8.setText("produced product");
		                    }
		                    else {textField8.setText("not enough input");}
		                   
		                    }
		                    catch (NumberFormatException e) {
		                        // Handle the case where parsing the input as integers fails
		                        textField8.setText("Invalid input");
		                    }
		                });
		                
		                button2.setOnAction(event -> {
		                  try {  // Get text from textField5 and textField6
		                    String id2 = textField5.getText();
		                    String quantity2 = textField6.getText();
		                 
		                    // Check if both id and quantity are not empty
		                    if (!id2.isEmpty() && !quantity2.isEmpty()) {
		                        // Parse id and quantity to integers
		                        int id3 = Integer.parseInt(id2);
		                        int quantity3 = Integer.parseInt(quantity2);
		                        
		                        // Remove the product from the monitor
		                        monitor.remove(id3, quantity3);
		
		                        // Consume the item and set the result in textField8
		                        String result = consumer.consumeItem(id3);
		                        textField8.setText(result);
		
		                        // Read accounts from file and set the result in textField7
		                        String result2 = monitor.readAccountsFromFile();
		                        textField7.setText(result2);
		                        
		                    }
		                     else {
		                        	textField8.setText("not enough input");
		                        }
		                    }catch (NumberFormatException e) {
		                        // Handle the case where parsing the input as integers fails
		                        textField8.setText("Invalid input");
		                    }
		
		               
		                });                
            	}
            	else {
            		textField8.setText("not enough input!");
            	}
            	
            	}
            	catch (NumberFormatException e) {
            		textField8.setText("error input: interger!");            		
            	} 
            }             
        });        
    	
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Producer-Consumer using monitor");
        stage.setScene(scene);
        stage.show();
    }
    

    	
	public static void main(String args[]) {
		
	    launch(args);
	}
}
