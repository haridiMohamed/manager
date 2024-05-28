package com.project.manager.user.service.interfaces;

import com.project.manager.user.entity.Provider;
import com.project.manager.user.entity.User;

import java.util.Optional;

public interface IProviderService {
    Provider save(Provider provider);

    Optional<Provider> findByProviderUser(User user);

}
