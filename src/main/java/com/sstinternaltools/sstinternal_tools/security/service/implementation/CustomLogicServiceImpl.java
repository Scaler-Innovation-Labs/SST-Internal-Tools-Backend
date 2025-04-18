package com.sstinternaltools.sstinternal_tools.security.service.implementation;

import com.sstinternaltools.sstinternal_tools.security.service.interfaces.CustomLogicService;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.ExcelEmailChecker;
import com.sstinternaltools.sstinternal_tools.user.entity.Role;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.entity.UserRole;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomLogicServiceImpl implements CustomLogicService {

    private final ExcelEmailChecker excelEmailChecker;
    private final UserRepository userRepository;

    private static final String EXCEL_FILE_PATH = "src/main/resources/admins.xlsx";

    public CustomLogicServiceImpl(ExcelEmailChecker excelEmailChecker, UserRepository userRepository) {
        this.excelEmailChecker = excelEmailChecker;
        this.userRepository = userRepository;
    }

    public List<UserRole> assignRoles(String email) {
        List<UserRole> roles = new ArrayList<>();

        try {
            String domain = email.split("@")[1];
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

            if (domain.equals("scaler.com")) {
                if (excelEmailChecker.isEmailInExcel(email, EXCEL_FILE_PATH)) {
                    UserRole adminRole = new UserRole();
                    adminRole.setRole(Role.ADMIN);// Assign admin role if found in the Excel sheet
                    adminRole.setUser(user);
                    roles.add(adminRole);
                }
            }
            else if (domain.equals("sst.scaler.com")) {
                UserRole studentRole = new UserRole();
                studentRole.setRole(Role.STUDENT);// Assign student role
                studentRole.setUser(user);
                roles.add(studentRole);
            }

        } catch (Exception e) {
            throw new RoleAssignmentException("Role assignment failed.");
        }

        return roles;
    }
}

