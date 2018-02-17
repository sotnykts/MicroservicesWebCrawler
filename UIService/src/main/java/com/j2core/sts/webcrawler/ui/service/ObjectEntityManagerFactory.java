package com.j2core.sts.webcrawler.ui.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The class is singleton object for entity manager factory
 */
public class ObjectEntityManagerFactory {

    private static volatile EntityManagerFactory entityManagerFactory = null;   // object for work with DB

    /**
     * The method get entity manager factory object for work with DB, if this object exist, else create this object.
     *
     * @return  entity manager factory object
     */
    public static EntityManagerFactory getEntityManagerEntity(){

        if (entityManagerFactory == null){

            synchronized (EntityManagerFactory.class){

                if (entityManagerFactory == null){

                    entityManagerFactory = Persistence.createEntityManagerFactory("webCrawler");
                }
            }
        }
        return entityManagerFactory;
    }

}
