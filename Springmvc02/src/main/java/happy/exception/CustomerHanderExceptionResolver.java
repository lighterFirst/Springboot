package happy.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(value = Ordered.HIGHEST_PRECEDENCE)//最高优先级
@Component
public class CustomerHanderExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {

        try {
            response.sendError(522,"我的锅");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
