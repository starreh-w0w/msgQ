package main.controller;

import main.model.Message;
import main.service.MessageService;
import main.service.SubscriptionService;

import java.util.List;
import java.util.Map;

public class ConsumerController {

    private final MessageService messageService;
    private final SubscriptionService subscriptionService;

    public ConsumerController(MessageService messageService, SubscriptionService subscriptionService) {
        this.messageService = messageService;
        this.subscriptionService = subscriptionService;
    }

    public void subscribe(String consumerId, String topicName) {
        subscriptionService.subscribe(consumerId, topicName);
    }

    public void unsubscribe(String consumerId, String topicName) {
        subscriptionService.unsubscribe(consumerId, topicName);
    }

    public Map<Integer, List<Message>> consume (String consumerId, String topicName) {
        return messageService.consume(consumerId, topicName);
    }
}
