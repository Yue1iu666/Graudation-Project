package com.example.index.interceptor;

import com.example.index.Utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.获取请求路径
//        String requestURI = request.getRequestURI();  //   /employee/basic/info  URI 是指 资源路径 即去掉协议 ip 端口号的路径
//
//        //2.判断请求路径是否为登录路径
//        if (requestURI.contains("login")){
//            log.info("是登录请求，放行");
//            return true;
//        }
        //3.获取请求头token
        String token = request.getHeader("token");

        //4.判断token是否为空,如果为空就响应状态码401
        if (token == null || token.isEmpty()){
            log.info("token为空，请重新登录");
            response.setStatus(401);
            return  false;
        }

        //5.如果token不为空 则校验token，如果校验不成功，应状态码401
        try {
            JwtUtils.parseJWT( token);
        } catch (Exception e) {
            log.info("token为空，请重新登录");
            response.setStatus(401);
            return false;
        }
        //6.如果token验证成功，则放行
        log.info("token验证成功，放行");
        return true;
    }
}

