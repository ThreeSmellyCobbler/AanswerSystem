package com.tsco.api.domain.enums;

public enum SubjectTypeEnum {

    MULTIPLY_CHOICE("多选题"), SINGLE_CHOICE("单选题"), ALGORITHM("算法题");

    private String desc;

    SubjectTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
