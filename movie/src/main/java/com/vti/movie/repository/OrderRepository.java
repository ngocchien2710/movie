package com.vti.movie.repository;

import com.vti.movie.modal.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT d FROM Order d WHERE d.buyerId = :buyerId")
    List<Order> findByBuyerId(@Param("buyerId") int id);
}
