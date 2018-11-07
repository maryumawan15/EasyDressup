package com.easydressup.model;

import java.io.Serializable;

/**
 *
 * @author
 */
public class Cloth implements Serializable{

    private int clothId;
    private Category catgroy;
    private byte[] image;
    private String contenttype;
    private String fileName;

    public Cloth(int clothId, Category catgroy, byte[] image, String contenttype, String fileName) {
        this.clothId = clothId;
        this.catgroy = catgroy;
        this.image = image;
        this.contenttype = contenttype;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getClothId() {
        return clothId;
    }

    public Category getCatgroy() {
        return catgroy;
    }

    public void setCatgroy(Category catgroy) {
        this.catgroy = catgroy;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

}
