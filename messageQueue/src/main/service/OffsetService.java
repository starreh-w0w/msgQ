package main.service;

import java.util.concurrent.ConcurrentHashMap;

public class OffsetService {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentHashMap<Integer, Integer>>> offsetMap;

    public OffsetService() {
        this.offsetMap = new ConcurrentHashMap<>();
    }

    public int getOffset(String consumerId, String topic, int partition) {
        return offsetMap
                .getOrDefault(consumerId, new ConcurrentHashMap<>())
                .getOrDefault(topic, new ConcurrentHashMap<>())
                .getOrDefault(partition, 0);
    }

    public void updateOffset(String consumerId, String topic, int partition, int offset) {
        offsetMap
                .computeIfAbsent(consumerId, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(topic, k -> new ConcurrentHashMap<>())
                .put(partition, offset);
    }
}
