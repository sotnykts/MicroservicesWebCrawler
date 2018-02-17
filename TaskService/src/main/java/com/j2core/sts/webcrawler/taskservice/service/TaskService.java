package com.j2core.sts.webcrawler.taskservice.service;

import com.j2core.sts.webcrawler.taskservice.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;

import java.util.List;
import java.util.Queue;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with task's service.
 */
public interface TaskService {

    /**
     * The method get not processed URLs from DB
     *
     * @param amount    amount not processed URLs
     * @param nodeId    attachment node Id
     * @return  collection with not processed URLs
     */
    List<UrlData> getNotProcessesTask(int amount, int nodeId);

    /**
     * The method add task result's information to the DB
     *
     * @param tackResult  task result's information (page's information, words from page, new URLs from page, etc.)
     * @return  add information successfully or not
     */
    boolean addTaskResult(ResultingInformation tackResult);

    /**
     * The method final save information to the DB
     *
     * @param processedTask         processed URLs
     * @param processesTask         processes URLs
     * @param notProcessesTack      not processed URLs
     * @param nodeId                attachment node Id
     * @return  final save information successfully or not
     */
    boolean finalSaveInformation(Queue<ResultingInformation> processedTask, Queue<UrlData> processesTask,
                                 Queue<UrlData> notProcessesTack, int nodeId);

    /**
     * The method verify attachment node Id
     *
     * @param collectionTask   collection with URLs
     * @param nodeId           attachment node Id
     * @return  return collection with attachment task for this node
     */
    Queue<UrlData> verifyAttachmentNode(Queue<UrlData> collectionTask, Integer nodeId);
}
