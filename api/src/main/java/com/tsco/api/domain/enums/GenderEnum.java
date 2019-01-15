package com.tsco.api.domain.enums;

public enum GenderEnum {

    MALE("男"), FEMALE("女");


    GenderEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }


}
