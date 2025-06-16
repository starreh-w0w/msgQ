package main.service;

import main.db.InMemoryDatabase;
import main.model.Message;
import main.model.Partition;
import main.model.Topic;
import main.utils.PartitionStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageService {

    private final InMemoryDatabase inMemoryDatabase;
    private final OffsetService offsetService;
    private final SubscriptionService subscriptionService;

    public MessageService(InMemoryDatabase inMemoryDatabase, OffsetService offsetService, SubscriptionService subscriptionService) {
        this.inMemoryDatabase = inMemoryDatabase;
        this.offsetService = offsetService;
        this.subscriptionService = subscriptionService;
    }

    public void publish(String topicName, String key, String payload) {
        Topic topic = inMemoryDatabase.getTopic(topicName);
        if (topic == null) throw new IllegalArgumentException("TOPIC NOT FOUND");

        int partitionIndex = PartitionStrategy.getPartition(key, topic.getPartitionCount());
        topic.getPartition(partitionIndex).publish(new Message(key, payload));
    }

    public Map<Integer, List<Message>> consume(String consumerId, String topicName) {
        if (!subscriptionService.isSubscribed(consumerId, topicName)) {
            throw new IllegalArgumentException("SUBSCRIPTION UNAVAILABLE");
        }

        Topic topic = inMemoryDatabase.getTopic(topicName);
        if (topic == null) throw new IllegalArgumentException("TOPIC NOT FOUND");

        Map<Integer, List<Message>> result = new HashMap<>();
        for (int idx = 0; idx < topic.getPartitionCount(); idx++) {
            Partition partition = topic.getPartition(idx);
            int offset = offsetService.getOffset(consumerId, topicName, idx);
            List<Message> messages = partition.poll(offset);
            result.put(idx, messages);
            offsetService.updateOffset(consumerId, topicName, idx, offset + messages.size());
        }
        return result;
    }
}
