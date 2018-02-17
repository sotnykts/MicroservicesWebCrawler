package com.j2core.sts.webcrawler.ui.dto.node_dto;

import java.util.List;

/**
 * Object for save information about all nodes
 */
public class AllNodeStatistic {

    private List<NodeData> nodeDataList;    // list with node's data
    private long startedNode;               // amount start nodes
    private long stoppedNode;               // amount stop nodes

    public AllNodeStatistic(){

    }

    public List<NodeData> getNodeDataList() {
        return nodeDataList;
    }

    public void setNodeDataList(List<NodeData> nodeDataList) {
        this.nodeDataList = nodeDataList;
    }

    public long getStartedNode() {
        return startedNode;
    }

    public void setStartedNode(long startedNode) {
        this.startedNode = startedNode;
    }

    public long getStoppedNode() {
        return stoppedNode;
    }

    public void setStoppedNode(long stoppedNode) {
        this.stoppedNode = stoppedNode;
    }
}
