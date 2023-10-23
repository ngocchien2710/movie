package com.vti.movie.service;

import com.vti.movie.dtos.BookingRequestDTO;
import com.vti.movie.entity.Bill;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IBillService {
    @Transactional
    void createNewBill(BookingRequestDTO bookingRequestDTO) throws RuntimeException;

    Bill getBillById(Integer id);

    List<Bill> getAllBills();

    void updateBill(Integer id, Bill updatedBill);

    void deleteBill(Integer id);
}
