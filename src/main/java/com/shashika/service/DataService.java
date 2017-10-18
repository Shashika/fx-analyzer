package com.shashika.service;

import com.shashika.common.Constants;
import com.shashika.model.DataFile;
import com.shashika.model.Deal;
import com.shashika.model.IsoSummary;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shashika on 10/16/17.
 */
public class DataService {

    private final static Logger LOGGER = Logger.getLogger(DataService.class.getName());

    private EntityManagerFactory emf;
    private EntityManager em;

    public int saveFile(DataFile dataFile) {

        emf = HibernateUtil.getEntityManagerFactory();
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(dataFile);
        em.getTransaction().commit();
        em.close();

        LOGGER.log(Level.INFO, "File data saved to database");
        return dataFile.getId();
    }

    public int saveDeal(Deal deal) {

        emf = HibernateUtil.getEntityManagerFactory();
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(deal);
        em.getTransaction().commit();
        em.close();

        LOGGER.log(Level.INFO, "Deal record saved to database");
        return deal.getId();
    }

    public void saveSummary(HashMap<String, Integer> currencyMap) {

        emf = HibernateUtil.getEntityManagerFactory();
        em = emf.createEntityManager();
        em.getTransaction().begin();

        for(String key : currencyMap.keySet()){

            IsoSummary record = em.find(IsoSummary.class, key);
            int newCount = currencyMap.get(key);

            if(record != null){

                int count = record.getCountDeals();
                record.setCountDeals(newCount + count);
                em.merge(record);
            }
            else{

                IsoSummary isoSummary = new IsoSummary();
                isoSummary.setIsoCode(key);
                isoSummary.setCountDeals(newCount);
                em.persist(isoSummary);
            }
        }
        em.getTransaction().commit();
        em.close();

        LOGGER.log(Level.INFO, "Summary data saved to database");
    }

    public void bulkSaveDeals(ArrayList<Deal> deals) {

        int dealsSize = deals.size();
        ArrayList<Deal> dealParts;

        for(int x = 0; x < Constants.THREAD_SIZE; x++){

            dealParts = new ArrayList<Deal>();

            for(int y = x*(dealsSize/Constants.THREAD_SIZE); y < (x+1)*dealsSize/Constants.THREAD_SIZE; y++){
                dealParts.add(deals.get(y));
            }

            DataSaver saver = new DataSaver(dealParts);
            saver.start();
        }

        LOGGER.log(Level.INFO, "Bulk data save operation hand overed to other threads");
    }

}
