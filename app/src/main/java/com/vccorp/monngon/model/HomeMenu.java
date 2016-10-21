package com.vccorp.monngon.model;

import java.util.List;

/**
 * Created by PC0353 on 10/21/2016.
 */

public class HomeMenu {
    private int type;//type=0 Mon ngon moi ngay type=1 Home
    private List<Mon> listMon;

    public List<Mon> getListMon() {
        return listMon;
    }

    public void setListMon(List<Mon> listMon) {
        this.listMon = listMon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
