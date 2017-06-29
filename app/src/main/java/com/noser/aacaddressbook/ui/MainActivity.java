package com.noser.aacaddressbook.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.noser.aacaddressbook.R;
import com.noser.aacaddressbook.db.AppDatabase;
import com.noser.aacaddressbook.db.entity.AddressEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends LifecycleActivity {

    private AddressListViewModel mViewModel;
    private AddressListAdapter mAddressListAdapter;
    private RecyclerView mAddressListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddAddressActivity.class));
            }
        });

        mAddressListView = (RecyclerView) findViewById(R.id.addressList);
        mAddressListAdapter = new AddressListAdapter(new ArrayList<AddressEntity>());
        mAddressListView.setLayoutManager(new LinearLayoutManager(this));

        mAddressListView.setAdapter(mAddressListAdapter);

        mViewModel = ViewModelProviders.of(this).get(AddressListViewModel.class);

        mViewModel.getAddresses().observe(MainActivity.this, new Observer<List<AddressEntity>>() {
            @Override
            public void onChanged(@Nullable List<AddressEntity> addresses) {
                mAddressListAdapter.addItems(addresses);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }
}
