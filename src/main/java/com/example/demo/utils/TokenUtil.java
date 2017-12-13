package com.example.demo.utils;

import com.example.demo.service.TokenDetailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.utils
 * @Date: 2017/12/13
 * @Time: 11:29
 */
public class TokenUtil {
    private final static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration}")
    private Long expiration;

    /**
     * @Author: rogue
     * @Description: 根据TokenDetail生成token
     * @ClassName: TokenUtil
     * @Date: 2017/12/13
     * @Time: 11:41
     */
    public String generateToken(TokenDetailService tokenDetail){
        Map<String,Object> claims = new HashMap<String, Object>();
        claims.put("sub",tokenDetail.getUsername());
        claims.put("created",this.generateCurrentDate());
        return this.generateToken(claims);
    }
    
    /**
     * @Author: rogue
     * @Description: 根据claims生成token
     * @ClassName: TokenUtil
     * @Date: 2017/12/13
     * @Time: 11:42
     */
    private String generateToken(Map<String, Object> claims) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512,this.secret.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException e) {
            logger.warn(e.getMessage());
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512,this.secret)
                    .compact();
        }
    }

    /**
     * @Author: rogue
     * @Description: token过期时间
     * @ClassName: TokenUtil
     * @Date: 2017/12/13
     * @Time: 11:44
     */
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    /**
     * @Author: rogue
     * @Description: 获得当前时间
     * @ClassName: TokenUtil
     * @Date: 2017/12/13
     * @Time: 11:43
     */
    private Date generateCurrentDate(){
        return new Date(System.currentTimeMillis());
    }
}
