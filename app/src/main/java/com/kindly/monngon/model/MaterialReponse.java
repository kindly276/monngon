package com.kindly.monngon.model;

import java.util.List;

/**
 * Created by rau muong on 15/11/2016.
 */
public class MaterialReponse extends CommonReponse {
    private List<Material> materials;

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}
