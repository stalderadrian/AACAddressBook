package com.noser.aacaddressbook.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.noser.aacaddressbook.model.Address;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */
@Entity(tableName = "address")
public class AddressEntity implements Address {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstName;
    private String lastName;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressEntity(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
