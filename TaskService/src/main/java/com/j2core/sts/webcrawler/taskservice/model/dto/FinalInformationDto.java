package com.j2core.sts.webcrawler.taskservice.model.dto;

import com.j2core.sts.webcrawler.taskservice.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;
import com.j2core.sts.webcrawler.taskservice.model.userdto.SecurityToken;

import java.util.Queue;
/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class container for sand final information witch need save to the DB
 */
public class FinalInformationDto {

    private SecurityToken token;
    private Queue<ResultingInformation> processedTask;
    private Queue<UrlData> processesTask;
    private Queue<UrlData> notProcessesTask;

    public FinalInformationDto(){

    }

    public SecurityToken getToken() {
        return token;
    }

    public void setToken(SecurityToken token) {
        this.token = token;
    }

    public Queue<ResultingInformation> getProcessedTask() {
        return processedTask;
    }

    public void setProcessedTask(Queue<ResultingInformation> processedTask) {
        this.processedTask = processedTask;
    }

    public Queue<UrlData> getProcessesTask() {
        return processesTask;
    }

    public void setProcessesTask(Queue<UrlData> processesTask) {
        this.processesTask = processesTask;
    }

    public Queue<UrlData> getNotProcessesTask() {
        return notProcessesTask;
    }

    public void setNotProcessesTask(Queue<UrlData> notProcessesTask) {
        this.notProcessesTask = notProcessesTask;
    }
}
