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
    log.info("PrePersist/PreUpdate: For CustomerLink: {}", customerLink);
  }

  @PostPersist
  @PostUpdate
  @PostLoad
  private void afterAnyUpdate(CustomerLink customerLink) {
    log.info("PostPersist/PostUpdate/PostLoad: For CustomerLink: {}", customerLink);
  }
}
