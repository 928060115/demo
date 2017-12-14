package com.example.demo.interceptor;

import com.example.demo.entity.TokenInfoEntity;
import com.example.demo.jpa.TokenInfoJPA;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.interceptor
 * @Date: 2017/12/14
 * @Time: 16:58
 */
public class JwtTokenInterceptor implements HandlerInterceptor {

    /**
     * @Author: rogue
     * @Description: 请求之前
     * @ClassName: JwtTokenInterceptor
     * @Date: 2017/12/14
     * @Time: 16:59
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //自动排除生成token的路径,并且如果是options请求是cors跨域请求，设置allow对应头信息
        if (httpServletRequest.getRequestURI().equals("/token") || RequestMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            return true;
        }

        //其他请求获取头信息
        final String authHeader = httpServletRequest.getHeader("X-YAuth-Token");
        try {
            if (authHeader == null || authHeader.trim() == "") {
                throw new SignatureException("not found X-YAuth-Token");
            }

            //获取jwt实体对象接口实例
            final Claims claims = Jwts.parser().setSigningKey("OAuthor1.0.0").parseClaimsJws(authHeader).getBody();
            //从数据库中获取token
            TokenInfoEntity token = getDAO(TokenInfoJPA.class, httpServletRequest).findOne(new Specification<TokenInfoEntity>() {
                @Override
                public Predicate toPredicate(Root<TokenInfoEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("appId"), claims.getSubject()));
                    return null;
                }
            });
            //数据库中的token值
            String tokenval = new String(token.getToken());
            //如果内存中不存在，提示客户端获取token
            if (tokenval == null || tokenval.trim() == "") {
                throw new SignatureException("not found token info,please get token agin.");
            }
            //判断内存中token是否与客户端传来的一致
            if (!tokenval.equals(authHeader)) {
                throw new SignatureException("not found token info , please get token agin.");
            }
        } catch (SignatureException | ExpiredJwtException e) {
            //异常处理
            //输出对象
            PrintWriter printWriter = httpServletResponse.getWriter();
            //输出error消息
            printWriter.write("need refresh token");
            printWriter.close();
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private <T> T getDAO(Class<T> clazz,HttpServletRequest request){
        BeanFactory beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return beanFactory.getBean(clazz);
    }
}
