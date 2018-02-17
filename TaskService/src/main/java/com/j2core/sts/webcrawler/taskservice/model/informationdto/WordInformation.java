package com.j2core.sts.webcrawler.taskservice.model.informationdto;

import javax.persistence.*;
import java.io.Serializable;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class container for keep words page's information
 */
@Entity
@Table(name = "wordInformation")
public class WordInformation implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "wordId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int wordId;

    @ManyToOne
    @JoinColumn(name = "pageId", nullable = false)
    private PageInformation pageData;

    @ManyToOne()
    private UrlData urlData;

    @Column(name = "word")
    private String word;

    @Column(name = "amountOnPage")
    private int amountWord;

    public WordInformation(){

    }

    public WordInformation(String word, int amountWord) {
        this.word = word;
        this.amountWord = amountWord;
    }

    public WordInformation(UrlData urlData, PageInformation pageData, String word, int amountWord){

        this.pageData = pageData;
        this.urlData = urlData;
        this.word = word;
        this.amountWord = amountWord;

    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public PageInformation getPageData() {
        return pageData;
    }

    public void setPageData(PageInformation pageData) {
        this.pageData = pageData;
    }

    public UrlData getUrlData() {
        return urlData;
    }

    public void setUrlData(UrlData urlData) {
        this.urlData = urlData;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getAmountWord() {
        return amountWord;
    }

    public void setAmountWord(int amountWord) {
        this.amountWord = amountWord;
    }

}
