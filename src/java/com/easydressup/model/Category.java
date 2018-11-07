package com.easydressup.model;

import java.io.Serializable;

/**
 * Cloths category
 *
 * @author
 */
public class Category implements Serializable{

    private int categoryId;
    private String categoryName;
    private Category parent;

    public Category(int categoryId, String categoryName, Category parent) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parent = parent;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

}
