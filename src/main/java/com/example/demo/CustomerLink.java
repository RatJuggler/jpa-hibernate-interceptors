package com.example.demo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@EntityListeners(CustomerLinkJpaEventListener.class)
public class CustomerLink {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  @Column(unique=true)
  private String account;
  // fetch type EAGER only seems affect loading when findById used.
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;

  protected CustomerLink() {}

  public CustomerLink(String account) {
    this.account = account;
  }

  @Override
  public String toString() {
    return String.format("CustomerLink[id=%d, account='%s', customer='%s']", id, account, customer);
  }

  public Long getId() {
    return id;
  }

  public String getAccount() {
    return account;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return customer;
  }
}
