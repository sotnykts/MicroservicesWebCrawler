package com.j2core.sts.webcrawler.ui.dto.url_dto;

/**
 * Enum for save URL's status
 */
public enum  URLStatus {

    NOT_PROCESSED ("not_processed", 0),
    PROCESSES("processes", 1),
    PROCESSED("processed", 2);

    private final String status;         // URL's status
    private final int statusNumber;      // status's number

    /**
     * Constructor
     *
     * @param status                status's name
     * @param statusNumber          status's number
     */
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
