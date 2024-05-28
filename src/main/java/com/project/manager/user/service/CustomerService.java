package com.project.manager.user.service;

import com.project.manager.user.entity.Customer;
import com.project.manager.user.entity.User;
import com.project.manager.user.repository.CustomerRepository;
import com.project.manager.user.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findByCustomerUser(User user) {
        return Optional.ofNullable(customerRepository.findByCustomerUser(user));
    }
}
