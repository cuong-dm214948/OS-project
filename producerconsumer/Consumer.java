package producerconsumer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consumer{
	
    public String consumeItem(int id) {
    	String result = null;
    	
    	LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = currentTime.format(formatter); 
        
        result += ("consuming product: " + id + " at " + time );
        return result;
    }
	
	
}
