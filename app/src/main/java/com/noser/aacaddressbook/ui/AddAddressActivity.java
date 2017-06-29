package com.noser.aacaddressbook.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.noser.aacaddressbook.R;
import com.noser.aacaddressbook.db.entity.AddressEntity;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class AddAddressActivity extends AppCompatActivity {

    private AddAddressViewModel mAddAddressViewModel;

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirstNameEditText = (EditText) findViewById(R.id.firstName);
        mLastNameEditText = (EditText) findViewById(R.id.lastName);

        mAddAddressViewModel = ViewModelProviders.of(this).get(AddAddressViewModel.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFirstNameEditText.getText() == null || mFirstNameEditText.getText().toString().equals("") ||
                        mLastNameEditText.getText() == null || mLastNameEditText.getText().toString().equals(""))
                    Toast.makeText(AddAddressActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                else {
                    mAddAddressViewModel.addAddress(
                            new AddressEntity(0,
                                    mFirstNameEditText.getText().toString(),
                                    mLastNameEditText.getText().toString()));
                    finish();
                }
            }
        });
    }
}
