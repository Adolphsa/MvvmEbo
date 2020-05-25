package com.lucas.ebo.data.bean.response;

/**
 * Created by lucas
 * Date: 2020/5/25 15:23
 */
public class DeviceInfoResultBean {

    /**
     * robot_name : Catpal-05F
     * machine_code : 3236393831385109004E005F
     * machine_version : ebo catpal s
     * robot_id : 75
     * is_superowner : true
     * picture : https://resource.ebo.enabotserver.com/nodeOne/images/2019/12/24/dM3Oqlft09.png.200x200_q85.png
     * tkid : 6967038e84b34135a8e5d78d6360b44b
     * model_marketing_name : ebo catpal
     */

    private String robot_name;
    private String machine_code;
    private String machine_version;
    private int robot_id;
    private boolean is_superowner;
    private String picture;
    private String tkid;
    private String model_marketing_name;

    public String getRobot_name() {
        return robot_name;
    }

    public void setRobot_name(String robot_name) {
        this.robot_name = robot_name;
    }

    public String getMachine_code() {
        return machine_code;
    }

    public void setMachine_code(String machine_code) {
        this.machine_code = machine_code;
    }

    public String getMachine_version() {
        return machine_version;
    }

    public void setMachine_version(String machine_version) {
        this.machine_version = machine_version;
    }

    public int getRobot_id() {
        return robot_id;
    }

    public void setRobot_id(int robot_id) {
        this.robot_id = robot_id;
    }

    public boolean isIs_superowner() {
        return is_superowner;
    }

    public void setIs_superowner(boolean is_superowner) {
        this.is_superowner = is_superowner;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTkid() {
        return tkid;
    }

    public void setTkid(String tkid) {
        this.tkid = tkid;
    }

    public String getModel_marketing_name() {
        return model_marketing_name;
    }

    public void setModel_marketing_name(String model_marketing_name) {
        this.model_marketing_name = model_marketing_name;
    }

    @Override
    public String toString() {
        return "DeviceInfoBean{" +
                "robot_name='" + robot_name + '\'' +
                ", machine_code='" + machine_code + '\'' +
                ", machine_version='" + machine_version + '\'' +
                ", robot_id=" + robot_id +
                ", is_superowner=" + is_superowner +
                ", picture='" + picture + '\'' +
                ", tkid='" + tkid + '\'' +
                ", model_marketing_name='" + model_marketing_name + '\'' +
                '}';
    }

}
