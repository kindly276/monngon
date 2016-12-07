package com.kindly.monngon.util;

import com.kindly.monngon.model.DayCookingReponse;
import com.kindly.monngon.model.HomeReponse;
import com.kindly.monngon.model.MaterialReponse;
import com.kindly.monngon.model.TypeCookingReponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @FormUrlEncoded
    @POST("get_mons.php")
    Call<HomeReponse> getMons(@Field("page") int page, @Field("num_page") int num_page,@Field("type") int type,@Field("id") int id);
}
