package com.personal.orderservice.services;

import com.personal.orderservice.models.Customer;
import com.personal.orderservice.models.Order;
import com.personal.orderservice.repositories.CustomerRepository;
import com.personal.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;

  private final OrderRepository orderRepository;

  public Customer create(Customer customer) {
    return this.repository.save(customer);
  }

  public Page<Customer> getAll(int page, int size, String sortOn, String sortDirection) {
    return this.repository.findAll(
      PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortOn))
    );
  }

  public Customer update(Customer customer, String id) {
    if (this.repository.existsById(id)) {
      return this.repository.save(customer);
    }
    throw new RuntimeException("Entity not exists");
  }

  public List<Customer> getAll() {
    return this.repository.findAll();
  }

  public List<Order> getOrders(String id) {
    return this.orderRepository.findByCustomer_Id(id);
  }

  public Optional<Customer> findById(String id) {
    return this.repository.findById(id);
  }
}
