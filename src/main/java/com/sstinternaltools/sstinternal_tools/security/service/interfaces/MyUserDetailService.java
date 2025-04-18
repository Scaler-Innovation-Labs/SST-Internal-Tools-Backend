package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public interface MyUserDetailService {
    UserDetails loadUserByEmail(String email);
}
