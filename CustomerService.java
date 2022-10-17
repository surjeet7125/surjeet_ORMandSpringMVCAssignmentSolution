package com.greatLearning.customer.service;

import java.util.List;

import com.greatLearning.customer.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll();

	public Customer findById(int theId);

	public void save(Customer thecustomer);

	public void deleteById(int theId);
}
