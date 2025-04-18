package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import com.sstinternaltools.sstinternal_tools.user.entity.UserRole;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface AuthService {
    public Map<String, String> rotateRefreshToken(Cookie refreshCookie, HttpServletResponse response);
    public void logout(Cookie refreshCookie);
    void register(String email);
}
