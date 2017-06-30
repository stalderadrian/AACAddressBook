package com.noser.aacaddressbook.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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

public class AddressListFragment extends LifecycleFragment implements View.OnClickListener, View.OnLongClickListener {

    public static final String TAG = "AddressListViewModel";

    private AddressListAdapter addressListAdapter;
    private AddressListViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.address_list, container, false);

        RecyclerView addressListView = (RecyclerView) view.findViewById(R.id.addressList);
        addressListAdapter = new AddressListAdapter(new ArrayList<AddressEntity>(), this, this);
        addressListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        addressListView.setAdapter(addressListAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(AddressListViewModel.class);
        mViewModel.getAddresses().observe(this, new Observer<List<AddressEntity>>() {
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

    @Override
    public boolean onLongClick(final View v) {
        final AddressEntity address = (AddressEntity) v.getTag();

        new AlertDialog.Builder(getContext())
                .setMessage("Delete " + address.getFirstName() + " " + address.getLastName() + "?")
                .setCancelable(true)
                .setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mViewModel.deleteAddress(address);
                            }
                        })
                .setNegativeButton("No", null)
                .create()
                .show();
        return true;
    }
}
