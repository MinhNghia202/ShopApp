//package com.project.shopapp.components;
//
//import com.project.shopapp.models.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.validation.Valid;
//import jakarta.websocket.Decoder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Slf4j
//@Component
//public class JwtTokenUtil {
//    @Value("${jwt.expiration}")
//    private long expiration; //save to an environment available
//
//    @Value("${jwt.secretKey}")
//    private String secretKey;
//
//    public String generateToken(User user) throws Exception{
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("phoneNumber", user.getPhoneNumber());
//        try {
//            return Jwts.builder()
//                    .setClaims(claims)
//                    .setSubject(user.getPhoneNumber())
//                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
//                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                    .compact();
//        }catch (Exception e){
//            log.error("Cannot create jwt token, error {}", e.getMessage());
//            return null;
//        }
//    }
//
//    private Key getSignInKey(){
//        byte[] bytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(bytes);
//    }
//
//    private Claims extractAllClaims(String token){
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(getSignInKey()) // Sử dụng khóa bí mật để xác thực token
//                    .build()
//                    .parseClaimsJws(token) // Phân tích cú pháp JWT
//                    .getBody(); // Trả về các Claims chứa thông tin từ token
//        } catch (Exception e) {
//            log.error("Cannot extract claims from token, error: {}", e.getMessage());
//            return null;
//        }
//    }
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
//        final Claims claims = this.extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    //Check expiration
//    public boolean tokenExpired(String token){
//        Date expirationDate = this.extractClaim(token,Claims::getExpiration);
//        return expirationDate.before(new Date());
//    }
//}
