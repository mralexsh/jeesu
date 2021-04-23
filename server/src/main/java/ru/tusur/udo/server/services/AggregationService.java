package ru.tusur.udo.server.services;

import org.springframework.stereotype.Service;
import ru.tusur.udo.server.dto.AggregationInfo;
import ru.tusur.udo.server.dto.NodeInfo;

import java.util.Date;

@Service
public class AggregationService {

    private AggregationInfo aggregationInfo = new AggregationInfo();

    public void aggregate(NodeInfo nodeInfo) {
        aggregationInfo.setAggregationTimestamp(new Date().getTime());
        aggregationInfo.getNodesHashMap().put(nodeInfo.getNodeName(), nodeInfo);
    }

    public AggregationInfo getAggregationInfo() {
        return aggregationInfo;
    }

}
