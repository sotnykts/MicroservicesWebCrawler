package com.j2core.sts.webcrawler.dao.model.dto;


import com.j2core.sts.webcrawler.dao.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.dao.model.userdto.SecurityToken;

/**
 * Created by sts on 8/9/17.
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
