package com.noser.aacaddressbook.ui.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.noser.aacaddressbook.data.AppDatabase;
import com.noser.aacaddressbook.data.entity.AddressEntity;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class AddressDetailViewModel extends AndroidViewModel {

    private final AppDatabase appDatabase;

    public AddressDetailViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public LiveData<AddressEntity> getAddress(int addressId) {
        return appDatabase.addressDao().loadAddress(addressId);
    }

    public void addAddress(AddressEntity address) {
        new InsertAsyncTask(appDatabase).execute(address);
    }

    public void updateAddress(AddressEntity address) {
        new UpdateAsyncTask(appDatabase).execute(address);
    }

    private static class InsertAsyncTask extends AsyncTask<AddressEntity, Void, Void> {
        private AppDatabase db;

        InsertAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(final AddressEntity... params) {
            db.addressDao().insert(params[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<AddressEntity, Void, Void> {
        private AppDatabase db;

        UpdateAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(final AddressEntity... params) {
            db.addressDao().update(params[0]);
            return null;
        }
    }
}
