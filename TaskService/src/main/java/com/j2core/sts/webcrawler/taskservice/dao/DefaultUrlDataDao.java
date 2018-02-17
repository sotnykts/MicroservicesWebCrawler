package com.j2core.sts.webcrawler.taskservice.dao;

import com.j2core.sts.webcrawler.taskservice.model.informationdto.URLStatus;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class is default implement interface UrlDataDao
 */
@Repository
public class DefaultUrlDataDao implements UrlDataDao {

    private final static Logger LOGGER = Logger.getLogger(DefaultUrlDataDao.class); // class for save logs information
    private EntityManager entityManager;

    @Autowired
    public DefaultUrlDataDao(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public UrlData get(int urlId) {

        UrlData result = null;

        try {

            result = entityManager.find(UrlData.class, urlId);

        }catch (Exception ex){

            LOGGER.error(" Get URL exception" + ex);
        }

        entityManager.clear();
        return result;
    }

    @Override
    public UrlData get(String url) {

        UrlData result = null;

        try {

            result = (UrlData) entityManager.createQuery("select e from UrlData e where e.url = :url").
                    setParameter("url", url).getSingleResult();

        }catch (Exception ex){

            LOGGER.error(" Get URL exception" + ex);

        }

        entityManager.clear();
        return result;
    }

    @Override
    public boolean updateStatus(int urlId, URLStatus status) {

        try {
            UrlData data = entityManager.find(UrlData.class, urlId);

            data.setStatus(status);
            data.setStatusChangeTime(System.currentTimeMillis());

            entityManager.getTransaction().begin();
            entityManager.merge(data);
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Update status URL exception" + ex);
            entityManager.clear();
            return false;
        }
    }

    @Override
    public boolean updateStatus(Collection<UrlData> collection, URLStatus status) {

        try {
            for (UrlData data : collection) {

                data.setStatusChangeTime(System.currentTimeMillis());
                data.setStatus(status);

                entityManager.getTransaction().begin();
                entityManager.merge(data);
                entityManager.getTransaction().commit();

            }

            entityManager.clear();
            return true;
        }catch (Exception ex){

            LOGGER.error(" Update status collection URL exception" + ex);
            entityManager.clear();
            return false;
        }
    }

    @Override
    public boolean update(UrlData urlData) {

        boolean result = true;

        try {

            entityManager.getTransaction().begin();
            entityManager.merge(urlData);
            entityManager.getTransaction().commit();

        }catch (Exception ex){

            result = false;
            LOGGER.error(" Update URL exception" + ex);
        }

        entityManager.clear();
        return result;
    }

    @Override
    public boolean update(List<UrlData> collection) {

        try {
            entityManager.getTransaction().begin();

            for (UrlData urlData : collection){

                entityManager.merge(urlData);

            }
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Update collection URL exception" + ex);
            entityManager.clear();
            return false;
        }
    }

    @Override
    public boolean updateNodeId(int urlId, int nodeId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean updateNodeId(List<UrlData> urlDataList, int nodeId) {

        try {

            entityManager.getTransaction().begin();

            for (UrlData urlData: urlDataList){

                urlData.setNodeId(nodeId);
                entityManager.merge(urlData);
            }

            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Update node id URL exception" + ex);
            entityManager.clear();
            return false;
        }
    }

    @Override
    public boolean add(UrlData urlData) {

        try {
            try {

                entityManager.createQuery("select e from UrlData e where e.url = :url").
                        setParameter("url", urlData.getUrl()).getSingleResult();

            }catch (NoResultException ex){

                entityManager.clear();
                entityManager.getTransaction().begin();
                entityManager.persist(urlData);
                entityManager.getTransaction().commit();

            }

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Add new URL exception" + ex);
            entityManager.clear();
            return false;
        }
    }

    @Override
    public boolean add(Collection<UrlData> urlDataCollection) {

        try {
            entityManager.getTransaction().begin();
            for (UrlData urlData : urlDataCollection) {

                try {

                    entityManager.createQuery("select e from UrlData e where e.url = :url").
                            setParameter("url", urlData.getUrl()).getSingleResult();

                } catch (NoResultException ex) {

                    entityManager.clear();

                    entityManager.persist(urlData);
                }
            }

            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Add new collection URL exception" + ex);
            entityManager.clear();
            return false;
        }
    }

    @Override
    public boolean delete(int urlId) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from UrlData e where e.urlId = :urlId").
                    setParameter("urlId", urlId).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete URL exception" + ex);
            entityManager.clear();
            return false;

        }

    }

    @Override
    public boolean delete(String url) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from UrlData e where e.url = :url").
                    setParameter("url", url).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete URL exception" + ex);
            entityManager.clear();
            return false;

        }

    }

    @Override
    public List<UrlData> getNotProcessesUrl(int amount, int nodeId){

        try {
            List<UrlData> result = entityManager.createQuery("select e from UrlData e where e.status = :status and e.amountReadPage < 7").
                    setParameter("status", URLStatus.NOT_PROCESSED).setMaxResults(amount).getResultList();
            entityManager.clear();

            if (result.isEmpty()) return new LinkedList<UrlData>();
            if (updateNodeId(result, nodeId) && updateStatus( result, URLStatus.PROCESSES) && updateAmountReadPage(result) ) {

                entityManager.clear();
                return result;

            } else {

                entityManager.clear();
                return new LinkedList<UrlData>();
            }
        }catch (Exception ex){

            LOGGER.error(" Get not processes URL exception" + ex);
            entityManager.clear();
            return new LinkedList<UrlData>();
        }
    }

    @Override
    public List<UrlData> getDeprecateUrl(int amount, int deprecateTime, int nodeId) {

        long unixTime = System.currentTimeMillis();
        long oldTime = unixTime - deprecateTime;

        try {

            List<UrlData> result = entityManager.createQuery("select e from UrlData e where e.status = :status " +
                    "and e.statusChangeUnixTime < :time and e.nodeId != :nodeId and e.amountReadPage < 7").setParameter("status", URLStatus.PROCESSES)
                    .setParameter("time", oldTime).setParameter("nodeId", nodeId).setMaxResults(amount).getResultList();

            if (result.isEmpty()){
                entityManager.clear();
                return new LinkedList<UrlData>();
            }else {
                entityManager.clear();

                if (updateNodeId(result, nodeId) && updateStatus(result, URLStatus.PROCESSES)) {

                    entityManager.clear();
                    return result;

                } else {
                    entityManager.clear();
                    return new LinkedList<UrlData>();
                }
            }

        }catch (Exception ex){

            LOGGER.error(" Get deprecate URL exception" + ex);
            entityManager.clear();
            return new LinkedList<UrlData>();
        }
    }

    @Override
    public boolean updateAmountReadPage(int urlId) {

        UrlData urlData;
        boolean result;

        try {

            urlData = entityManager.find(UrlData.class, urlId);

            urlData.setAmountReadPage(urlData.getAmountTransition() + 1);

            entityManager.getTransaction().begin();

            entityManager.merge(urlData);

            entityManager.getTransaction().commit();

            result = true;

        }catch (Exception ex){

            LOGGER.error(" Update amount read page URL exception" + ex);
            result = false;

        }

        entityManager.clear();
        return result;
    }

    @Override
    public int getAmountReadPage(int urlId) {

        UrlData result = null;

        try {

            result = entityManager.find(UrlData.class, urlId);

        }catch (Exception ex){

            LOGGER.error(" Get amount read page URL exception" + ex);

        }

        entityManager.clear();
        assert result != null;
        return result.getAmountReadPage();
    }

    @Override
    public boolean updateAmountReadPage(List<UrlData> urlDataList) {

        try {

            for (UrlData url : urlDataList){

                updateAmountReadPage(url.getNodeId());
            }

            return true;

        }catch (Exception ex){

            LOGGER.error(" Update amount read page URL exception" + ex);
            return false;

        }
    }

}
