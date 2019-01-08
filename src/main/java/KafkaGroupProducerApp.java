import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.*;

public class KafkaGroupProducerApp {

    public static void main(String[] args){

        // Create a properties dictionary for the required/optional Producer config settings
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093, localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
       
        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(props);

        try {
        	int counter = 0;
            while (counter < 100){
            	myProducer.send(new ProducerRecord<String, String>("my-big-topic", "abcdefghijklmnopqrstuvwxyz"));
            	counter++;
            }
        } catch (Exception e) { 
        	System.out.println(e.getMessage());
        } finally {
        	myProducer.close();
        }
    }
}
