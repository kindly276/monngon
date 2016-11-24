package com.kindly.monngon.model;

import java.util.List;

/**
 * Created by rau muong on 15/11/2016.
 */
public class TypeCookingReponse extends CommonReponse {
    private List<CookingType> cachnaus;

    public List<CookingType> getCachnaus() {
        return cachnaus;
    }

    public void setCachnaus(List<CookingType> cachnaus) {
        this.cachnaus = cachnaus;
    }
}
