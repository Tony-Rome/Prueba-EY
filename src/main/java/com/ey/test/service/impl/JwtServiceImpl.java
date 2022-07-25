package com.ey.test.service.impl;

import com.ey.test.service.JwtService;
import com.ey.test.util.DatetimeUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private static String SECRET = "test-jwt-secret";
    private static int EXPIRATION = 3600;

    @Override
    public String generateJwt(String email) {
        LocalDateTime currentDatetime = DatetimeUtil.getCurrentDateTime();
        Date issuedAt = DatetimeUtil.toDate(currentDatetime);
        Date expiration = DatetimeUtil.toDate(currentDatetime.plusSeconds(EXPIRATION));
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
