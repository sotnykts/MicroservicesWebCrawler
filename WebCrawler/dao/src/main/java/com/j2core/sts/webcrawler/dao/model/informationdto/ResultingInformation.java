package com.j2core.sts.webcrawler.dao.model.informationdto;



import com.j2core.sts.webcrawler.dao.model.dto.WordEntity;

import java.util.List;

/**
 * Created by sts on 1/20/16.
 */

/**
 * Object for saves information about page
 */
public class ResultingInformation{

    private String pageUrl;
    private int amountTransition;
    private List<WordEntity> wordsInPage = null;           // collection for saved all word from this page and their amount on this page
    private List<UrlData> urlCollectionNew = null;  // collection for saved all links from this page
    private String pagesText;                              // page's content
    private int urlId;                              // URL's id in DB
    private int pageId;                                  // Page's id in DB
    private int nodeId;


    public ResultingInformation(){}

    /**
     * Constructor  ResultingInformation's class
     *
     * @param id                 id URL's in DB
     * @param pageUrl            page's URL
     * @param amountTransition   amount transition from original page
     * @param nodeId             node's id
     */
    public ResultingInformation(int id, String pageUrl, int amountTransition, int nodeId) {
        this.urlId = id;
        this.pageUrl = pageUrl;
        this.amountTransition = amountTransition;
        this.nodeId = nodeId;

    }


    public List<WordEntity> getWordsInPage() {
        return wordsInPage;
    }

    public void setWordsInPage(List<WordEntity> wordsInPage) {
        this.wordsInPage = wordsInPage;
    }

    public List<UrlData> getUrlCollectionNew() {
        return urlCollectionNew;
    }

    public void setUrlCollectionNew(List<UrlData> urlCollectionNew) {
        this.urlCollectionNew = urlCollectionNew;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl){
        this.pageUrl = pageUrl;
    }

    public int getAmountTransition() {
        return amountTransition;
    }

    public void setAmountTransition(int amountTransition){

        this.amountTransition = amountTransition;
    }

    public String getPagesText(){
        return pagesText;
    }

    public void setPagesText(String text){
        pagesText = text;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId){

        this.urlId = urlId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toString() {
        return "ResultingInformation{" +
                "urlId=" + urlId +
                "pageUrl='" + pageUrl + '\'' +
                ", amountTransition=" + amountTransition +
                ", wordsInPage=" + wordsInPage +
                ", urlCollectionNew=" + urlCollectionNew +
                ", pagesText='" + pagesText + '\'' +
                '}';
    }
}
