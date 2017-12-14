package com.example.demo.controller;

import com.example.demo.entity.TokenInfoEntity;
import com.example.demo.entity.UserInfoEntity;
import com.example.demo.jpa.TokenInfoJPA;
import com.example.demo.jpa.UserInfoJPA;
import com.example.demo.utils.TokenResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.controller
 * @Date: 2017/12/14
 * @Time: 15:55
 */
@RestController
@RequestMapping("/jwt")
public class TokenController {

    @Autowired
    private UserInfoJPA userInfoJPA;

    @Autowired
    private TokenInfoJPA tokenInfoJPA;

    /**
     * @Author: rogue
     * @Description: 获取token，更新token
     * @ClassName: TokenController
     * @Date: 2017/12/14
     * @Time: 16:04
     */
    @RequestMapping(value = "/token",method = {RequestMethod.GET,RequestMethod.POST})
    public TokenResult getToken(@RequestParam String appId,@RequestParam String appSecret){
        TokenResult token = new TokenResult();
        //appId is NULL
        if (appId == null || appId.trim() == ""){
            token.setFlag(false);
            token.setMsg("appId is not found");
        }
        //appSecret is NULL
        else if (appSecret == null || appSecret.trim() == ""){
            token.setFlag(false);
            token.setMsg("appSecret is not found");
        }
        else {
            //根据appId查询用户实体
            UserInfoEntity userInfoEntity = userInfoJPA.findOne(new Specification<UserInfoEntity>() {
                @Override
                public Predicate toPredicate(Root<UserInfoEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("appId"),appId));
                    return null;
                }
            });

            //如果不存在
            if (userInfoEntity == null){
                token.setFlag(false);
                token.setMsg("appId：" + appId + "is not found");
            }
            //验证appSecret是否存在
            else if (!new String(userInfoEntity.getAppSecret()).equals(appSecret.replace(" ","+"))){
                token.setFlag(false);
                token.setMsg("appSecret is not effective");
            }
            else {
                //检测数据库中是否存在该appId的token值
                TokenInfoEntity tokenInfoEntity = tokenInfoJPA.findOne(new Specification<TokenInfoEntity>() {
                    @Override
                    public Predicate toPredicate(Root<TokenInfoEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        criteriaQuery.where(criteriaBuilder.equal(root.get("appId"),appId));
                        return null;
                    }
                });
                //返回token值
                String tokenStr = null;
                //tokenInfoEntity == null --> 生成newToken --> 保存数据库 --> 写入内存 --> 返回newToken
                if (tokenInfoEntity == null){
                    tokenStr = createNewToken(appId);
                    //将token保存到数据库
                    tokenInfoEntity = new TokenInfoEntity();
                    tokenInfoEntity.setAppId(appId);
                    tokenInfoEntity.setBuildTime(String.valueOf(System.currentTimeMillis()));
                    tokenInfoEntity.setToken(tokenStr.getBytes());
                    tokenInfoJPA.save(tokenInfoEntity);
                }
                //tokenInfoEntity != null --> 验证是否超时 -->
                //不超时 --> token
                //超时 --> 生成newToken --> 保存数据库 --> 写入内存 --> 返回newToken
                else{
                    //判断数据库中token是否过期，如果没有过期不需要更新直接返回token即可
                    //数据库中的生成时间
                    long dbBuildTime = Long.valueOf(tokenInfoEntity.getBuildTime());
                    //当前时间
                    long currentTime = System.currentTimeMillis();
                    //如果当前时间-数据库生成时间<7200证明可以正常使用
                    long second = TimeUnit.MILLISECONDS.toSeconds(currentTime-dbBuildTime);
                    if (second > 0 && second < 7200){
                        tokenStr = new String(tokenInfoEntity.getToken());
                    }else {
                        //超时
                        tokenStr = createNewToken(appId);
                        //将token保存到数据库
                        tokenInfoEntity.setBuildTime(String.valueOf(System.currentTimeMillis()));
                        tokenInfoEntity.setToken(tokenStr.getBytes());
                        tokenInfoJPA.save(tokenInfoEntity);
                    }
                }
                //设置返回token
                token.setToken(tokenStr);
            }
        }
        return token;
    }

    /**
     * @Author: rogue
     * @Description: 生成新的token
     * @ClassName: TokenController
     * @Date: 2017/12/14
     * @Time: 16:30
     */
    private String createNewToken(String appId){
        //获取当前系统时间
        Date now = new Date(System.currentTimeMillis());
        //过期时间
        Date expiration = new Date(now.getTime() + 7200000);

        return Jwts
                .builder()
                .setSubject(appId)
                .setIssuedAt(now)
                .setIssuer("Online OAuth BuildER")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256,"OAuthor1.0.0")
                .compact();
    }
}
