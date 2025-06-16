package main.db;

import main.model.Topic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDatabase {

    private final Map<String, Topic> topics;

    public InMemoryDatabase() {
        this.topics = new ConcurrentHashMap<>();
    }

    public boolean topicExists(String name) {
        return topics.containsKey(name);
    }

    public Topic getTopic(String name) {
        return topics.get(name);
    }

    public Map<String, Topic> getTopics() {
        return topics;
    }

    public void createTopic(String name, int partitions) {
        topics.putIfAbsent(name, new Topic(name, partitions));
    }

    public void deleteTopic(String name) {
        topics.remove(name);
    }
}
