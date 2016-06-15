package com.u0509421.todayinhistory.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Terry on 29/3/16.
 */
public class VolleyUtil {

    private volatile static RequestQueue requestQueue;

    public static RequestQueue getRequestQueue(Context context){
        if (requestQueue == null){
            synchronized (VolleyUtil.class){
                if (requestQueue == null){
                    requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                }
            }
        }
        return requestQueue;
    }
}
