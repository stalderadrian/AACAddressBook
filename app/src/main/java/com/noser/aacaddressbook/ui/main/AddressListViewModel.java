package com.noser.aacaddressbook.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.noser.aacaddressbook.data.AppDatabase;
import com.noser.aacaddressbook.data.entity.AddressEntity;

import java.util.List;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class AddressListViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddressListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public LiveData<List<AddressEntity>> getAddresses() {
        return appDatabase.addressDao().loadAllAddresses();
    }

    public void deleteAddress(AddressEntity address) {
        new DeleteAsyncTask(appDatabase).execute(address);
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
