package com.tsco.api.domain.enums;

public enum SubjectStatusEnum {

    ONGOING("正在使用"), AUDITING("审核中"), REJECT("拒绝");

    private String desc;

    SubjectStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
