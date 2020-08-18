package com.shcp.satisfymind;

public class WorryListItem {

    int no;
    String title;
    String text;
    String nickname;
    String sex;
    int favor;

    public WorryListItem() {
    }

    public WorryListItem(int no, String title, String text, String nickname, String sex, int favor) {
        this.no = no;
        this.title = title;
        this.text = text;
        this.nickname = nickname;
        this.sex = sex;
        this.favor = favor;
    }
}
