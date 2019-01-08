import org.apache.kafka.clients.consumer.*;

import java.util.*;

public class KafkaConsumerGroupApp04 {

    public static void main(String[] args){

        // Create a properties dictionary for the required/optional Producer config settings
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093, localhost:9094");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "test-group"); // Required when subscribing to topics. Rename as desired.
       
        // Create a KafkaConsumer instance and configure it with properties.
        KafkaConsumer<String, String> myConsumer = new KafkaConsumer<String, String>(props);

        // Create a topic subscription list:
        ArrayList<String> topics = new ArrayList<String>();
        topics.add("my-big-topic");
        // topics.add("myNewTopic");
        myConsumer.subscribe(topics);

        // Start polling for messages:
        try {
            while (true){
                ConsumerRecords<String, String> records = myConsumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s", record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                }
            }
        } catch (Exception e) { 
        	System.out.println(e.getMessage());
        } finally {
        	myConsumer.close();
        }
    }
}
