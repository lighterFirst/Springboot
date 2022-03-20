package happy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/css/*","/images/*"})
public class Filter01 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    log.info("123");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    log.info("2");
    filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    log.info("3");
    }

}
