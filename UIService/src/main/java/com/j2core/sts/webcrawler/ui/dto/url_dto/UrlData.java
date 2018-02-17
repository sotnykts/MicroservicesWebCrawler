package com.j2core.sts.webcrawler.ui.dto.url_dto;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Object for save information about URL's data
 */
@Entity
@Table(name = "urlData")
public class UrlData implements Serializable {

    private static final long serialVersionUID = 40L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "urlId")
    private int urlId;                        // id URL

    @Column(name = "url", unique = true, length = 65535)
    private String url;                       // URL

    @Column(name = "amountTransition")
    private int amountTransition;             // amount transaction from first page

    @Column(name = "status")
    private URLStatus status;                 // URL's status

    @Column(name = "statusChangeTime")
    private long statusChangeUnixTime;        // unixTime change URL's status

    @Column(name = "nodeId")
    private Integer nodeId = null;            // node's id which process URL

    @Column(name = "amountReadPage")
    private int amountReadPage = 0;

    public UrlData(){

    }

    /**
     * Constructor
     *
     * @param url                   page's URL
     * @param amountTransition      amount transition from first page
     * @param status                URL's status(not processed, processed, or processes)
     */
    public UrlData(String url, int amountTransition, URLStatus status) {
        this.url = url;
        this.amountTransition = amountTransition;
        this.status = status;
        this.statusChangeUnixTime = System.currentTimeMillis();
    }

    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId = urlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAmountTransition() {
        return amountTransition;
    }

    public void setAmountTransition(int amountTransition) {
        this.amountTransition = amountTransition;
    }

    public URLStatus getStatus() {
        return status;
    }

    public void setStatus(URLStatus status) {
        this.status = status;
    }

    public long getStatusChangeUnixTime() {
        return statusChangeUnixTime;
    }

    public void setStatusChangeUnixTime(long statusChangeUnixTime) {
        this.statusChangeUnixTime = statusChangeUnixTime;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public int getAmountReadPage() {
        return amountReadPage;
    }

    public void setAmountReadPage(int amountReadPage) {
        this.amountReadPage = amountReadPage;
    }

    @Override
    public String toString() {
        return "UrlData{" +
                "urlId=" + urlId +
                ", url='" + url + '\'' +
                ", amountTransition=" + amountTransition +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlData urlData = (UrlData) o;

        if (urlId != urlData.urlId) return false;
        if (amountTransition != urlData.amountTransition) return false;
        if (!url.equals(urlData.url)) return false;
        return status == urlData.status;

    }

    @Override
    public int hashCode() {
        int result = urlId;
        result = 31 * result + url.hashCode();
        result = 31 * result + amountTransition;
        return result;
    }
}
