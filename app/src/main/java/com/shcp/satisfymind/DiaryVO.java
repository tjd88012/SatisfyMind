package com.shcp.satisfymind;

public class DiaryVO {

    String num;
    String tvDiary;
    String tvDate;

    public DiaryVO(String num, String tvDiary, String tvDate) {
        this.num = num;
        this.tvDiary = tvDiary;
        this.tvDate = tvDate;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTvDiary() {
        return tvDiary;
    }

    public void setTvDiary(String tvDiary) {
        this.tvDiary = tvDiary;
    }

    public String getTvDate() {
        return tvDate;
    }

    public void setTvDate(String tvDate) {
        this.tvDate = tvDate;
    }
}
