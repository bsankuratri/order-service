package com.personal.orderservice.controllers;

import com.personal.orderservice.models.Order;
import com.personal.orderservice.services.OrderService;
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
@RequestMapping("orders")
@Validated
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping()
  public ResponseEntity<Page<Order>> getAllByPage(
    @Min(0)
    @RequestParam(defaultValue = "0") int page,
    @Min(1)
    @Max(100)
    @RequestParam(defaultValue = "25") int size,
    @Pattern(regexp = "products.name")
    @RequestParam(name = "sort.on", defaultValue = "products.name") String sortOn,
    // TODO create an enum validator
    @Pattern(regexp = "ASC|DESC")
    @RequestParam(name = "sort.dir", defaultValue = "ASC") String sortDirection
  ) {

    return new ResponseEntity<>(orderService.getAll(page, size, sortOn, sortDirection), HttpStatus.OK);
  }

  @GetMapping("list")
  public ResponseEntity<List<Order>> getOrders() {
    return new ResponseEntity<>(this.orderService.getAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getById(@PathVariable String id) {
    return this.orderService.findById(id)
      .map(order -> new ResponseEntity(order, HttpStatus.OK))
      .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
  }

  @PostMapping()
  public ResponseEntity<Order> save(@RequestBody Order order) {
    return  new ResponseEntity<>(this.orderService.create(order), HttpStatus.OK);
  }

}
