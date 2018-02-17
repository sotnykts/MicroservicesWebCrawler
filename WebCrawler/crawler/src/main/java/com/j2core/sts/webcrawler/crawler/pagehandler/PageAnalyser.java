package com.j2core.sts.webcrawler.crawler.pagehandler;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.j2core.sts.webcrawler.dao.model.dto.WordEntity;
import com.j2core.sts.webcrawler.dao.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.dao.model.informationdto.UrlData;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/*
 * Created by sts on 1/12/16.
 */

/**
 * Class for analyse page's information. This class does Receive links from page and parse page's content.
 */
public class PageAnalyser implements WebInteraction{

    private static final Logger LOGGER = Logger.getLogger(PageAnalyser.class);  // object for logging this class
    private int maxAmountTransition;                                             // max amount transition from first page's URL
    private final int valueReadPage;                                             // amount of pages read attempts

    /**
     * Constructor
     *
     * @param maxAmountTransition     max value amount transition on the original page
     * @param amountReadPage          amount endeavour read page, if its not access
     */
    public PageAnalyser(int maxAmountTransition, int amountReadPage) {
        this.maxAmountTransition = maxAmountTransition;
        this.valueReadPage = amountReadPage;
    }


    @Override
    public ResultingInformation analysePageInformation(UrlData urlData) {

        ResultingInformation pageInformation = null;

        if (urlData.getAmountTransition() < maxAmountTransition && urlData.getAmountReadPage() < valueReadPage) {

            Document htmlDocument;
            pageInformation = new ResultingInformation(urlData.getUrlId(), urlData.getUrl(),
                    urlData.getAmountTransition(), urlData.getNodeId());

            try {
                htmlDocument = Jsoup.connect(urlData.getUrl()).timeout(60000).userAgent("Chrome").get();

                if (urlData.getAmountTransition() < maxAmountTransition) {
                    pageInformation.setUrlCollectionNew(receiveLinksFromPage(htmlDocument, urlData.getUrl(),
                            urlData.getAmountTransition()));
                }
                pageInformation.setPagesText(htmlDocument.body().text());

                pageInformation.setWordsInPage(parsePageContent(htmlDocument));

            } catch (Exception exp) {

                LOGGER.error( " Analyse Page " + exp );
                return null;
            }
        }
        return pageInformation;
    }


    /**
     * This method is receiving all links from page
     *
     * @param htmlDocument      data from page
     * @param pageUrl           page's URL
     * @param amountTransition  value amount transition of original page
     * @return collection with all links from page
     */
    public List<UrlData> receiveLinksFromPage(Document htmlDocument, String pageUrl, int amountTransition){

        List<UrlData> newUrlCollection = new LinkedList<>();
        Set<UrlData> collectionNewUrl = new HashSet<>();

        String domainUrl = takePageDomain(pageUrl);

        Elements elements = htmlDocument.getElementsByTag("a");

        for(Element elem : elements){
            String newUrl = elem.toString();

            int index = newUrl.indexOf("href=\"")+6;
            try {
                newUrl = newUrl.substring(index, newUrl.indexOf('\"', index));

            }catch (StringIndexOutOfBoundsException e){

                LOGGER.error(" Receive links from page, getNewLink " + e);
            }


            if (!newUrl.contains("http")){
                newUrl = domainUrl + newUrl;
            }

            collectionNewUrl.add(new UrlData(newUrl, (amountTransition + 1)));
        }

        newUrlCollection.addAll(collectionNewUrl);

        return UrlVerification.verifyUrlCollection(newUrlCollection);
    }


    /**
     * This method is parsing page's content
     *
     * @param htmlDocument  data from page
     * @return  collection with all words from page and their amount on this page
     */
    public List<WordEntity> parsePageContent(Document htmlDocument){

        Multiset<String> wordInSite = HashMultiset.create();
        String text = htmlDocument.body().text();

        wordInSite.addAll(Splitter.onPattern("([,.:@!?;1234567890()<>/{}+-=*&#$%•]|\\[|\\]|\\s)").trimResults().omitEmptyStrings().splitToList(text));

        Set<Multiset.Entry<String>> words = wordInSite.entrySet();
        List<WordEntity> pageWords = new LinkedList<>();

        for (Multiset.Entry<String > word : words){

            pageWords.add(new WordEntity(word.getElement(), word.getCount()));
        }

        return pageWords;

    }


    /**
     * This method is taking domain from URL
     *
     * @param pagesUrl     string with page's URL
     * @return  string with page's domain
     */
    public String takePageDomain(String pagesUrl){

        if (pagesUrl.indexOf("/", 8) < 0) {
            return pagesUrl;
        }else return pagesUrl.substring(0, pagesUrl.indexOf("/", 8));
    }

    public int getMaxAmountTransition() {
        return maxAmountTransition;
    }

    public void setMaxAmountTransition(int maxAmountTransition) {
        this.maxAmountTransition = maxAmountTransition;
    }

    public int getValueReadPage() {
        return valueReadPage;
    }

}
