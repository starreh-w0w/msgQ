package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Partition {

    private final Queue<Message> messages;

    public Partition() {
        this.messages = new ConcurrentLinkedQueue<>();
    }

    public Queue<Message> getMessages() {
        return messages;
    }

    public int size() {
        return messages.size();
    }

    public void publish(Message message) {
        messages.offer(message);
    }

    public List<Message> poll(int offset) {
        return new ArrayList<>(messages).subList(offset, messages.size());
    }


}
