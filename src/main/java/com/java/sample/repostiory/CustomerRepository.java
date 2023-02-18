package com.java.sample.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.sample.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> {

}
