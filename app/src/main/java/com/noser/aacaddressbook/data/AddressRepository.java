package com.noser.aacaddressbook.data;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.noser.aacaddressbook.data.db.AppDatabase;
import com.noser.aacaddressbook.data.db.entity.AddressEntity;

import java.util.List;

/**
 * Created by Adrian Stalder on 04.07.2017.
 */

public class AddressRepository {

    private final AppDatabase appDatabase;

    public AddressRepository(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public LiveData<List<AddressEntity>> getAllAddresses() {
        return appDatabase.addressDao().loadAllAddresses();
    }

    public LiveData<AddressEntity> getAddress(int addressId) {
        return this.appDatabase.addressDao().loadAddress(addressId);
    }

    public void addAddress(AddressEntity address) {
        new AddressRepository.InsertAsyncTask(appDatabase).execute(address);
    }

    public void updateAddress(AddressEntity address) {
        new UpdateAsyncTask(appDatabase).execute(address);
    }

    public void deleteAddress(AddressEntity address) {
        new AddressRepository.DeleteAsyncTask(appDatabase).execute(address);
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

    private static class DeleteAsyncTask extends AsyncTask<AddressEntity, Void, Void> {
        private AppDatabase db;

        DeleteAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(final AddressEntity... params) {
            db.addressDao().delete(params[0]);
            return null;
        }
    }
}
