package com.tsco.member.enums;

public enum USERTYPE {

    ADMIN("管理员"),
    MEMBER("会员");

    USERTYPE(String desc) {
        this.desc = desc;
    }

    private String desc;


}
