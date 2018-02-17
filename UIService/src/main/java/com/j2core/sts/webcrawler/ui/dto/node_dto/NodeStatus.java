package com.j2core.sts.webcrawler.ui.dto.node_dto;

/**
 * Object for save node's information and node's status (work, stopped, suspended or was not correctly stopped)
 */
public class NodeStatus {

    private NodeData nodeData;     // node's data
    private int nodeStatus;        // node's status

    /**
     * Constructor
     *
     * @param nodeData  node's data
     */
    public NodeStatus(NodeData nodeData) {
        this.nodeData = nodeData;
    }

    public NodeData getNodeData() {
        return nodeData;
    }

    public void setNodeData(NodeData nodeData) {
        this.nodeData = nodeData;
    }

    public int getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(int nodeStatus) {
        this.nodeStatus = nodeStatus;
    }
}
