package com.lucas.ebo.data.repository.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by lucas
 * Date: 2020/5/27 17:56
 */
@Dao
public interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertUserInfo(UserInfo userInfo);

    @Delete
    void deleteUserInfo(UserInfo userInfo);

    @Update
    void updateUserInfo(UserInfo userInfo);

    @Query("SELECT * FROM userInfo_table")
    List<UserInfo> getUserInfoList();

    @Query("SELECT * FROM userInfo_table WHERE user_id = :userId")
    UserInfo getUserInfoById(int userId);

}
