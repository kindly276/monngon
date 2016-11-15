package com.vccorp.monngon.model;

import java.util.List;

/**
 * Created by rau muong on 15/11/2016.
 */
public class TypeCookingReponse extends CommonReponse {
    private List<Cooking> cachnaus;

    public List<Cooking> getCachnaus() {
        return cachnaus;
    }

    public void setCachnaus(List<Cooking> cachnaus) {
        this.cachnaus = cachnaus;
    }
}
