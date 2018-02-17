package com.j2core.sts.webcrawler.taskservice.dao;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.j2core.sts.webcrawler.taskservice.model.dto.WordEntity;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.PageInformation;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.WordInformation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class is default implement interface WordInformationDao
 */
@Repository("wordInformationDao")
public class DefaultWordInformationDao implements WordInformationDao {

    private final static Logger LOGGER = Logger.getLogger(DefaultWordInformationDao.class); // class for save logs information
    private EntityManager entityManager;

    @Autowired
    public DefaultWordInformationDao(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public boolean save(UrlData urlData, PageInformation pageData, List<WordEntity> wordsInPage) {

        try{

            entityManager.getTransaction().begin();

            for (WordEntity word : wordsInPage){

                WordInformation wordData = new WordInformation(urlData, pageData, word.getWord(), word.getAmount());

                entityManager.persist(wordData);

            }

            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception e){

            LOGGER.error(" Save words exception" + e);
            entityManager.clear();
            return false;
        }

    }

    @Override
    public Multiset<String> get(UrlData urlData) {

        Multiset<String> result = null;
        List<WordInformation> wordCollection;

        try {

            wordCollection = entityManager.createQuery("select e from WordInformation e where e.urlData = :urlData").
                    setParameter("urlData", urlData).getResultList();

            entityManager.flush();

            result = HashMultiset.create();

            for (WordInformation word : wordCollection){

                result.add(word.getWord(), word.getAmountWord());

            }

        }catch (Exception ex){

            LOGGER.error(" Get words exception" + ex);

        }

        entityManager.clear();
        return result;
    }

    @Override
    public Multiset<String> get(PageInformation pageInformation) {

        Multiset<String> result = null;
        List<WordInformation> wordCollection;

        try {

            wordCollection = entityManager.createQuery("select e from WordInformation e where e.pageData = :pageInformation").
                    setParameter("pageInformation", pageInformation).getResultList();

            entityManager.flush();

            result = HashMultiset.create();

            for (WordInformation word : wordCollection){

                result.add(word.getWord(), word.getAmountWord());

            }

        }catch (Exception ex){

            LOGGER.error(" Get words exception" + ex);

        }

        entityManager.clear();
        return result;
    }

    @Override
    public boolean delete(UrlData urlData) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from WordInformation e where e.urlData = :urlData").
                    setParameter("urlData", urlData).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.flush();

        }catch (Exception ex){

            LOGGER.error(" Delete words exception" + ex);
            entityManager.clear();
            return false;

        }

        entityManager.clear();
        return true;
    }

    @Override
    public boolean delete(PageInformation pageInformation) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from WordInformation e where e.pageData = :pageInformation").
                    setParameter("pageInformation", pageInformation).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.flush();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete words exception" + ex);
            entityManager.clear();
            return false;

        }
    }

}
