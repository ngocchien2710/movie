package com.vti.movie.repository;

import com.vti.movie.modal.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {
    Bid findById(int id);
    @Query(value = "SELECT * FROM Bid WHERE product_id = :productId",nativeQuery = true)
    List<Bid> findByProductId(@Param("productId") int id);
}
