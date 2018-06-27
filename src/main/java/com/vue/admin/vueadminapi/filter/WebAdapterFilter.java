package com.vue.admin.vueadminapi.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther: WB
 * @Date: 2018/6/21 09:59
 * @Description:
 */
@Configuration
public class WebAdapterFilter extends WebMvcConfigurerAdapter {

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}
