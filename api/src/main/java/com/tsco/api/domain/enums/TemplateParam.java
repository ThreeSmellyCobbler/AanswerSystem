package com.tsco.api.domain.enums;

public enum TemplateParam {

    VERIFICATION_CODE("verificationCode", "验证码");

    private String desc;
    private String name;

    TemplateParam(String name, String desc) {
        this.desc = desc;
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
