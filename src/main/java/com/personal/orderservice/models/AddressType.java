package com.personal.orderservice.models;

public enum AddressType {
  HOME("home"),
  OFFICE("office");

  AddressType(String type) { this.type = type; }

  private String type;
}
