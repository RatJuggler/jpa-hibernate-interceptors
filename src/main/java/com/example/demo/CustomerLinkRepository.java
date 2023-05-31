package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerLinkRepository extends JpaRepository<CustomerLink, Long> {

  CustomerLink findByAccount(String account);

  CustomerLink findById(long id);

  CustomerLink findByCustomer(Customer customer);

  CustomerLink findByCustomerId(long customerId);
}
