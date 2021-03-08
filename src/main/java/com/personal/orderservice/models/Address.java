package com.personal.orderservice.models;

import com.personal.orderservice.models.validators.OnUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

  @Id
  @NotNull(groups = {OnUpdate.class})
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @Enumerated(EnumType.STRING)
  private AddressType type;

  @NotBlank
  private String line;

  private String street;

  @NotBlank
  private String city;

  private String state;

  @Pattern(regexp = "^[1-9][0-9]{5}$")
  @Column(name = "pin_code")
  @NotBlank
  private String pinCode;

  private String country;

  public Address(String city, String line, String pinCode, AddressType type) {
    this.city = city;
    this.line = line;
    this.pinCode = pinCode;
    this.type = type;
  }

}
