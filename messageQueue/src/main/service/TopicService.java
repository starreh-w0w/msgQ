package main.service;

import main.db.InMemoryDatabase;

public class TopicService {
    private final InMemoryDatabase inMemoryDatabase;

    public TopicService(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    public void createTopic(String topic, int partitions) {
        inMemoryDatabase.createTopic(topic, partitions);
    }

    public void deleteTopic(String topic) {
        inMemoryDatabase.deleteTopic(topic);
    }

    public boolean topicExists(String topic) {
        return inMemoryDatabase.topicExists(topic);
    }
}
