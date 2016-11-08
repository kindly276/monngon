package com.vccorp.monngon.model;

import java.util.List;

/**
 * Created by PC0353 on 11/8/2016.
 */

public class HomeReponse extends CommonReponse {
    private List<Mon> mons;

    public List<Mon> getMons() {
        return mons;
    }

    public void setMons(List<Mon> mons) {
        this.mons = mons;
    }
}
