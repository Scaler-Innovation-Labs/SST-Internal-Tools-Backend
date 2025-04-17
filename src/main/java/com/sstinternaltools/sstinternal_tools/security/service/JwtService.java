package com.sstinternaltools.sstinternal_tools.security.service;

import org.springframework.beans.factory.annotation.Value;

public class JwtService {

    @Value("${jwt.secretkey}")
    private String jwtSecreKey;



}
