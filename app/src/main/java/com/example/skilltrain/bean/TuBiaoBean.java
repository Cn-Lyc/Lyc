package com.example.skilltrain.bean;

public class TuBiaoBean {
    String name;
    int img;

    public TuBiaoBean(String name,int img ){
        this.img=img;
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
