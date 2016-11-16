package com.vccorp.monngon.model;

import java.util.List;

/**
 * Created by PC0353 on 11/16/2016.
 */

public class DayCookingReponse extends CommonReponse {
    private List<DayCooking> dipnaus;

    public List<DayCooking> getDipnaus() {
        return dipnaus;
    }

    public void setDipnaus(List<DayCooking> dipnaus) {
        this.dipnaus = dipnaus;
    }
}
