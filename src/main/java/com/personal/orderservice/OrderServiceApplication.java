package com.personal.orderservice;

import com.personal.orderservice.models.Address;
import com.personal.orderservice.models.AddressType;
import com.personal.orderservice.models.Customer;
import com.personal.orderservice.models.Order;
import com.personal.orderservice.models.Product;
import com.personal.orderservice.services.CustomerService;
import com.personal.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class OrderServiceApplication {

	private final OrderService orderService;

	private final CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@PostConstruct()
	private void loadData() {
		Address homeAddress = new Address("Hyderabad", "KPHB", "500072", AddressType.HOME);
		Address officeAddress = new Address("Hyderabad", "KPHB", "500072", AddressType.OFFICE);
		Customer customer = new Customer("Test Customer1", "test1.customer@email.com", "0987654321");
		customer.getAddresses().add(homeAddress);
		customer.getAddresses().add(officeAddress);
		Customer savedCustomer = customerService.create(customer);
		Product product1 = new Product("First Product", "Test Sellar");
		Product product2 = new Product("Second Product", "Test Sellar");
		Order order1 = new Order(savedCustomer, savedCustomer.getAddress(AddressType.HOME).get());
		order1.getProducts().add(product1);
		Order order2 = new Order(savedCustomer, savedCustomer.getAddress(AddressType.OFFICE).get());
		order2.getProducts().add(product2);
		orderService.create(order1);
		orderService.create(order2);
	}


}
