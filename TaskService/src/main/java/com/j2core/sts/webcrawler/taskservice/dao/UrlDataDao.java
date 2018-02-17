package com.j2core.sts.webcrawler.taskservice.dao;

import com.j2core.sts.webcrawler.taskservice.model.informationdto.URLStatus;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;

import java.util.Collection;
import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with URLData's table from database.
 */
public interface UrlDataDao {

    /**
     * The method get URL's data
     *
     * @param urlId   URL's id
     * @return URL's data
     */
    UrlData get(int urlId);

    /**
     * The method get URL's data
     *
     * @param url  URL's link
     * @return  URL's data
     */
    UrlData get(String url);

    /**
     * The method update URL's status
     *
     * @param urlId      URL's id
     * @param status     new status value
     * @return  update was successfully or not
     */
    boolean updateStatus(int urlId, URLStatus status);

    /**
     * The method update URL's status for URL's collection
     *
     * @param collection    URL's collection
     * @param status        new status value
     * @return  update was successfully or not
     */
    boolean updateStatus(Collection<UrlData> collection, URLStatus status);

    /**
     * The method update URL's data
     *
     * @param urlData    new URL's data
     * @return update was successfully or not
     */
    boolean update(UrlData urlData);

    /**
     * The method update data for URL's collection
     *
     * @param collection   URL's collection with new data
     * @return  update was successfully or not
     */
    boolean update(List<UrlData> collection);

    /**
     * The method update nodeId witch attachment URL
     *
     * @param urlId       URL's id
     * @param nodeId      new node's id
     * @return  update was successfully or not
     */
    boolean updateNodeId(int urlId, int nodeId);

    /**
     * The method update nodeId for URL's collection
     *
     * @param urlDataList    URL's collection for update nodeId
     * @param nodeId         new nodeID
     * @return  update was successfully or not
     */
    boolean updateNodeId(List<UrlData> urlDataList, int nodeId);

    /**
     * The method add new URL
     *
     * @param urlData   information about new URL
     * @return add was successfully or not
     */
    boolean add(UrlData urlData);

    /**
     * The method add URL's collection with new URLs
     *
     * @param urlDataCollection    collection with new URLs
     * @return  add was successfully or not
     */
    boolean add(Collection<UrlData> urlDataCollection);

    /**
     * The method delete URL
     *
     * @param urlId   URL's id
     * @return delete was successfully or not
     */
    boolean delete(int urlId);

    /**
     * The method delete URL
     *
     * @param url    URL's link
     * @return  delete was successfully or not
     */
    boolean delete(String url);

    /**
     * The method get not processes URLs
     *
     * @param amount    amount not processes URLs
     * @param nodeId    node id for attached
     * @return   collection with not processes URLs witch attached to the node id
     */
    List<UrlData> getNotProcessesUrl(int amount, int nodeId);

    /**
     * The method get deprecate URLs
     *
     * @param amount          amount get URLs
     * @param deprecateTime   value deprecate time
     * @param nodeId          value new node id
     * @return  collection with deprecate URLs witch attsched to the new node id
     */
    List<UrlData> getDeprecateUrl(int amount, int deprecateTime, int nodeId);

    /**
     * The method update value amount read page
     *
     * @param urlId  URL's id
     * @return  update was successfully or not
     */
    boolean updateAmountReadPage(int urlId);

    /**
     * The method get value amount read page
     *
     * @param urlId    URL's id
     * @return  amount read page's value
     */
    int getAmountReadPage(int urlId);

    /**
     * The method update for collection with URLs
     *
     * @param urlDataList   URL's collection
     * @return    update was successfully or not
     */
    boolean updateAmountReadPage(List<UrlData> urlDataList);

}
