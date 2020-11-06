package com.tatocuervo.springbootstarter.common.security;

import com.tatocuervo.springbootstarter.common.model.AppUser;
import com.tatocuervo.springbootstarter.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static java.lang.String.format;

/**
 * Implements UserDetailsService to perform user validation with JPA
 */

@Service
public class JpaUserDetailService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(JpaUserDetailService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.debug(format("Attempt to find username %s", s));
        AppUser appUser = userRepository.findByUsername(s);

        if (appUser == null) {
            logger.warn(format("User with name %s does nos exists", s));
            throw new UsernameNotFoundException(format("User with name %s does nos exists", s));
        }

        return new User(appUser.getUsername(), appUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(appUser.getRole())));
    }
}
