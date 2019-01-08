import org.apache.kafka.clients.producer.*;

import java.util.*;

public class KafkaProducerApp {

    public static void main(String[] args){

        // Create a properties dictionary for the required/optional Producer config settings:
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093, localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(props);
       
        try {
        	for(int i=0; i<150; i++) {
        		myProducer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), "MyMessage: " + Integer.toString(i)));
        	}
               
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myProducer.close();
        }

    }
}
