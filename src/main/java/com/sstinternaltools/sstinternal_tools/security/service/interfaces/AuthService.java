package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import com.sstinternaltools.sstinternal_tools.user.entity.UserRole;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface AuthService {
    public Map<String, String> rotateRefreshToken(String refreshToken);
    public void logout(String refreshToken);
    void register(String email);
}
