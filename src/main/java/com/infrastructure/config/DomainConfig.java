package com.infrastructure.config;

import com.usecase.admin.CreateUserUseCase;
import com.usecase.admin.ExternalUserProvider;
import com.usecase.admin.SyncUsersFromLdapUseCase;
import com.usecase.admin.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        return new CreateUserUseCase(userRepository);
    }

    @Bean
    public SyncUsersFromLdapUseCase syncUsersFromLdapUseCase(
            ExternalUserProvider ldapProvider,
            CreateUserUseCase createUserUseCase) {
        return new SyncUsersFromLdapUseCase(ldapProvider, createUserUseCase);
    }
}
