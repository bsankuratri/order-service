package com.personal.orderservice.models;

import com.personal.orderservice.models.validators.OnUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@Entity
@Table(name = "customer")
@NoArgsConstructor
public class Customer {

  @Id
  @NotNull(groups = {OnUpdate.class})
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @NotBlank
  private String name;

  @NotBlank
  private String email;

  private String phone;

  @OneToMany(cascade=CascadeType.ALL)
  @NotNull
  @Size(min = 1)
  @JoinTable(name = "customer_addresses",
    joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "address_id", referencedColumnName = "id")}
  )
  private List<Address> addresses = new ArrayList<>();

  public Customer(String name, String email, String phone) {
    this.name = name;
    this.email = email;
    this.phone = phone;
  }

  public Optional<Address> getAddress(AddressType addressType) {
    return this.getAddresses().stream()
      .filter(address -> address.getType() == addressType)
      .findFirst();
  }

}
