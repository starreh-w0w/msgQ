package main.service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SubscriptionService {

    private final ConcurrentHashMap<String, Set<String>> subscriptions;

    public SubscriptionService() {
        this.subscriptions = new ConcurrentHashMap<>();
    }

    public boolean isSubscribed(String consumerId, String topicName) {
        return subscriptions.containsKey(consumerId) &&
                subscriptions.get(consumerId).contains(topicName);
    }

    public void subscribe(String consumerId, String topicName) {
        subscriptions.computeIfAbsent(consumerId, k -> ConcurrentHashMap.newKeySet())
                .add(topicName);
    }

    public void unsubscribe(String consumerId, String topicName) {
        Set<String> topics = subscriptions.get(consumerId);
        if (topics != null) topics.remove(topicName);
    }

    public Set<String> getSubscribedTopics(String consumerId) {
        return subscriptions.getOrDefault(consumerId, Set.of());
    }
}
