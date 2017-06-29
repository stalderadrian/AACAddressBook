package com.noser.aacaddressbook.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.noser.aacaddressbook.db.entity.AddressEntity;

import java.util.List;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

@Dao
public interface AddressDao {
    @Query("SELECT * FROM address")
    LiveData<List<AddressEntity>> loadAllAddresses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AddressEntity address);
}