package main.controller;

import main.service.MessageService;

public class ProducerController {

    private final MessageService messageService;

    public ProducerController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void publish (String topicName, String key, String payload) {
        messageService.publish(topicName, key, payload);
    }
}
