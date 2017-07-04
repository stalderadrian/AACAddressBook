package com.noser.aacaddressbook.ui.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.noser.aacaddressbook.data.AddressRepository;
import com.noser.aacaddressbook.data.db.AppDatabase;
import com.noser.aacaddressbook.data.db.entity.AddressEntity;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class AddressDetailViewModel extends AndroidViewModel {

    private final AddressRepository addressRepository;

    public AddressDetailViewModel(Application application) {
        super(application);

        addressRepository = new AddressRepository(AppDatabase.getDatabase(this.getApplication()));
    }

    public LiveData<AddressEntity> getAddress(int addressId) {
        return addressRepository.getAddress(addressId);
    }

    public void addAddress(AddressEntity address) {
        addressRepository.addAddress(address);
    }

    public void updateAddress(AddressEntity address) {
        addressRepository.updateAddress(address);
    }
}
