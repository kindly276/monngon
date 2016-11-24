package com.kindly.monngon.model;

import java.util.List;

/**
 * Created by PC0353 on 11/8/2016.
 */

public class HomeReponse extends CommonReponse {
    private List<Mon> mons;
    private List<Mon> monviews;


    public List<Mon> getMonviews() {
        return monviews;
    }

    public void setMonviews(List<Mon> monviews) {
        this.monviews = monviews;
    }

    public List<Mon> getMons() {
        return mons;
    }

    public void setMons(List<Mon> mons) {
        this.mons = mons;
    }


}
