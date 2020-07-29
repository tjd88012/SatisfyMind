package com.shcp.satisfymind;

public class FSListItem {

    int no;
    String title;
    String text;
    String nickName;
    int favor;

    public FSListItem() {
    }

    public FSListItem(int no, String title, String text, String nickName, int favor) {
        this.no = no;
        this.title = title;
        this.text = text;
        this.nickName = nickName;
        this.favor = favor;
    }
}
