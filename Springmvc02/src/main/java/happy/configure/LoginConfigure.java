package happy.configure;

import happy.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//将拦截器配置在容器中
@Configuration
public class LoginConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //如果是拦截所有，那么静态资源也会被拦截
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/","/login","css/**", "/public/error/css/**","/fonts/**", "public/error/fonts/**","/images/**", "public/error/images/**","/js/**", "public/error/js/**");
    }
}
