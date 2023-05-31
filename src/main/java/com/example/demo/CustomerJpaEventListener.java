package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CustomerJpaEventListener {

	private static final Logger log = LoggerFactory.getLogger(CustomerJpaEventListener.class);
    
  @PrePersist
  @PreUpdate
  private void beforeAnyUpdate(Customer customer) {
    if (customer.getId() == null) {
      log.info("About to add a customer");
    } else {
      log.info("About to update customer: {}", customer.getId());
    }
    String decodedSecret = customer.getSecret();
    String encodedSecret = Base64.getEncoder().encodeToString(decodedSecret.getBytes());
    customer.setSecret(encodedSecret);
    log.info("Secret encoded: {} -> {}", decodedSecret, encodedSecret);
  }
  
  @PostPersist
  @PostUpdate
  private void afterAnyUpdate(Customer customer) {
    log.info("Add/update complete for customer: {}", customer.getId());
    customer.setSecret(decodeSecret(customer.getSecret()));
  }
  
  @PostLoad
  private void afterLoad(Customer customer) {
    log.info("Customer loaded from database: {}", customer.getId());
    customer.setSecret(decodeSecret(customer.getSecret()));
  }

  private String decodeSecret(String encodedSecret) {
    byte[] decodedBytes = Base64.getDecoder().decode(encodedSecret);
    String decodedSecret = new String(decodedBytes);    
    log.info("Secret decoded: {} -> {}", encodedSecret, decodedSecret);
    return decodedSecret;
  }
}
