package com.noser.aacaddressbook.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.noser.aacaddressbook.R;
import com.noser.aacaddressbook.db.entity.AddressEntity;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class EditAddressActivity extends LifecycleActivity {

    private EditAddressViewModel editAddressViewModel;

    private EditText firstNameEditText;
    private EditText lastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        android.widget.Toolbar toolbar = (android.widget.Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        firstNameEditText = (EditText) findViewById(R.id.firstName);
        lastNameEditText = (EditText) findViewById(R.id.lastName);

        editAddressViewModel = ViewModelProviders.of(this).get(EditAddressViewModel.class);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        if (id != 0) {
            editAddressViewModel.getAddress(id).observe(this, new Observer<AddressEntity>() {
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
                    Toast.makeText(EditAddressActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                else {
                    if (id != 0) {
                        editAddressViewModel.updateAddress(
                                new AddressEntity(id,
                                        firstNameEditText.getText().toString(),
                                        lastNameEditText.getText().toString()));
                    } else {
                        editAddressViewModel.addAddress(
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
