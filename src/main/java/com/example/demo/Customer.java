package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@EntityListeners(CustomerJpaEventListener.class)
public class Customer implements EncodedMarker {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private String secret;
  @Transient
  private boolean encoded;

  protected Customer() {}

  public Customer(String firstName, String lastName, String secret) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.secret = secret;
  }

  @Override
  public String toString() {
    return String.format("Customer[id=%d, firstName='%s', lastName='%s', secret='%s', encoded='%s']", id, firstName, lastName, secret, isEncoded());
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public void setEncoded(boolean encoded) {
    this.encoded = encoded;
  }

  @Override
  public boolean isEncoded() {
    return encoded;
  }
}
