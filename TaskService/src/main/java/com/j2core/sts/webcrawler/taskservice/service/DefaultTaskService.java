package com.j2core.sts.webcrawler.taskservice.service;

import com.j2core.sts.webcrawler.taskservice.dao.PageInformationDao;
import com.j2core.sts.webcrawler.taskservice.dao.UrlDataDao;
import com.j2core.sts.webcrawler.taskservice.dao.WordInformationDao;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.PageInformation;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.URLStatus;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * Created by Sotnyk Tetiana.
 */
/**
 * The class is TaskService interface implementation
 */
@Service
public class DefaultTaskService implements TaskService {

    private final static Logger LOGGER = Logger.getLogger(DefaultTaskService.class);   // class for save logs information
    private final static int deprecatedMilliSecTime = 600000;                          // deprecated time for get task
    private WordInformationDao wordDao;
    private UrlDataDao urlDao;
    private PageInformationDao pageDao;

    @Autowired
    public DefaultTaskService(UrlDataDao urlDataDao, PageInformationDao pageInformationDao, WordInformationDao wordInformationDao) {

        this.urlDao = urlDataDao;
        this.pageDao = pageInformationDao;
        this.wordDao = wordInformationDao;
    }


    public List<UrlData> getNotProcessesTask(int amount, int nodeId) {

        List<UrlData> result = null;
        try {
            result = urlDao.getDeprecateUrl(amount, deprecatedMilliSecTime, nodeId);

            int amountTack = result.size();

            if (amountTack < amount){

                result.addAll(urlDao.getNotProcessesUrl((amount - amountTack), nodeId));

            }
        }catch (Exception exc){

            LOGGER.error(" Get new tasks exception: " + exc);
        }

        return result;
    }

    public boolean addTaskResult(ResultingInformation tackResult) {

        try {

            UrlData urlData = urlDao.get(tackResult.getUrlId());

            if (urlData.getNodeId() == tackResult.getNodeId()){

                PageInformation pageInformation = new PageInformation(tackResult.getPagesText(), new Date(), urlData);
                int pageId = pageDao.add(urlData, pageInformation);

                if ( pageId == 0){

                    urlDao.updateStatus(urlData.getUrlId(), URLStatus.PROCESSED);

                    return true;

                } else if (pageId == -1){

                    return false;

                } else {

                    pageInformation.setPageId(pageId);

                    urlDao.add(tackResult.getUrlCollectionNew());

                    wordDao.save(urlData, pageInformation, tackResult.getWordsInPage());

                    urlDao.updateStatus(urlData.getUrlId(), URLStatus.PROCESSED);

                }
            }

            return true;

        }catch (Exception ex){

            LOGGER.error(" Save task result exception " + ex);

            return false;
        }
    }

    public boolean finalSaveInformation(Queue<ResultingInformation> processedTask, Queue<UrlData> processesTask,
                                        Queue<UrlData> notProcessesTack, int nodeId) {

        Queue<UrlData> resultQueue = null;

        try {
            if (!processedTask.isEmpty()){

                Iterator<ResultingInformation> processedTackIterator = processedTask.iterator();
                while (processedTackIterator.hasNext()){

                    ResultingInformation resultingInformation = processedTackIterator.next();
                    if (addTaskResult(resultingInformation)){

                        processedTackIterator.remove();
                    }
                }
            }

            resultQueue = mergeNotProcessedTask(processedTask, processesTask, notProcessesTack);

            urlDao.updateStatus(verifyAttachmentNode(resultQueue, nodeId), URLStatus.NOT_PROCESSED);

            return true;

        }catch (Exception ex) {

            LOGGER.error(" Final save exception: " + ex);

            try {
                return resultQueue != null && urlDao.updateStatus(verifyAttachmentNode(resultQueue, nodeId), URLStatus.NOT_PROCESSED);

            }catch (Exception e){
                LOGGER.error(" Final save update status exception: " + e);
                return false;
            }
        }

    }

    public Queue<UrlData> verifyAttachmentNode(Queue<UrlData> collectionTask, Integer nodeId) {

        try {
            Iterator<UrlData> urlDataIterator = collectionTask.iterator();
            while (urlDataIterator.hasNext()) {
                UrlData data = urlDataIterator.next();
                UrlData dbData = urlDao.get(data.getUrlId());
                if (!nodeId.equals(dbData.getNodeId())) {
                    urlDataIterator.remove();
                }
            }

        }catch (Exception ex){

            LOGGER.error(" Verify exception: " + ex);
        }
        return collectionTask;
    }

    /**
     * The method merge processed, processes and not processes urls with need return to DB.
     *
     * @param processedTask        collection with processed urls
     * @param processesTask        collection with processes urls
     * @param notProcessesTask     collection with not processes urls
     * @return  total collection with urls
     */
    private Queue<UrlData> mergeNotProcessedTask(Queue<ResultingInformation> processedTask, Queue<UrlData> processesTask,
                                                 Queue<UrlData> notProcessesTask){

        Queue<UrlData> resultQueue = new ArrayBlockingQueue<UrlData>(processedTask.size() + processesTask.size() + notProcessesTask.size());

        if (!processedTask.isEmpty()){

            resultQueue.addAll(getUrlData(processedTask));
        }

        if (!processesTask.isEmpty()){

            resultQueue.addAll(processesTask);
        }

        if (!notProcessesTask.isEmpty()){

            resultQueue.addAll(notProcessesTask);

        }

        return resultQueue;
    }

    /**
     * The method get url's information from resulting information collection
     *
     * @param processedTack   resulting information
     * @return  collection with url's information
     */
    private Queue<UrlData> getUrlData(Queue<ResultingInformation> processedTack){

        Queue<UrlData> urlDataQueue = new ArrayBlockingQueue<UrlData>(processedTack.size());

        for (ResultingInformation result : processedTack){

            UrlData data = new UrlData(result.getPageUrl(), result.getAmountTransition(), URLStatus.NOT_PROCESSED);
            data.setUrlId(result.getUrlId());
            data.setNodeId(0);

            urlDataQueue.add(data);

        }

        return urlDataQueue;

    }
}
