package com.personal.orderservice.repositories;

import com.personal.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

  public List<Order> findByCustomer_Id(String id);
}
