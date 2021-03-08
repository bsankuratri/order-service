package com.personal.orderservice.controllers;

import com.personal.orderservice.models.Customer;
import com.personal.orderservice.models.Order;
import com.personal.orderservice.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("customers")
@Validated
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping()
  public ResponseEntity<Page<Customer>> getAllByPage(
    @Min(0)
    @RequestParam(defaultValue = "0") int page,
    @Min(1)
    @Max(100)
    @RequestParam(defaultValue = "25") int size,
    @Pattern(regexp = "name|email")
    @RequestParam(name = "sort.on", defaultValue = "name") String sortOn,
    // TODO create an enum validator
    @Pattern(regexp = "ASC|DESC")
    @RequestParam(name = "sort.dir", defaultValue = "ASC") String sortDirection
  ) {

    return new ResponseEntity<>(customerService.getAll(page, size, sortOn, sortDirection), HttpStatus.OK);
  }

  @GetMapping("list")
  public ResponseEntity<List<Customer>> getAll() {
    return new ResponseEntity<>(this.customerService.getAll(), HttpStatus.OK);
  }

  @GetMapping("{id}/orders")
  public ResponseEntity<List<Order>> getOrders(@PathVariable String id) {
    return new ResponseEntity<>(this.customerService.getOrders(id), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getById(@PathVariable String id) {
    return this.customerService.findById(id)
      .map(order -> new ResponseEntity(order, HttpStatus.OK))
      .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
  }

  @PostMapping()
  public ResponseEntity<Customer> save(@RequestBody Customer customer) {
    return  new ResponseEntity<>(this.customerService.create(customer), HttpStatus.OK);
  }

}
