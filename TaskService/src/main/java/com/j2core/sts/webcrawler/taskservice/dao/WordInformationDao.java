package com.j2core.sts.webcrawler.taskservice.dao;

import com.google.common.collect.Multiset;
import com.j2core.sts.webcrawler.taskservice.model.dto.WordEntity;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.PageInformation;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;

import java.util.List;
import java.util.Set;
/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with WordInformation's table from database.
 */
public interface WordInformationDao {

    /**
     * The method save collection words from page in the DB
     *
     * @param urlData                 URL's data
     * @param pageInformation         page's information
     * @param wordsCollection         words collection from page
     * @return  save was successfully or not
     */
    boolean save(UrlData urlData, PageInformation pageInformation, List<WordEntity> wordsCollection);

    /**
     * The method get all words from URL
     *
     * @param urlData  URL's data
     * @return collection with all page's words from URL
     */
    Multiset<String> get(UrlData urlData);

    /**
     * The method get all page's words
     *
     * @param pageInformation    page's information
     * @return collection with all page's words
     */
    Multiset<String> get(PageInformation pageInformation);

    /**
     * The method delete all page's words from URL
     *
     * @param urlData   URL's data
     * @return delete was successfully or not
     */
    boolean delete(UrlData urlData);

    /**
     * The method delete all page's words
     *
     * @param pageInformation   page's information
     * @return  delete was successfully or not
     */
    boolean delete(PageInformation pageInformation);

}
