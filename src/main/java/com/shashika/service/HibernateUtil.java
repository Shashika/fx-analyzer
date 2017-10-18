package com.shashika.service;

import com.shashika.analyzer.servlet.FileUploadServlet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shashika on 10/17/17.
 */
public class HibernateUtil {

    private final static Logger LOGGER = Logger.getLogger(HibernateUtil.class.getName());

    private static final EntityManagerFactory entityManagerFactory;
    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa-fxanalyzer");

        } catch (Throwable ex) {

            LOGGER.log(Level.SEVERE, "Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
