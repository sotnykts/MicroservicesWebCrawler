package com.j2core.sts.webcrawler.ui.dto.url_dto;

/**
 * Object for save information with URL"s statistics
 */
public class UrlStatistic {

    private Long amountUrl;         // amount URL in DB
    private Long processedUrl;      // amount processed URL in DB
    private Long processesUrl;      // amount processes URL in DB
    private Long notProcessedUrl;   // amount not processed URL in DB


    public UrlStatistic(){

    }

    public Long getAmountUrl() {
        return amountUrl;
    }

    public void setAmountUrl(Long amountUrl) {
        this.amountUrl = amountUrl;
    }

    public Long getProcessedUrl() {
        return processedUrl;
    }

    public void setProcessedUrl(Long processedUrl) {
        this.processedUrl = processedUrl;
    }

    public Long getProcessesUrl() {
        return processesUrl;
    }

    public void setProcessesUrl(Long processesUrl) {
        this.processesUrl = processesUrl;
    }

    public Long getNotProcessedUrl() {
        return notProcessedUrl;
    }

    public void setNotProcessedUrl(Long notProcessedUrl) {
        this.notProcessedUrl = notProcessedUrl;
    }
}
