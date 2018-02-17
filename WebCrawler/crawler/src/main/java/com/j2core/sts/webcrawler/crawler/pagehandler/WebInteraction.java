package com.j2core.sts.webcrawler.crawler.pagehandler;

/*
 * Created by sts on 1/20/16.
 */

import com.j2core.sts.webcrawler.crawler.pagehandler.exception.NotReadPageException;
import com.j2core.sts.webcrawler.dao.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.dao.model.informationdto.UrlData;

/**
 * Interface for analyse page's information
 */
public interface WebInteraction {

    /**
     * Method for analyse page's information from URL
     *
     * @param pagesUrl  page's URL for analyse information
     * @return Page's information
     */
    ResultingInformation analysePageInformation(UrlData pagesUrl) throws NotReadPageException;
}
