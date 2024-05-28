package com.project.manager.user.repository;

import com.project.manager.user.entity.Customer;
import com.project.manager.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerUser(User user);


}
