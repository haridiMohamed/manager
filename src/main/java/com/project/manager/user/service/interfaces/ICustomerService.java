package com.project.manager.user.service.interfaces;

import com.project.manager.user.entity.Customer;
import com.project.manager.user.entity.User;

import java.util.Optional;

public interface ICustomerService {
    Customer save(Customer customer);

    Optional<Customer> findByCustomerUser(User user);


}
