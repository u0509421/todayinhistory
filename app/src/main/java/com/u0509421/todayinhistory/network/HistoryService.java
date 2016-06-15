package com.u0509421.todayinhistory.network;

import com.u0509421.todayinhistory.db.bean.Result;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Terry on 5/6/16.
 */
public interface HistoryService {

    // 请求示例：http://v.juhe.cn/todayOnhistory/queryEvent.php?key=YOURKEY&date=1/1

    String BASE_URL = "http://v.juhe.cn/todayOnhistory/";
    String KEY = "a87c2d7033aedc2b2460de9117588285";

    @GET("queryEvent.php")
    Observable<Result> getResult(@Query("key") String key,
                                 @Query("date") String date);
}
