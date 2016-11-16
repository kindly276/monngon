package com.vccorp.monngon.util;

import com.vccorp.monngon.model.DayCookingReponse;
import com.vccorp.monngon.model.HomeReponse;
import com.vccorp.monngon.model.MaterialReponse;
import com.vccorp.monngon.model.TypeCookingReponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PC0353 on 11/8/2016.
 */

public interface ApiInterface {
    @GET("get_all_products.php")
    Call<HomeReponse> getHome();
    @GET("get_all_material.php")
    Call<MaterialReponse> getMaterial();
    @GET("get_all_cachnau.php")
    Call<TypeCookingReponse> getTypeCooking();
    @GET("get_all_dipnau.php")
    Call<DayCookingReponse> getDayCooking();
}
