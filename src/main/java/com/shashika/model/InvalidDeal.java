package com.shashika.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shashika on 10/16/17.
 */
@Entity
@Table(name="invalid_deals")
public class InvalidDeal extends Deal implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "file_id", nullable = false)
    private DataFile fileId;

    private String fromIso;
    private String toIso;
    private long timestamp;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataFile getFileId() {
        return fileId;
    }

    public void setFileId(DataFile fileId) {
        this.fileId = fileId;
    }

    public String getFromIso() {
        return fromIso;
    }

    public void setFromIso(String fromIso) {
        this.fromIso = fromIso;
    }

    public String getToIso() {
        return toIso;
    }

    public void setToIso(String toIso) {
        this.toIso = toIso;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
