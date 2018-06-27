package com.vue.admin.vueadminapi.filter;

import com.vue.admin.vueadminapi.config.Contants;
import com.vue.admin.vueadminapi.dto.UserDTO;
import com.vue.admin.vueadminapi.redis.RedisCacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: WB
 * @Date: 2018/6/21 09:47
 * @Description:
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    public static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("login") || requestURI.contains("reLogin") || requestURI.contains("logout")
                || requestURI.contains("error")) {
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader(Contants.HEADER_TOKEN);
        UserDTO loginUser = RedisCacheUtils.getFromCache(token);
        if (loginUser == null) {
            logger.error("TOKEN 过期");
            response.sendRedirect(request.getContextPath() + "/user/reLogin");
            return false;
        }
        return super.preHandle(request, response, handler);
    }

}
