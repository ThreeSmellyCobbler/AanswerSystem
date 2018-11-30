package com.tsco.member.enums;

public enum UserRoleEnum {

    ADMIN("管理员"),
    MEMBER("会员");

    UserRoleEnum(String desc) {
        this.desc = desc;
    }

    private String desc;


}
