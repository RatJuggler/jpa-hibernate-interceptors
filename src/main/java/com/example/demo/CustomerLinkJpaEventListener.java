package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CustomerLinkJpaEventListener {

	private static final Logger log = LoggerFactory.getLogger(CustomerLinkJpaEventListener.class);
    
  @PrePersist
  @PreUpdate
  private void beforeAnyUpdate(CustomerLink customerLink) {
    if (customerLink.getId() == null) {
      log.info("About to add a customer link");
    } else {
      log.info("About to update customer link: {}", customerLink.getId());
    }
  }
  
  @PostPersist
  @PostUpdate
  private void afterAnyUpdate(CustomerLink customerLink) {
    log.info("Add/update complete for customer link: {}", customerLink.getId());
  }
  
  @PostLoad
  private void afterLoad(CustomerLink customerLink) {
    log.info("Customer link loaded from database: {}", customerLink.getId());
  }
}
