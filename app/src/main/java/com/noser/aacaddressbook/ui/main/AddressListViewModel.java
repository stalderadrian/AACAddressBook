package com.noser.aacaddressbook.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.noser.aacaddressbook.data.AddressRepository;
import com.noser.aacaddressbook.data.db.AppDatabase;
import com.noser.aacaddressbook.data.db.entity.AddressEntity;

import java.util.List;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class AddressListViewModel extends AndroidViewModel {

    private final AddressRepository addressRepository;

    public AddressListViewModel(Application application) {
        super(application);

        addressRepository = new AddressRepository(AppDatabase.getDatabase(this.getApplication()));
    }

    public LiveData<List<AddressEntity>> getAllAddresses() {
        return addressRepository.getAllAddresses();
    }

    public void deleteAddress(AddressEntity address) {
        addressRepository.deleteAddress(address);
    }
}
