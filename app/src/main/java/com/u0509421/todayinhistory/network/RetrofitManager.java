package com.u0509421.todayinhistory.network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Terry on 15/6/16.
 */
public class RetrofitManager {

    private static HistoryService historyService = null;
    private static Retrofit retrofit = null;

    public static void init(Context context){
        initRetrofit();
        historyService = retrofit.create(HistoryService.class);
    }

    private static void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(HistoryService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static HistoryService getHistoryService(Context context){
        if (historyService != null){
            return historyService;
        }
        init(context);
        return getHistoryService(context);
    }
}
