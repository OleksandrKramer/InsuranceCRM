package com.insurance.crm.service;


import com.insurance.crm.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();
    Customer create(Customer customer);
    Customer update(Customer customer);
    void delete(Long id);
    Customer getById(Long id);
    Customer findByCustomerSurname(String surname);
}
