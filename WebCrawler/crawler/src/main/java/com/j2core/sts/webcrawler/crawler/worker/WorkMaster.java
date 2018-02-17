package com.j2core.sts.webcrawler.crawler.worker;

import com.j2core.sts.webcrawler.crawler.pagehandler.PageAnalyser;
import com.j2core.sts.webcrawler.dao.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.dao.model.informationdto.UrlData;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 *  Class manage worker's pool.
 */
public class WorkMaster implements Runnable{

    private static final Logger LOGGER = Logger.getLogger(Worker.class);  // Object for save log information
    private final static int amountWaitTime = 500;                        // Amount time waiting before continue work
    private volatile boolean flagStopWork = false;                        // WorkMaster's stop flag
    private static final int valueReadPage = 5;                           // Amount page reads
    private BlockingQueue<UrlData> pagesLink;
    private BlockingQueue<ResultingInformation> analysedPages;
    private BlockingQueue<UrlData> processesPages;
    private final Object sync;
    private final int amountWorkerThreads;
    private final int maxAmountTransition;

    /**
     * Constructor
     * @param amountWorkerThreads                   // Amount worker's threads
     * @param pagesLink                             // Collection with page's link for process
     * @param processesLink                         // Collection with page's link which in processes
     * @param analysedPages                         // Collection with Information after process
     * @param maxAmountTransition                   // Amount max transition
     * @param sync                                  // Object for synchronized
     */
    public WorkMaster(int amountWorkerThreads, BlockingQueue<UrlData> pagesLink, BlockingQueue<UrlData> processesLink,
                      BlockingQueue<ResultingInformation> analysedPages, int maxAmountTransition, Object sync) {
        this.amountWorkerThreads = amountWorkerThreads;
        this.pagesLink = pagesLink;
        this.processesPages = processesLink;
        this.analysedPages = analysedPages;
        this.maxAmountTransition = maxAmountTransition;
        this.sync = sync;

    }


    @Override
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "UW_UNCOND_WAIT", justification = "Exception detail hide")
    public void run() {

        LOGGER.info("Start workMaster");
        PageAnalyser analyser = new PageAnalyser(maxAmountTransition, valueReadPage);
        ExecutorService poolThread = Executors.newFixedThreadPool(amountWorkerThreads);

        while (!flagStopWork){

            if (!pagesLink.isEmpty()) {
                Runnable worker = new Worker(pagesLink, processesPages, analysedPages, analyser, sync);
                poolThread.execute(worker);
            }else {
                synchronized (sync){
                    sync.notifyAll();
                }
                synchronized (sync){
                    try {
                        sync.wait(5000);
                    } catch (InterruptedException e) {
                        LOGGER.error(e);
                    }
                }
            }
        }

        poolThread.shutdownNow();
        LOGGER.info("WorkMaster poolThread.shutdown");

        try {
            boolean closeThreads = false;
            while (!closeThreads) {
                closeThreads = poolThread.awaitTermination(amountWaitTime, TimeUnit.MILLISECONDS);
                synchronized (sync){
                    sync.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("Stopped workMaster");

    }


    /**
     * The method set stop work flag
     *
     * @param flag stop work flag value
     */
    public void setFlagStopWork(boolean flag){

        this.flagStopWork = flag;
    }


    /**
     * The method get stop work flag
     *
     * @return value stop work flag
     */
    public boolean isFlagStopWork() {
        return flagStopWork;
    }
}
