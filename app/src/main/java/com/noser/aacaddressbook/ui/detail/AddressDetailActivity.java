package com.noser.aacaddressbook.ui.detail;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.noser.aacaddressbook.R;
import com.noser.aacaddressbook.data.db.entity.AddressEntity;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class AddressDetailActivity extends LifecycleActivity {

    private static final String KEY_ADDRESS_ID = "key_address_id";

    private AddressDetailViewModel addressDetailViewModel;

    private EditText firstNameEditText;
    private EditText lastNameEditText;

    public static Intent createIntent(Context context, int addressId) {
        Intent intent = new Intent(context, AddressDetailActivity.class);
        intent.putExtra(KEY_ADDRESS_ID, addressId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_detail);
        android.widget.Toolbar toolbar = (android.widget.Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        firstNameEditText = (EditText) findViewById(R.id.firstName);
        lastNameEditText = (EditText) findViewById(R.id.lastName);

        addressDetailViewModel = ViewModelProviders.of(this).get(AddressDetailViewModel.class);

        Intent intent = getIntent();
        final int id = intent.getIntExtra(KEY_ADDRESS_ID, 0);
        if (id != 0) {
            addressDetailViewModel.getAddress(id).observe(this, new Observer<AddressEntity>() {
                @Override
                public void onChanged(@Nullable AddressEntity address) {
                    firstNameEditText.setText(address != null ? address.getFirstName() : "");
                    lastNameEditText.setText(address != null ? address.getLastName() : "");
                }
            });
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstNameEditText.getText() == null || firstNameEditText.getText().toString().equals("") ||
                        lastNameEditText.getText() == null || lastNameEditText.getText().toString().equals(""))
                    Toast.makeText(AddressDetailActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                else {
                    if (id != 0) {
                        addressDetailViewModel.updateAddress(
                                new AddressEntity(id,
                                        firstNameEditText.getText().toString(),
                                        lastNameEditText.getText().toString()));
                    } else {
                        addressDetailViewModel.addAddress(
                                new AddressEntity(0,
                                        firstNameEditText.getText().toString(),
                                        lastNameEditText.getText().toString()));
                    }
                    finish();
                }
            }
        });
    }
}
