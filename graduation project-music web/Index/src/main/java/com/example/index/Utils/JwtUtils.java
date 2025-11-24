package com.example.index.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // ⚠️ 注意：Base64 编码的密钥必须解码后 >= 32 字节（用于 HS256）
    // 示例：生成一个 32 字节随机密钥 -> Base64 编码后使用
    // 正确示例（32字节原始数据）："ABCDEFGHIJKLMNOPQRSTUVWXYZ123456" -> Base64: "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVoxMjM0NTY="
    private static final String SIGNING_KEY_BASE64 = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVoxMjM0NTY="; // 替换为你自己的强密钥！

    private static final long EXPIRE_MILLIS = 43200000L; // 12 小时

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            java.util.Base64.getDecoder().decode(SIGNING_KEY_BASE64)
    );

    /**
     * 生成 JWT 令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        if (claims == null) {
            throw new IllegalArgumentException("Claims cannot be null");
        }

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_MILLIS))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 JWT 令牌（使用新版 JJWT API）
     */
    public static Claims parseJWT(String jwt) {
        if (jwt == null || jwt.trim().isEmpty()) {
            throw new IllegalArgumentException("JWT token is null or empty");
        }

        try {
            return Jwts.parser()
                    .verifyWith(SECRET_KEY) // 新版推荐方式
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "JWT token has expired", e);
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token format", e);
        } catch (SignatureException e) {
            throw new SignatureException("Invalid JWT signature", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }
}