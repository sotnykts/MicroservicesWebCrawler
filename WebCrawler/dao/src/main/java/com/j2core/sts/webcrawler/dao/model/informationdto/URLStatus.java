package com.j2core.sts.webcrawler.dao.model.informationdto;

/**
 * Created by sts on 8/18/16.
 */

/**
 * Class enum with URL's status
 */
public enum URLStatus {

    NOT_PROCESSED ("not_processed", 0),
    PROCESSES("processes", 1),
    PROCESSED("processed", 2);

    private final String status;         // URL's status
    private final int statusNumber;      // status's number


    URLStatus(String status, int statusNumber){

        this.status = status;
        this.statusNumber = statusNumber;

    }


    public String getStatus(){
        return status;
    }


    public int getStatusNumber() {
        return statusNumber;
    }

}
