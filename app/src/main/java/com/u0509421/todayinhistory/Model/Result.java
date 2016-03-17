package com.u0509421.todayinhistory.Model;

/**
 * Created by Terry on 17/3/16.
 */
public class Result {

    private String day;
    private String date;
    private String title;
    private String e_id;

    public Result(){
        super();
    }
    public Result(String day,String date,String title,String e_id){
        super();
        this.day = day;
        this.date = date;
        this.title = title;
        this.e_id = e_id;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getE_id() {
        return e_id;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
