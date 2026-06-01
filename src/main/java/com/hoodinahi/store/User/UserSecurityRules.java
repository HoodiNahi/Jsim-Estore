package com.hoodinahi.store.User;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

import com.hoodinahi.store.common.SecuirtyRules;

@Component
public class UserSecurityRules implements SecuirtyRules {

    @Override
    public void configure(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        // TODO Auto-generated method stub
        registry.requestMatchers(HttpMethod.POST, "/users").permitAll();
    }

}
