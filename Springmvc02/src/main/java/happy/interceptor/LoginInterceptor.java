package happy.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.info("preHandle：拦截请求的路径的是{}",url);
        HttpSession session = request.getSession();
        Object o1 = session.getAttribute("user");
        if(o1 != null){
            return true;
        }
            //拦截,回到登录页
            request.setAttribute("msg", "请重新登陆");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


        log.info("postHandle拦截的请求路径是{}",modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("afterCompletion的执行异常", ex);
    }
}
