package com.j2core.sts.webcrawler.taskservice.dao;

import com.j2core.sts.webcrawler.taskservice.model.informationdto.PageInformation;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class is default implement interface PageInformationDao
 */
@Repository
public class DefaultPageInformationDao implements PageInformationDao {

    private final static Logger LOGGER = Logger.getLogger(DefaultPageInformationDao.class); // class for save logs information
    private EntityManager entityManager;

    @Autowired
    public DefaultPageInformationDao(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public int add(UrlData urlData, PageInformation pageInformation) {

        try {

            entityManager.createQuery("select e from PageInformation e where e.urlData.urlId = :urlData").
                    setParameter("urlData", urlData.getUrlId()).getSingleResult();

        } catch (NoResultException ex) {

            try {

                entityManager.clear();

                entityManager.getTransaction().begin();

                pageInformation = entityManager.merge(pageInformation);

                entityManager.getTransaction().commit();

                entityManager.clear();
                return pageInformation.getPageId();

            }catch (Exception e){

                LOGGER.error(" Add page exception" + ex);
                entityManager.clear();
                return -1;
            }
        }

        entityManager.clear();
        return 0;
    }

    @Override
    public PageInformation get(int pageId) {

        PageInformation result = null;

        try {

            result = entityManager.find(PageInformation.class, pageId);

        }catch (Exception ex){

            LOGGER.error(" Get page exception" + ex);

        }

        entityManager.clear();
        return result;
    }

    @Override
    public PageInformation get(UrlData urlData) {

        PageInformation result = null;

        try {

            result = (PageInformation) entityManager.createQuery("select e from PageInformation e where e.urlData.urlId = :urlData").
                    setParameter("urlData", urlData.getUrlId()).getSingleResult();

        }catch (Exception ex){

            LOGGER.error(" Get page exception" + ex);

        }

        entityManager.clear();
        return result;
    }

    @Override
    public boolean delete(int pageId) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from PageInformation e where e.pageId = :pageId").
                    setParameter("pageId", pageId).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete page exception" + ex);
            entityManager.clear();
            return false;

        }
    }

    @Override
    public boolean delete(UrlData urlData) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from PageInformation e where e.urlData.urlId = :urlData").
                    setParameter("urlData", urlData.getUrlId()).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete page exception" + ex);
            entityManager.clear();
            return false;

        }
    }

    @Override
    public boolean update(UrlData urlData, String pageText) {

        try {

            PageInformation pageInformation = (PageInformation) entityManager.createQuery("select e from PageInformation e where e.urlData.urlId = :urlData").
                    setParameter("urlData", urlData.getUrlId()).getSingleResult();

            pageInformation.setPageText(pageText);

            entityManager.getTransaction().begin();
            entityManager.merge(pageInformation);
            entityManager.getTransaction().commit();

        }catch (Exception ex){

            LOGGER.error(" Update page exception" + ex);
            entityManager.clear();
            return false;

        }

        entityManager.clear();
        return true;
    }
}
