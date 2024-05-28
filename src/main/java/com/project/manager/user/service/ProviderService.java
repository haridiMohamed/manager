package com.project.manager.user.service;

import com.project.manager.user.entity.Provider;
import com.project.manager.user.entity.User;
import com.project.manager.user.repository.ProviderRepository;
import com.project.manager.user.service.interfaces.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService implements IProviderService {
    @Autowired
    ProviderRepository providerRepository;

    @Override
    public Provider save(Provider user) {

        return providerRepository.save(user);
    }

    @Override
    public Optional<Provider> findByProviderUser(User user) {
        return Optional.ofNullable(providerRepository.findByProviderUser(user));
    }
}
