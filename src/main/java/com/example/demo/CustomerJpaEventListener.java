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
    log.info("PrePersist/PreUpdate: For Customer: {}", customer);
    String decodedSecret = customer.getSecret();
    String encodedSecret = Base64.getEncoder().encodeToString(decodedSecret.getBytes());
    log.info("PrePersist/PreUpdate: Secret encoded: {} -> {}", decodedSecret, encodedSecret);
    customer.setSecret(encodedSecret);
    customer.setEncoded(true);
    log.info("PrePersist/PreUpdate: {}", customer);
  }

  @PostPersist
  @PostUpdate
  @PostLoad
  private void afterAnyUpdateOrLoad(Customer customer) {
    log.info("PostPersist/PostUpdate/PostLoad: For Customer: {}", customer);
    String encodedSecret = customer.getSecret();
    byte[] decodedBytes = Base64.getDecoder().decode(encodedSecret);
    String decodedSecret = new String(decodedBytes);
    log.info("PostPersist/PostUpdate/PostLoad: Secret decoded: {} -> {}", encodedSecret, decodedSecret);
    customer.setSecret(decodedSecret);
    customer.setEncoded(false);
    log.info("PostPersist/PostUpdate/PostLoad: {}", customer);
  }
}
