package com.tsco.api.domain.enums;

public enum EmailTemplateEnum {
    REGISTER_VERIFICATION("registerTemplate", "注册验证码");

    EmailTemplateEnum(String name, String desc) {
        this.desc = desc;
        this.name = name;
    }

    private String desc;

    private String name;

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
