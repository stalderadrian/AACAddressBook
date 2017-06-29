package com.noser.aacaddressbook.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.noser.aacaddressbook.db.AppDatabase;
import com.noser.aacaddressbook.db.entity.AddressEntity;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class AddAddressViewModel extends AndroidViewModel {

    private final AppDatabase mAppDatabase;

    public AddAddressViewModel(Application application) {
        super(application);

        mAppDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void addAddress(AddressEntity address) {
        new addAsyncTask(mAppDatabase).execute(address);
    }

    private static class addAsyncTask extends AsyncTask<AddressEntity, Void, Void> {

        private AppDatabase mDb;

        addAsyncTask(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final AddressEntity... params) {
            mDb.addressDao().insert(params[0]);
            return null;
        }

    }
}
