package ru.tusur.udo.server.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AggregationInfo {

    private long aggregationTimestamp;
    private final Map<String, NodeInfo> nodes = new HashMap<String, NodeInfo>();

    public long getAggregationTimestamp() {
        return aggregationTimestamp;
    }

    public void setAggregationTimestamp(long aggregationTimestamp) {
        this.aggregationTimestamp = aggregationTimestamp;
    }

    public Map<String, NodeInfo> getNodesHashMap() {
        return nodes;
    }

    public List<NodeInfo> getNodes() {
        return nodes.values().stream().collect(Collectors.toList());
    }
}
