package com.example.sabkasbfa.ui.models;

public class NavCategoryModels {
    String name;
    String description;
    String img_url;
    String type;

    public NavCategoryModels() {
    }

    public NavCategoryModels(String name, String description, String type, String img_url) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.img_url = img_url;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
