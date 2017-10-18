package com.shashika.model;

/**
 * Created by shashika on 10/17/17.
 */
public class Deal {

    private int id;
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
