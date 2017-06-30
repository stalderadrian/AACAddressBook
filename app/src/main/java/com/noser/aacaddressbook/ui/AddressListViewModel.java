package com.noser.aacaddressbook.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.noser.aacaddressbook.db.AppDatabase;
import com.noser.aacaddressbook.db.entity.AddressEntity;

import java.util.List;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class AddressListViewModel extends AndroidViewModel {

    private AppDatabase mAppDatabase;

    public AddressListViewModel(Application application) {
        super(application);

        mAppDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public LiveData<List<AddressEntity>> getAddresses() {
        return mAppDatabase.addressDao().loadAllAddresses();
    }
}
