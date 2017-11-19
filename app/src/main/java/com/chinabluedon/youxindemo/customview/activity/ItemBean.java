package com.chinabluedon.youxindemo.customview.activity;

import java.io.Serializable;

/**
 * @author ht
 * @time 2017/9/9  11:20
 * @desc ${TODD}
 */
public class ItemBean implements Serializable {

    public final static int TYPE_SINGLE = 1;
    public final static int TYPE_DOUBLE = 2;

    private int type;

    private String title;

    private int imageViewId;

    public ItemBean (String title, int imageViewId, int type) {
        this.title = title;
        this.imageViewId = imageViewId;
        this.type = type;
    }

    public int getType () {
        return type;
    }

    public void setType (int type) {
        this.type = type;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public int getImageViewId () {
        return imageViewId;
    }

    public void setImageViewId (int imageViewId) {
        this.imageViewId = imageViewId;
    }
}
