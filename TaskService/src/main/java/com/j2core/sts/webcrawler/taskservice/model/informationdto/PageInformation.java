package com.j2core.sts.webcrawler.taskservice.model.informationdto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class container for keep page's information
 */

@Entity
@Table(name = "pageInformation")
public class PageInformation implements Serializable{

    private static final long serialVersionUID = 41L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "pageId", nullable = false)
    private Integer pageId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "urlId", nullable = false)
    private UrlData urlData;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateInformation")
    private Date dataDate;

    @Column(name = "pageText", length = 65535)
    private String pageText;

    public PageInformation(){

    }

    public PageInformation(String pageText, Date dataDate, UrlData urlData) {
        this.pageText = pageText;
        this.dataDate = dataDate;
        this.urlData = urlData;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String getPageText() {
        return pageText;
    }

    public void setPageText(String pageText) {
        this.pageText = pageText;
    }

    public UrlData getUrlData() {
        return urlData;
    }

    public void setUrlData(UrlData urlData) {
        this.urlData = urlData;
    }

    @Override
    public String toString() {
        return "PageInformation{" +
                "pageId=" + pageId +
                ", urlData=" + urlData +
                ", dataDate=" + dataDate +
                ", pageText='" + pageText + '\'' +
                '}';
    }
}
