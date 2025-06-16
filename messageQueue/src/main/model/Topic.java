package main.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Topic {

    private final String name;
    private final List<Partition> partitions;

    public Topic(String name, int partitionCount) {
        this.name = name;
        this.partitions = IntStream.range(0, partitionCount)
                .mapToObj(i -> new Partition())
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public int getPartitionCount() {
        return partitions.size();
    }

    public Partition getPartition(int index) {
        return partitions.get(index);
    }

    public List<Partition> getPartitions() {
        return partitions;
    }
}
