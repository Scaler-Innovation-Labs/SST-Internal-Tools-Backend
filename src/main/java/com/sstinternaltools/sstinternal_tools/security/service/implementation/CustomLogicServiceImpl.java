package com.sstinternaltools.sstinternal_tools.security.service.implementation;

import com.sstinternaltools.sstinternal_tools.security.exception.RoleAssignmentException;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.CustomLogicService;
import com.sstinternaltools.sstinternal_tools.user.entity.Role;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.entity.UserRole;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CustomLogicServiceImpl implements CustomLogicService {

    private final UserRepository userRepository;

    public CustomLogicServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserRole> assignRoles(String email) {
        List<UserRole> roles = new ArrayList<>();

        try {
            String domain = email.split("@")[1];
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

            if (email.startsWith("srinidhi") || email.startsWith("vivek") || email.startsWith("rudray") || email.startsWith("yash") || domain.equals("scaler.com")) { // Need to change this after getting a priveleged email.
                UserRole adminRole = new UserRole();
                adminRole.setRole(Role.valueOf("admin".toUpperCase()));// Assign admin role. Excel sheet has already been checked for before saving the user.
                adminRole.setUser(user);
                roles.add(adminRole);
            }
            else if (domain.equals("sst.scaler.com")) {
                UserRole studentRole = new UserRole();
                studentRole.setRole(Role.valueOf("student".toUpperCase()));// Assign student role
                studentRole.setUser(user);
                roles.add(studentRole);
            }

        } catch (Exception e) {
            throw new RoleAssignmentException("Role assignment failed.");
        }
        return roles;
    }
}
