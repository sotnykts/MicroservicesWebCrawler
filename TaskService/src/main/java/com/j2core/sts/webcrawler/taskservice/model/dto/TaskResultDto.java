package com.j2core.sts.webcrawler.taskservice.model.dto;

import com.j2core.sts.webcrawler.taskservice.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.taskservice.model.userdto.SecurityToken;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class container for sand task's result
 */
public class TaskResultDto {

    private SecurityToken token;
    private ResultingInformation result;

    public TaskResultDto(){

    }

    public TaskResultDto(SecurityToken token, ResultingInformation result) {
        this.token = token;
        this.result = result;
    }

    public SecurityToken getToken() {
        return token;
    }

    public void setToken(SecurityToken token) {
        this.token = token;
    }

    public ResultingInformation getResult() {
        return result;
    }

    public void setResult(ResultingInformation result) {
        this.result = result;
    }
}
