package com.lucas.ebo.data.bean.respone;

import java.util.List;

/**
 * Created by lucas
 * Date: 2020/4/6 19:11
 */
public class RegisterResultBean {


    /**
     * data : {"user_id":30,"username":"EEN6R375","phone_num":null,"email":"64396908@qq.com","nickname":null,"avatar":null,"phone_area":null,"robots":[{"robot_id":1,"machine_code":"001","machine_version":"v1"}],"is_valid":false,"gender":0,"changed_pwd":true,"third_party":0,"accept_email":true}
     * code : 193100
     */

    private DataBean data;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
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

        private int user_id;
        private String username;
        private Object phone_num;
        private String email;
        private Object nickname;
        private Object avatar;
        private Object phone_area;
        private boolean is_valid;
        private int gender;
        private boolean changed_pwd;
        private int third_party;
        private boolean accept_email;
        private List<RobotsBean> robots;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getPhone_num() {
            return phone_num;
        }

        public void setPhone_num(Object phone_num) {
            this.phone_num = phone_num;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public Object getPhone_area() {
            return phone_area;
        }

        public void setPhone_area(Object phone_area) {
            this.phone_area = phone_area;
        }

        public boolean isIs_valid() {
            return is_valid;
        }

        public void setIs_valid(boolean is_valid) {
            this.is_valid = is_valid;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public boolean isChanged_pwd() {
            return changed_pwd;
        }

        public void setChanged_pwd(boolean changed_pwd) {
            this.changed_pwd = changed_pwd;
        }

        public int getThird_party() {
            return third_party;
        }

        public void setThird_party(int third_party) {
            this.third_party = third_party;
        }

        public boolean isAccept_email() {
            return accept_email;
        }

        public void setAccept_email(boolean accept_email) {
            this.accept_email = accept_email;
        }

        public List<RobotsBean> getRobots() {
            return robots;
        }

        public void setRobots(List<RobotsBean> robots) {
            this.robots = robots;
        }

        public static class RobotsBean {
            /**
             * robot_id : 1
             * machine_code : 001
             * machine_version : v1
             */

            private int robot_id;
            private String machine_code;
            private String machine_version;

            public int getRobot_id() {
                return robot_id;
            }

            public void setRobot_id(int robot_id) {
                this.robot_id = robot_id;
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
        }
    }
}
