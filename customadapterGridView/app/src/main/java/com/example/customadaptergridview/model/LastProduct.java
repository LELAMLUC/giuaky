package com.example.customadaptergridview.model;

public class LastProduct {
    private String name;
    private String desc;
    private String imageUrl;
    public LastProduct(String name, String desc,String imageUrl) {
        this.name = name;
        this.desc = desc;
        this.imageUrl = imageUrl;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
