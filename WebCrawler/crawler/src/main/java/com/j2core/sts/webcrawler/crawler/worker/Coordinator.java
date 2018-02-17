package com.j2core.sts.webcrawler.crawler.worker;

import com.j2core.sts.webcrawler.dao.model.dto.FinalInformationDto;
import com.j2core.sts.webcrawler.dao.model.dto.TaskResultDto;
import com.j2core.sts.webcrawler.dao.model.informationdto.NodeData;
import com.j2core.sts.webcrawler.dao.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.dao.model.informationdto.URLStatus;
import com.j2core.sts.webcrawler.dao.model.informationdto.UrlData;
import com.j2core.sts.webcrawler.dao.model.userdto.SecurityToken;
import com.j2core.sts.webcrawler.dao.rest.RestClient;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/*
 * Created by Sotnyk Tetiana.
 */
/**
 * Class create Workers threads, take URL information from DB, and saved analysed page's information in DB
 */
public class Coordinator implements Runnable{

    private static final Logger LOGGER = Logger.getLogger(Coordinator.class);                  // Object for save log information
    private BlockingQueue<UrlData> pagesLink = new LinkedBlockingQueue<>();                    // Collection for save page's link for process
    private BlockingQueue<ResultingInformation> analysedPages = new LinkedBlockingQueue<>();   // Collection for save result information from page's link
    private BlockingQueue<UrlData> processesLink = new LinkedBlockingQueue<>();                // Collection for save link which in process
    private RestClient restClient;                                                             // Object for work with Task Service
    private boolean flagStopCoordinator = false;                                               // Coordinator's stopped work flag
    private static int coefficientAmountUrl = 5;                                               // Coefficient amount URL
    private final static int amountWaitTime = 3000;                                             // Amount time waiting before continue work
    private final Object sync = new Object();                                                  // Object for synchronized all threads
    private int amountWorkerThreads;
    private int maxAmountTransition;
    private final String nameCoordinator;
    private Thread workMaster;
    private int nodeId;
    private SecurityToken securityToken;


    /**
     * Constructor Coordinator's class
     *
     * @param amountWorkerThreads               // amount Worker's threads
     * @param maxAmountTransition               // max amount transition from first page's URL
     * @param nodeName                          // node's name
     */
    public Coordinator(SecurityToken token, int amountWorkerThreads, int maxAmountTransition, String nodeName, RestClient restClient) {

        this.securityToken = token;
        this.amountWorkerThreads = amountWorkerThreads;
        this.maxAmountTransition = maxAmountTransition;
        this.nameCoordinator = nodeName;
        this.restClient = restClient;
    }


    @Override
    public void run() {

        LOGGER.info(" Start coordinator " + nameCoordinator);
        nodeId = restClient.createNode(nameCoordinator, securityToken);

        if (nodeId != -1) {

            WorkMaster master = new WorkMaster(amountWorkerThreads, pagesLink, processesLink, analysedPages, maxAmountTransition, sync);
            workMaster = new Thread(master);
            workMaster.start();

            try {
                // get first link for process
                if (pagesLink.isEmpty()) {
                    synchronized (sync) {
                        if (pagesLink.isEmpty()) {

                            List<UrlData> newTask = restClient.getTask(nodeId, amountWorkerThreads, securityToken);
                            pagesLink.addAll(newTask);
                        }
                    }

                    waitTime(amountWaitTime);
                }

                while (!flagStopCoordinator) {

                    action();

                    NodeData nodeData = null;

                    while (nodeData == null) {

                        nodeData = restClient.getNodeData(nodeId, securityToken);
                    }

                    flagStopCoordinator = nodeData.isStopFlag();

                }

                restClient.changeWorkFlag(nodeId, 0, securityToken);

                master.setFlagStopWork(true);

            }catch (Exception ex){

                LOGGER.error( "exception " + ex);
            }
            finally {

                if (workMaster != null && workMaster.isAlive()) {
                    try {
                        synchronized (sync){
                            sync.notifyAll();
                        }
                        workMaster.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // save last page's information and not processed link in to data base
                FinalInformationDto finalInformationDto = new FinalInformationDto();
                finalInformationDto.setToken(securityToken);
                finalInformationDto.setProcessedTask(analysedPages);
                finalInformationDto.setProcessesTask(processesLink);
                finalInformationDto.setNotProcessesTask(pagesLink);

                boolean finalSave = false;

                try {

                    finalSave = restClient.finalSaveInformation(finalInformationDto, nodeId);

                } catch (Exception ex) {

                    LOGGER.error(ex);
                }

                LOGGER.info("Coordinator finally " + finalSave);
            }
        } else {

            LOGGER.info("Coordinator can not create new node");
        }
    }


    /**
     * The method do coordinator's work with save page's information in DB and get link for process its information from DB
     */
    private void action(){

        boolean successAddInformation = false;

        // save page's information from link in to data base
        int amountInformation = analysedPages.size();
        if (amountInformation > 0) {

            for (int i = 0; i < amountInformation; i++) {
                try {
                    ResultingInformation resultingInformation = analysedPages.take();
                    try {

                        successAddInformation = restClient.saveTaskResult(new TaskResultDto(securityToken, resultingInformation));

                    }catch (Exception ex){

                        LOGGER.error(" action1 " + ex);
                        successAddInformation = false;
                    }
                    if (!successAddInformation){

                        UrlData url = new UrlData(resultingInformation.getPageUrl(), resultingInformation.getAmountTransition(),
                                URLStatus.PROCESSES);
                        url.setUrlId(resultingInformation.getUrlId());
                        processesLink.add(url);

                    }
                } catch (InterruptedException ex) {
                    LOGGER.error(" action2 "  + ex);
                }
            }
        }
        if (pagesLink.size() > amountWorkerThreads) {

            waitTime(100);

        } else {

            // get new link for process
            try {
                List<UrlData> newTask = restClient.getTask(nodeId, amountWorkerThreads * coefficientAmountUrl, securityToken);
                if (newTask != null){

                    pagesLink.addAll(newTask);

                }
                synchronized (sync){
                    sync.notifyAll();
                }
            }catch (Exception e){

                LOGGER.error("action3" + e);
            }
        }
    }


    /**
     *  The thread wait work
     */
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {"UW_UNCOND_WAIT", "WA_NOT_IN_LOOP"}, justification = "Exception detail hide")
    private void waitTime(int time){

        synchronized (sync){
            try {
                sync.wait(time);
            } catch (InterruptedException e) {
                LOGGER.error(" woke up exception " + e);
            }
        }
    }


    public void setFlagStopCoordinator(){

        this.flagStopCoordinator = true;

    }


    public boolean isFlagStopCoordinator() {
        return flagStopCoordinator;
    }

}
