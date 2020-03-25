package backend.Service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface OrderKafkaService {
    void dealOrder(ConsumerRecord record);
}
