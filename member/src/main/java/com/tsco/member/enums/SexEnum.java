package com.tsco.member.enums;

public enum SexEnum {
    MALE("男"),
    FEMALE("女");

    SexEnum(String desc) {
        this.desc = desc;
    }

    private String desc;
}
