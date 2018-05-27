package com.sdut.trade.conf;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sdut.trade.annotation.LoginRequired;
import com.sdut.trade.dao.UserInfoDao;
import com.sdut.trade.entity.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：登陆拦截器
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/26
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserInfoDao userInfoDao;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {

            // 执行认证
            String token = (String) request.getSession().getAttribute("token");  // 从 http 请求头中取出 token
            if (token == null) {
                log.warn("无token，请重新登录");
                response.sendRedirect("/login");
                return false;
            }

            int userId;

            try {
                userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));  // 获取 token 中的 user id
            } catch (JWTDecodeException e) {
                log.warn("token无效，请重新登录");
                response.sendRedirect("/login");
                return false;
            }

            UserInfo user = userInfoDao.getById(userId);

            if (user == null) {
                log.warn("用户不存在，请重新登录");
                response.sendRedirect("/login");
                return false;
            }
            // 验证 token
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    verifier.verify(token);
                } catch (JWTVerificationException e) {
                    log.warn("token无效，请重新登录");
                    response.sendRedirect("/login");
                    return false;
                }
            } catch (UnsupportedEncodingException ignore) {
            }

            request.setAttribute("currentUser", user);

            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}

