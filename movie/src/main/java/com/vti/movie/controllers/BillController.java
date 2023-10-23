package com.vti.movie.controllers;

import com.vti.movie.dtos.BookingRequestDTO;
import com.vti.movie.entity.Bill;
import com.vti.movie.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bills")
public class BillController {
    @Autowired
    private IBillService billService;

    //Tạo bill mới
    @PostMapping("/create-new-bill")
    public ResponseEntity<String> createNewBill(@RequestBody BookingRequestDTO bookingRequestDTO) {
        try {
            billService.createNewBill(bookingRequestDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã mua vé xem phim thành công !", HttpStatus.OK);
    }

    // lấy thông tin một hóa đơn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Integer id) {
        Bill bill = billService.getBillById(id);
        if (bill != null) {
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // cập nhật thông tin một hóa đơn
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBill(@PathVariable Integer id, @RequestBody Bill updatedBill) {
        try {
            billService.updateBill(id, updatedBill);
            return new ResponseEntity<>("Cập nhật hóa đơn thành công !", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    // xóa một hóa đơn theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Integer id) {
        try {
            billService.deleteBill(id);
            return new ResponseEntity<>("Xóa hóa đơn thành công !", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    // lấy danh sách tất cả các hóa đơn
    @GetMapping("/all")
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
}
