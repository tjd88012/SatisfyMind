package com.shcp.satisfymind;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

public interface RetrofitService {

    @GET("/login/FamousSaying/FSInsert.php")
    Call<String> insertFS(@QueryMap Map<String, String> item);

    @FormUrlEncoded
    @POST("/login/Worry/WorryInsert.php")
    Call<String> insertWorry(@FieldMap Map<String, String> item);

    @Multipart
    @POST("login/HealingPhoto/HPInsert.php")
    Call<String> insertHP(@PartMap Map<String, String> item, @Part MultipartBody.Part file);

    @GET("login/FamousSaying/FSLoad.php")
    Call<ArrayList<FSListItem>> loadFS();

    @GET("login/Worry/WorryLoad.php")
    Call<ArrayList<WorryListItem>> loadWorry();

    @GET("login/HealingPhoto/HPLoad.php")
    Call<ArrayList<HPListItem>> loadHP();

}
