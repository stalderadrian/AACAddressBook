package com.noser.aacaddressbook.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noser.aacaddressbook.R;
import com.noser.aacaddressbook.db.entity.AddressEntity;
import com.noser.aacaddressbook.model.Address;

import java.util.List;

/**
 * Created by Adrian Stalder on 29.06.2017.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressRowViewHolder> {

    private List<AddressEntity> mAddresses;

    public AddressListAdapter(List<AddressEntity> addresses) {
        this.mAddresses = addresses;
    }

    @Override
    public AddressRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressRowViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(AddressRowViewHolder holder, int position) {
        Address address = mAddresses.get(position);
        holder.firstNameTextView.setText(address.getFirstName());
        holder.lastNameTextView.setText(address.getLastName());
        holder.itemView.setTag(address);
    }

    @Override
    public int getItemCount() {
        return mAddresses.size();
    }

    public void addItems(List<AddressEntity> addresses) {
        this.mAddresses = addresses;
        notifyDataSetChanged();
    }

    static class AddressRowViewHolder extends RecyclerView.ViewHolder {
        private TextView firstNameTextView;
        private TextView lastNameTextView;

        AddressRowViewHolder(View view) {
            super(view);
            firstNameTextView = (TextView) view.findViewById(R.id.firstName);
            lastNameTextView = (TextView) view.findViewById(R.id.lastName);
        }
    }
}
