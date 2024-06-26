package com.project.manager.user.repository;

import com.project.manager.user.entity.Provider;
import com.project.manager.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider findByProviderUser(User user);

}
