package com.hoodinahi.store.admin;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import com.hoodinahi.store.User.Role;
import com.hoodinahi.store.common.SecuirtyRules;

public class AdminSecurityRules implements SecuirtyRules {

    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
            .requestMatchers("/admin/**").hasRole(Role.ADMIN.name());
    }

}
