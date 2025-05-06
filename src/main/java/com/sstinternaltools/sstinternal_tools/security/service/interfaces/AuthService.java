package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import java.util.Map;

public interface AuthService {
    public Map<String, String> rotateRefreshToken(String refreshToken);
    public void logout(String refreshToken);
    void register(String email);
}