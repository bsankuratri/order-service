package com.personal.orderservice.services;

import com.personal.orderservice.models.Customer;
import com.personal.orderservice.models.Order;
import com.personal.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository repository;

  public Order create(Order order) {
    return this.repository.save(order);
  }

  public Order update(Order order, String id) {
    if (this.repository.existsById(id)) {
      return this.repository.save(order);
    }
    throw new RuntimeException("Entity not exists");
  }

  public Page<Order> getAll(int page, int size, String sortOn, String sortDirection) {
    return this.repository.findAll(
      PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortOn))
    );
  }

  public List<Order> getAll() {
    return this.repository.findAll();
  }

  public Optional<Order> findById(String id) {
    return this.repository.findById(id);
  }
}
