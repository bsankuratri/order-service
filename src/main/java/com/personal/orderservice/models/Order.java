package com.personal.orderservice.models;

import com.personal.orderservice.models.validators.OnUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "orderZ")
@NoArgsConstructor
public class Order {

  @Id
  @NotNull(groups = {OnUpdate.class})
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @NotNull
  @OneToOne()
  @JoinColumn(name = "customer_id", nullable=false)
  private Customer customer;

  @NotNull
  @OneToOne()
  @JoinColumn(name = "address_id", nullable=false)
  private Address address;

  @NotNull
  @Size(min = 1)
  @OneToMany(cascade=CascadeType.ALL)
  @JoinTable(name = "order_products",
    joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
  )
  private List<Product> products = new ArrayList<>();

  public Order(Customer customer, Address address) {
    this.customer = customer;
    this.address = address;
  }
}
