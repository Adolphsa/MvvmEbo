package com.lucas.ebo.data.bean.request;

/**
 * Created by lucas
 * Date: 2020/5/19 15:58
 *
 * 验证码请求对象
 */
public class AuthCodeRequestBean {

    private String language;

    private String phone_num;

    private String operation;

    private String phone_area;

    private String email;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPhone_area() {
        return phone_area;
    }

    public void setPhone_area(String phone_area) {
        this.phone_area = phone_area;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
