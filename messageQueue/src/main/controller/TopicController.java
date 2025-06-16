package main.controller;

import main.service.TopicService;

public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    public boolean topicExists(String topicName) {
        return topicService.topicExists(topicName);
    }

    public void createTopic(String topicName, int partitionCount) {
        topicService.createTopic(topicName, partitionCount);
    }

    public void deleteTopic(String topicName) {
        topicService.deleteTopic(topicName);
    }
}
