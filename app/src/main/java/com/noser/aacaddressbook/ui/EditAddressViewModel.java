package com.noser.aacaddressbook.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.noser.aacaddressbook.db.AppDatabase;
import com.noser.aacaddressbook.db.entity.AddressEntity;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class EditAddressViewModel extends AndroidViewModel {

    private final AppDatabase appDatabase;

    public EditAddressViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public LiveData<AddressEntity> getAddress(int addressId) {
        return appDatabase.addressDao().loadAddress(addressId);
    }

    public void addAddress(AddressEntity address) {
        new insertAsyncTask(appDatabase).execute(address);
    }

    public void updateAddress(AddressEntity address) {
        new updateAsyncTask(appDatabase).execute(address);
    }

    private static class insertAsyncTask extends AsyncTask<AddressEntity, Void, Void> {
        private AppDatabase db;

        insertAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(final AddressEntity... params) {
            db.addressDao().insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<AddressEntity, Void, Void> {
        private AppDatabase db;

        updateAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(final AddressEntity... params) {
            db.addressDao().update(params[0]);
            return null;
        }
    }
}
