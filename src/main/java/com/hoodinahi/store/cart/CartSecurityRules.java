package com.hoodinahi.store.cart;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

import com.hoodinahi.store.common.SecuirtyRules;

@Component
public class CartSecurityRules implements SecuirtyRules {

    @Override
    public void configure(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        // TODO Auto-generated method stub
        registry.requestMatchers("/carts/**").permitAll();
    }
    
}
