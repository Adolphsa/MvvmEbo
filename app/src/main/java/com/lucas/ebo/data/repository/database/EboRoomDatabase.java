package com.lucas.ebo.data.repository.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lucas.architecture.utils.Utils;

/**
 * Created by lucas
 * Date: 2020/5/27 17:08
 */
@Database(entities = {UserInfo.class}, version = 1)
public abstract class EboRoomDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "enabot.db";

    private static EboRoomDatabase databaseInstance;

    public abstract UserInfoDao userInfoDao();

    public static synchronized EboRoomDatabase getInstance()
    {
        if(databaseInstance == null)
        {
            databaseInstance = Room
                    .databaseBuilder(Utils.getApp(), EboRoomDatabase.class, DATABASE_NAME)
                    .build();
        }
        return databaseInstance;
    }

}
