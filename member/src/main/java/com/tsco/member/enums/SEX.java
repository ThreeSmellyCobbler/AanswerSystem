package com.tsco.member.enums;

public enum SEX {
    MALE("男"),
    FEMALE("女");

    SEX(String desc) {
        this.desc = desc;
    }

    private String desc;

}
