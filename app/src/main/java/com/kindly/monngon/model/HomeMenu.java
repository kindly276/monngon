package com.kindly.monngon.model;

import java.util.List;

/**
 * Created by PC0353 on 10/21/2016.
 */

public class HomeMenu {
    private int type;//type=0 Mon ngon moi ngay type=1 Home
    private List<Mon> listMon;
    private List<Mon> listMonMost;
    private List<Material> listMaterial;
    private List<CookingType> cachnaus;



    public List<Material> getListMaterial() {
        return listMaterial;
    }

    public void setListMaterial(List<Material> listMaterial) {
        this.listMaterial = listMaterial;
    }

    public List<Mon> getListMon() {
        return listMon;
    }

    public void setListMon(List<Mon> listMon) {
        this.listMon = listMon;
    }

    public List<Mon> getListMonMost() {
        return listMonMost;
    }

    public void setListMonMost(List<Mon> listMonMost) {
        this.listMonMost = listMonMost;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<CookingType> getCachnaus() {
        return cachnaus;
    }

    public void setCachnaus(List<CookingType> cachnaus) {
        this.cachnaus = cachnaus;
    }
}
