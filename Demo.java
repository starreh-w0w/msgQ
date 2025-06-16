import main.controller.ConsumerController;
import main.controller.ProducerController;
import main.controller.TopicController;
import main.db.InMemoryDatabase;
import main.model.Message;
import main.service.MessageService;
import main.service.OffsetService;
import main.service.SubscriptionService;
import main.service.TopicService;

import java.util.List;
import java.util.Map;

public class Demo {

    private static InMemoryDatabase inMemoryDatabase;
    private static TopicService topicService;
    private static OffsetService offsetService;
    private static SubscriptionService subscriptionService;
    private static MessageService messageService;

    private static void setup() {
        inMemoryDatabase = new InMemoryDatabase();
        topicService = new TopicService(inMemoryDatabase);
        offsetService = new OffsetService();
        subscriptionService = new SubscriptionService();
        messageService = new MessageService(inMemoryDatabase, offsetService, subscriptionService);
    }

    private static void display(String consumerId, String topicName, Map<Integer, List<Message>> consumed) {

        System.out.println(consumerId + " @ " + topicName);
        System.out.println("---------------------------------------------------------");
        consumed.forEach((partition, messages) -> {
            System.out.println("Partition " + partition);
            for (Message msg : messages) {
                System.out.println("  " + msg);
            }
        });
        System.out.println("---------------------------------------------------------");
    }

    public static void main(String[] args) {

        // Setting Up Demo
        Demo.setup();

        // Controllers
        TopicController topicController = new TopicController(topicService);
        ProducerController producerController = new ProducerController(messageService);
        ConsumerController consumerController = new ConsumerController(messageService, subscriptionService);

        // Create Topics With Partitions
        topicController.createTopic("DSA", 3);
        topicController.createTopic("OOP", 3);
        topicController.createTopic("DBMS", 3);

        // Subscribe To Topics
        consumerController.subscribe("Consumer1", "DSA");
        consumerController.subscribe("Consumer2", "OOP");
        consumerController.subscribe("Consumer3", "DBMS");

        // Publish To Topics
        producerController.publish("DSA", "Array", "1D Array");
        producerController.publish("DSA", "Stack", "Monotonic Stack");
        producerController.publish("DSA", "Queue", "DEQueue");
        producerController.publish("OOP", "Abstraction", "Abstract Class");
        producerController.publish("OOP", "Polymorphism", "Overloading");
        producerController.publish("OOP", "Inheritance", "Overriding");
        producerController.publish("DBMS", "ACID", "Transaction");
        producerController.publish("DBMS", "Normalization", "1NF");
        producerController.publish("DBMS", "SQL", "Select * From");

        // Consume From Topics
        Map<Integer, List<Message>> consumed1 = consumerController.consume("Consumer1", "DSA");
        Demo.display("Consumer1", "DSA", consumed1);

        Map<Integer, List<Message>> consumed2 = consumerController.consume("Consumer2", "OOP");
        Demo.display("Consumer2", "OOP", consumed2);

        Map<Integer, List<Message>> consumed3 = consumerController.consume("Consumer3", "DBMS");
        Demo.display("Consumer3", "DBMS", consumed3);
    }
}
