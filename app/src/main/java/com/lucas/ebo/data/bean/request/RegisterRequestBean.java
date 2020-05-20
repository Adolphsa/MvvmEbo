package com.lucas.ebo.data.bean.request;

/**
 * Created by lucas
 * Date: 2020/5/18 15:50
 */
public class RegisterRequestBean {

    private String app_token;

    private String device_id;

    private String app_kind;

    private String registry_region;

    private String language;

    private String password_1;

    private String password_2;

    private String phone_message;

    private String phone_num;

    private String phone_area;

    private String email;


    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getApp_kind() {
        return app_kind;
    }

    public void setApp_kind(String app_kind) {
        this.app_kind = app_kind;
    }

    public String getRegistry_region() {
        return registry_region;
    }

    public void setRegistry_region(String registry_region) {
        this.registry_region = registry_region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPassword_1() {
        return password_1;
    }

    public void setPassword_1(String password_1) {
        this.password_1 = password_1;
    }

    public String getPassword_2() {
        return password_2;
    }

    public void setPassword_2(String password_2) {
        this.password_2 = password_2;
    }

    public String getPhone_message() {
        return phone_message;
    }

    public void setPhone_message(String phone_message) {
        this.phone_message = phone_message;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
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
