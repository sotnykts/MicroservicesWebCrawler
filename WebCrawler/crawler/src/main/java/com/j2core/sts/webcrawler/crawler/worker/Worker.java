package com.j2core.sts.webcrawler.crawler.worker;

import com.j2core.sts.webcrawler.crawler.pagehandler.PageAnalyser;
import com.j2core.sts.webcrawler.dao.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.dao.model.informationdto.UrlData;
import org.apache.log4j.Logger;
import java.util.concurrent.BlockingQueue;

/*
 * Created by sts on 8/2/16.
 */

/**
 * Class analysed page's information, content, new link, and index all words from page
 */
public class Worker implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Worker.class);  // object for save log information
    private BlockingQueue<UrlData> pagesLink;
    private BlockingQueue<ResultingInformation> analysedPages;
    private BlockingQueue<UrlData> processesPages;
    private PageAnalyser pagesAnalyser;
    private final Object sync;

    /**
     * Constructor Worker's class
     * @param pagesLink           collection with page's links (URLs)
     * @param processesLink       collection with URL's which in processes
     * @param analysedPages       collection for saved analysed page's information
     * @param pagesAnalyser       object for analysed page
     * @param sync                object for synchronized
     */
    public Worker(BlockingQueue<UrlData> pagesLink, BlockingQueue<UrlData> processesLink,
                  BlockingQueue<ResultingInformation> analysedPages, PageAnalyser pagesAnalyser, Object sync) {
        this.pagesLink = pagesLink;
        this.processesPages = processesLink;
        this.analysedPages = analysedPages;
        this.pagesAnalyser = pagesAnalyser;
        this.sync = sync;
    }

    @Override
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {"WA_NOT_IN_LOOP", "UW_UNCOND_WAIT"}, justification = "Exception detail hide")
    public void run() {

        ResultingInformation newInformation;
        UrlData urlData;

        if (pagesLink.isEmpty()) {

            synchronized (sync){
                try {
                    sync.wait(5000);
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                }
            }
        } else {
            try {
                urlData = pagesLink.take();
                processesPages.add(urlData);

                if (urlData.getAmountTransition() < pagesAnalyser.getMaxAmountTransition()) {
                    newInformation = pagesAnalyser.analysePageInformation(urlData);

                    if (newInformation != null) {

                        boolean added = analysedPages.add(newInformation);
                        while (!added) {
                            added = analysedPages.add(newInformation);
                        }
                        processesPages.remove(urlData);
                    }
                }
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }
}
