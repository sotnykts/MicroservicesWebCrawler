package com.j2core.sts.webcrawler.taskservice.model.informationdto;

import javax.persistence.*;
import java.io.Serializable;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class container for keep URL's information
 */
@Entity
@Table(name = "urlData")
public class UrlData implements Serializable{

    private static final long serialVersionUID = 40L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "urlId")
    private int urlId;

    @Column(name = "url", unique = true, length = 65535)
    private String url;

    @Column(name = "amountTransition")
    private int amountTransition;

    @Column(name = "status")
    private URLStatus status;

    @Column(name = "statusChangeTime")
    private long statusChangeUnixTime;

    @Column(name = "nodeId")
    private Integer nodeId = null;

    @Column(name = "amountReadPage")
    private int amountReadPage = 0;

    public UrlData(){

    }

    public UrlData(String url, int amountTransition) {
        this.url = url;
        this.amountTransition = amountTransition;
        this.status = URLStatus.NOT_PROCESSED;
        this.statusChangeUnixTime = System.currentTimeMillis();
    }

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

    public long getStatusChangeTime() {
        return statusChangeUnixTime;
    }

    public void setStatusChangeTime(long statusChangeUnixTime) {
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
