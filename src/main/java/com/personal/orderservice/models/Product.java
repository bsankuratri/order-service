package com.personal.orderservice.models;

import com.personal.orderservice.models.validators.OnUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {

  @Id
  @NotNull(groups = {OnUpdate.class})
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @NotBlank
  private String name;

  @Column(name = "sellar_name")
  @NotBlank
  private String sellarName;

  public Product(String name, String sellarName) {
    this.name = name;
    this.sellarName = sellarName;
  }
}
