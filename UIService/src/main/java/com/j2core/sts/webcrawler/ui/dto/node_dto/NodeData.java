package com.j2core.sts.webcrawler.ui.dto.node_dto;

import javax.persistence.*;

/**
 * Object for save node's information
 */
@Entity
@Table(name = "nodeData")
public class NodeData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "nodeId")
    private int nodeId;                            // node Id

    @Column(name = "nodeName", length = 65535)
    private String nodeName;                       // node's name

    @Column(name = "statusWork")
    private boolean statusWork = true;             // node's status work

    @Column(name = "startUnixTime", length = 65535)
    private long startUnixTime;                    // node's start unixTime

    @Column(name = "stopUnixTime", length = 65535)
    private long stopUnixTime;                     // node's stop unixTime

    @Column(name = "stoppedFlag")
    private boolean stoppedFlag = false;           // stop work's flag

    public NodeData(){

    }

    /**
     * Constructor
     *
     * @param name   node's name
     */
    public NodeData (String name){
        this.nodeName = name;
        this.statusWork = true;
        this.startUnixTime = System.currentTimeMillis();
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public boolean isStatusWork() {
        return statusWork;
    }

    public void setStatusWork(boolean statusWork) {
        this.statusWork = statusWork;
    }

    public long getStartUnixTime() {
        return startUnixTime;
    }

    public void setStartUnixTime(long startUnixTime) {
        this.startUnixTime = startUnixTime;
    }

    public long getStopUnixTime() {
        return stopUnixTime;
    }

    public void setStopUnixTime(long stopUnixTime) {
        this.stopUnixTime = stopUnixTime;
    }

    public boolean isStoppedFlag() {
        return stoppedFlag;
    }

    public void setStoppedFlag(boolean stoppedFlag) {
        this.stoppedFlag = stoppedFlag;
    }
}
