package main.utils;

public class PartitionStrategy {
    public static int getPartition(String key, int partitionCount) {
        return Math.abs(key.hashCode()) % partitionCount;
    }
}
