package com.shashika.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shashika on 10/16/17.
 */
@Entity
@Table(name="files")
public class DataFile implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String fileName;

    public DataFile(){}

    public DataFile(String fileName){
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
