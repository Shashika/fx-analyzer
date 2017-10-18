package com.shashika.service;

import com.shashika.model.Deal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

/**
 * Created by shashika on 10/17/17.
 */
public class DataSaver extends Thread {

    private ArrayList<Deal> deals;
    private EntityManagerFactory emf;
    private EntityManager em;
    volatile boolean finished = false;

    public DataSaver(ArrayList<Deal> deals){
        this.deals = deals;
        emf = HibernateUtil.getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    private void saveDeals(){

        em.getTransaction().begin();
        for (int x = 0; x < deals.size(); x++){

            em.persist(deals.get(x));

            if (x % 1000 == 0) {
                em.flush();
                em.clear();
            }
        }
        em.getTransaction().commit();
        Thread.currentThread().stop();
    }

    @Override
    public void run() {

        saveDeals();
    }
}
