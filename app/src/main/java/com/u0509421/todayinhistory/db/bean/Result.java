package com.u0509421.todayinhistory.db.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry on 15/6/16.
 */
public class Result {
    /**
     * reason : success
     * result : [{"day":"1/1","date":"前45年01月01日","title":"罗马共和国开始使用儒略历","e_id":"1"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    /**
     * day : 1/1
     * date : 前45年01月01日
     * title : 罗马共和国开始使用儒略历
     * e_id : 1
     */

    private ArrayList<EventList> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ArrayList<EventList> getResult() {
        return result;
    }

    public void setResult(ArrayList<EventList> result) {
        this.result = result;
    }

}
