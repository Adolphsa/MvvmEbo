package com.lucas.ebo.data.repository.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by lucas
 * Date: 2020/5/27 17:40
 */
@Entity(tableName = "userInfo_table")
public class UserInfo {

    /**
     * user_id : 30
     * username : EEN6R375
     * phone_num : null
     * email : 64396908@qq.com
     * nickname : null
     * avatar : null
     * phone_area : null
     * robots : [{"robot_id":1,"machine_code":"001","machine_version":"v1"}]
     * is_valid : false
     * gender : 0
     * changed_pwd : true
     * third_party : 0
     * accept_email : true
     */

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "phone_num")
    private String phoneNum;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "nick_name")
    private String nickName;

    @ColumnInfo(name = "avatar")
    private String avatar;

    @ColumnInfo(name = "phone_area")
    private String phoneArea;

    @ColumnInfo(name = "is_valid")
    private boolean isValid;

    @ColumnInfo(name = "gender")
    private int gender;

    @ColumnInfo(name = "changed_pwd")
    private boolean changedPwd;

    @ColumnInfo(name = "third_party")
    private int thirdParty;

    @ColumnInfo(name = "accept_email")
    private boolean acceptEmail;

    public UserInfo()
    {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneArea() {
        return phoneArea;
    }

    public void setPhoneArea(String phoneArea) {
        this.phoneArea = phoneArea;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isChangedPwd() {
        return changedPwd;
    }

    public void setChangedPwd(boolean changedPwd) {
        this.changedPwd = changedPwd;
    }

    public int getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(int thirdParty) {
        this.thirdParty = thirdParty;
    }

    public boolean isAcceptEmail() {
        return acceptEmail;
    }

    public void setAcceptEmail(boolean acceptEmail) {
        this.acceptEmail = acceptEmail;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phoneArea='" + phoneArea + '\'' +
                ", isValid=" + isValid +
                ", gender=" + gender +
                ", changedPwd=" + changedPwd +
                ", thirdParty=" + thirdParty +
                ", acceptEmail=" + acceptEmail +
                '}';
    }
}
