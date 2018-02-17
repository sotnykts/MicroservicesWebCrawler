package com.j2core.sts.webcrawler.taskservice.dao;


import com.j2core.sts.webcrawler.taskservice.model.informationdto.PageInformation;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with pageInformation's table from database.
 */
public interface PageInformationDao {

    /**
     * The method add new page information
     *
     * @param urlData            URL's data
     * @param pageInformation    page's information
     * @return   page's id
     */
    int add(UrlData urlData, PageInformation pageInformation);

    /**
     * The method get page information
     *
     * @param pageId   page's id
     * @return  page's information
     */
    PageInformation get(int pageId);

    /**
     * The method get page's information
     *
     * @param urlData   URL's data
     * @return  page's information
     */
    PageInformation get(UrlData urlData);

    /**
     * The method delete page's information
     *
     * @param pageId   page's id
     * @return  delete was successfully or not
     */
    boolean delete(int pageId);

    /**
     * The method delete page's information
     *
     * @param urlData    URL's data
     * @return delete was successfully or not
     */
    boolean delete(UrlData urlData);

    /**
     * The method update page's information
     *
     * @param urlData     URL's data
     * @param pageText    new page's information
     * @return update was successfully or not
     */
    boolean update(UrlData urlData, String pageText);

}
