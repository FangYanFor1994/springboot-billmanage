package com.fy.billmanagement.mapper;

import com.fy.billmanagement.entities.Bill;
import com.fy.billmanagement.entities.BillProvider;

import java.util.List;

public interface BillMapper {

    List<Bill> getBillProvider(BillProvider billProvider);

    BillProvider getBillProviderByBid(Integer bid);

    void addBill(Bill bill);

    void updateBill(Bill bill);

    void delateBill(Integer bid);
}
