package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface MyUserDetailsService {
    UserDetails loadUserByEmail(String email);
}
