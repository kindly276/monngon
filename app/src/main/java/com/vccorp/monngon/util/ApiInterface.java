package com.vccorp.monngon.util;

import com.vccorp.monngon.model.HomeReponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PC0353 on 11/8/2016.
 */

public interface ApiInterface {
    @GET("get_all_products.php")
    Call<HomeReponse> getHome();
}
