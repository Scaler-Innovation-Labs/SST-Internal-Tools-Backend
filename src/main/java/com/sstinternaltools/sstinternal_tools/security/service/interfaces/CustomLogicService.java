package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import com.sstinternaltools.sstinternal_tools.user.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomLogicService {
     List<UserRole> assignRoles(String email);
}
