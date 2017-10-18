package com.shashika.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by shashika on 10/16/17.
 */
@Entity
@Table(name="iso_summary")
public class IsoSummary implements Serializable{

    @Id
    private String isoCode;
    private int countDeals;

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public int getCountDeals() {
        return countDeals;
    }

    public void setCountDeals(int countDeals) {
        this.countDeals = countDeals;
    }
}
