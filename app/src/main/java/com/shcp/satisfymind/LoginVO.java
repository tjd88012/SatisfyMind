package com.shcp.satisfymind;

public class LoginVO {

    //남여 radiobutton 추가할 것
    String nickName;
    String sex;
    String age;

    public LoginVO(String nickName, String sex, String age) {
        this.nickName = nickName;
        this.sex = sex;
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
