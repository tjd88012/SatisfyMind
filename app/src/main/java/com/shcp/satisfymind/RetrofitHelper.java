package com.shcp.satisfymind;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    public static Retrofit getInstanceLoad(){
        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl("http://shcp.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    public static Retrofit getInstanceInsert(){
        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl("http://shcp.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        return builder.build();
    }

}
