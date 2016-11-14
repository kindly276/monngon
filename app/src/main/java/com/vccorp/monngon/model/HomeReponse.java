package com.vccorp.monngon.model;

import java.util.List;

/**
 * Created by PC0353 on 11/8/2016.
 */

public class HomeReponse extends CommonReponse {
    private List<Mon> mons;
    private List<Material> materials;

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Mon> getMons() {
        return mons;
    }

    public void setMons(List<Mon> mons) {
        this.mons = mons;
    }
}
