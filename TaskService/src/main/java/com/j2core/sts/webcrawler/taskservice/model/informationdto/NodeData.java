package com.j2core.sts.webcrawler.taskservice.model.informationdto;

import javax.persistence.*;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class container for keep node's information.
 */
@Entity
@Table(name = "nodeData")
public class NodeData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "nodeId")
    private int nodeId;

    @Column(name = "nodeName", length = 65535)
    private String nodeName;

    @Column(name = "statusWork")
    private boolean statusWork = true;

    @Column(name = "startUnixTime", length = 65535)
    private long startUnixTime;

    @Column(name = "stopUnixTime", length = 65535)
    private long stopUnixTime;

    @Column(name = "stopFlag")
    private boolean stopFlag = false;

    public NodeData(){

    }

    public NodeData(String name){
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

    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }
}
