package happy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class Listen01 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
      log.info("项目初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("销毁项目");
    }
}
