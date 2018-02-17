package com.j2core.sts.webcrawler.ui.dto.node_dto;

/**
 * Object for save statistics information about node
 */
public class NodeStatistic {

    private long startTime;              // node's start unixTime
    private long stopTime;               // node's stop unixTime
    private long amountProcessesUrl;     // amount processes URL in node
    private long amountProcessedUrl;     // amount processed URL node

    public NodeStatistic (){

    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    public long getAmountProcessesUrl() {
        return amountProcessesUrl;
    }

    public void setAmountProcessesUrl(long amountProcessesUrl) {
        this.amountProcessesUrl = amountProcessesUrl;
    }

    public long getAmountProcessedUrl() {
        return amountProcessedUrl;
    }

    public void setAmountProcessedUrl(long amountProcessedUrl) {
        this.amountProcessedUrl = amountProcessedUrl;
    }
}
