package com.shcp.satisfymind;

public class WorryListItem {

    int no;
    String title;
    String text;
    String nickName;
    String sex;
    int favor;

    public WorryListItem() {
    }

    public WorryListItem(int no, String title, String text, String nickName, String sex, int favor) {
        this.no = no;
        this.title = title;
        this.text = text;
        this.nickName = nickName;
        this.sex = sex;
        this.favor = favor;
    }
}
