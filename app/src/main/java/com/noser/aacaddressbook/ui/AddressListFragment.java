package com.noser.aacaddressbook.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noser.aacaddressbook.R;
import com.noser.aacaddressbook.db.entity.AddressEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian Stalder on 30.06.2017.
 */

public class AddressListFragment extends LifecycleFragment implements View.OnClickListener {

    public static final String TAG = "AddressListViewModel";

    private AddressListAdapter addressListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.address_list, container, false);

        RecyclerView addressListView = (RecyclerView) view.findViewById(R.id.addressList);
        addressListAdapter = new AddressListAdapter(new ArrayList<AddressEntity>(), this);
        addressListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        addressListView.setAdapter(addressListAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AddressListViewModel viewModel = ViewModelProviders.of(this).get(AddressListViewModel.class);

        viewModel.getAddresses().observe(this, new Observer<List<AddressEntity>>() {
            @Override
            public void onChanged(@Nullable List<AddressEntity> addresses) {
                if (addresses != null) {
                    addressListAdapter.addItems(addresses);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        AddressEntity address = (AddressEntity) v.getTag();
        Intent intent = new Intent(getActivity(), EditAddressActivity.class);
        intent.putExtra("id", address.getId());
        startActivity(intent);
    }
}
