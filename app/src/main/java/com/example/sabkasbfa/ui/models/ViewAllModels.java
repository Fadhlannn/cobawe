package com.example.sabkasbfa.ui.models;

import java.io.Serializable;

public class ViewAllModels implements Serializable {
    String name;
    String description;
    String type;
    String img_url;
    String price;

    public ViewAllModels() {
    }

    public ViewAllModels(String description, String name, String type, String img_url, String price) {
        this.description = description;
        this.name = name;
        this.type = type;
        this.img_url = img_url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}