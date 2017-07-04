package com.noser.aacaddressbook.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.noser.aacaddressbook.data.dao.AddressDao;
import com.noser.aacaddressbook.data.entity.AddressEntity;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

@Database(entities = {AddressEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "address-db";

    private static AppDatabase sAppDatabase;

    public static AppDatabase getDatabase(Context context) {
        if (sAppDatabase == null) {
            sAppDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
            //sAppDatabase = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).build();
        }
        return sAppDatabase;
    }

    public static void destroyInstance() {
        sAppDatabase = null;
    }

    public abstract AddressDao addressDao();
}
